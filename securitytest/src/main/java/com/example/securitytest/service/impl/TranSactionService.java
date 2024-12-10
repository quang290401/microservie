package com.example.securitytest.service.impl;

import com.example.securitytest.common.AESUtils;
import com.example.securitytest.common.RSAUtils;
import com.example.securitytest.dto.TransactionHistoryDTO;
import com.example.securitytest.entity.TransactionHistoryEntity;
import com.example.securitytest.repository.TransactionRepository;
import com.example.securitytest.service.ItranSactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.BadPaddingException;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TranSactionService implements ItranSactionService {
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;
    private final PublicKey publicKey;
    private final RestTemplate restTemplate;

    /**
     * Xử lý logic thêm giao dịch mới, bao gồm việc kiểm tra thông tin, mã hóa dữ liệu
     * và lưu thông tin giao dịch vào cơ sở dữ liệu.
     *
     * @param transactionHistoryDTO đối tượng chứa thông tin giao dịch, bao gồm:
     *                              - {@code account}: Tài khoản tham gia giao dịch.
     * @return Đối tượng {@code TransactionHistoryDTO} chứa thông tin giao dịch đã được thêm thành công.
     *         Dữ liệu này được ánh xạ từ {@code TransactionHistoryEntity}.
     * @throws IllegalArgumentException nếu tài khoản không hợp lệ (null hoặc rỗng).
     * @throws Exception nếu xảy ra lỗi trong quá trình mã hóa hoặc lưu dữ liệu.
     */
    @Override
    public TransactionHistoryDTO addTranSaction(TransactionHistoryDTO transactionHistoryDTO) throws Exception {

        LocalDateTime now = LocalDateTime.now();

        // Kiểm tra tài khoản nguồn và đích
        if (transactionHistoryDTO.getAccount() == null || transactionHistoryDTO.getAccount().isEmpty()) {
            throw new IllegalArgumentException("Source account is required");
        }
        if (transactionHistoryDTO.getDestinationAccount() == null || transactionHistoryDTO.getDestinationAccount().isEmpty()) {
            throw new IllegalArgumentException("Destination account is required");
        }
        if (transactionHistoryDTO.getInDebt() == null || transactionHistoryDTO.getInDebt() <= 0) {
            throw new IllegalArgumentException("Transaction amount must be greater than 0");
        }

        // Mã hóa số tài khoản bằng AES trước khi lưu
        String encryptedSourceAccount = AESUtils.encrypt(transactionHistoryDTO.getAccount());
        String encryptedDestinationAccount = AESUtils.encrypt(transactionHistoryDTO.getDestinationAccount());

        // Bản ghi nợ cho tài khoản nguồn
        TransactionHistoryEntity sourceTransaction = new TransactionHistoryEntity();
        sourceTransaction.setTransactionId(UUID.randomUUID().toString());
        sourceTransaction.setAccount(encryptedSourceAccount);
        sourceTransaction.setInDebt(transactionHistoryDTO.getInDebt()); // Số tiền nợ
        sourceTransaction.setHave(true); // Không có khoản có
        sourceTransaction.setTime(now);

        // Bản ghi có cho tài khoản đích
        TransactionHistoryEntity destinationTransaction = new TransactionHistoryEntity();
        destinationTransaction.setTransactionId(UUID.randomUUID().toString());
        destinationTransaction.setAccount(encryptedDestinationAccount);
        destinationTransaction.setInDebt(0.0); // Không có khoản nợ
        destinationTransaction.setHave(true); // Số tiền có
        destinationTransaction.setTime(now);

        // Lưu cả hai bản ghi vào cơ sở dữ liệu
        transactionRepository.save(sourceTransaction);
        transactionRepository.save(destinationTransaction);

        // Ánh xạ bản ghi giao dịch nợ (tài khoản nguồn) sang DTO để trả về
        return modelMapper.map(sourceTransaction, TransactionHistoryDTO.class);
    }

    /**
     * Xử lý giao dịch bằng cách mã hóa dữ liệu nhạy cảm và gửi đến các service khác.
     *
     * @param transactionHistoryDTO đối tượng chứa thông tin giao dịch cần xử lý.
     * @throws Exception nếu xảy ra lỗi trong quá trình mã hóa hoặc gửi dữ liệu.
     */
    public void processTransaction(TransactionHistoryDTO transactionHistoryDTO) throws Exception {


        // Mã hóa các thông tin cần truyền qua các service bằng RSA
        String encryptedTransactionId = RSAUtils.encrypt(transactionHistoryDTO.getTransactionId(), publicKey);
        String encryptedAccount = RSAUtils.encrypt(transactionHistoryDTO.getAccount(), publicKey);
        String encryptedInDebt = RSAUtils.encrypt(transactionHistoryDTO.getInDebt().toString(), publicKey); // Nợ (Double -> String)
        Boolean haveValue = transactionHistoryDTO.getHave() != null ? transactionHistoryDTO.getHave() : Boolean.FALSE;
        String encryptedHave = RSAUtils.encrypt(haveValue.toString(), publicKey); // Boolean -> String
        String encryptedTime = RSAUtils.encrypt(transactionHistoryDTO.getTime().toString(), publicKey); // Time -> String

        // Gửi thông tin đã mã hóa qua các service khác (hoặc lưu lại trong cơ sở dữ liệu, tùy theo yêu cầu của bạn)
        // Ví dụ gọi service khác hoặc trả về các dữ liệu đã mã hóa
        sendToAnotherService(encryptedTransactionId, encryptedAccount, encryptedInDebt, encryptedHave, encryptedTime);
    }
    /**
     * Gửi dữ liệu giao dịch đã mã hóa tới một service khác qua HTTP POST request.
     *
     * @param encryptedTransactionId ID giao dịch đã được mã hóa.
     * @param encryptedAccount Tài khoản đã được mã hóa.
     * @param encryptedInDebt Giá trị nợ đã được mã hóa.
     * @param encryptedHave Giá trị "có" đã được mã hóa.
     * @param encryptedTime Thời gian giao dịch đã được mã hóa.
     */
    private void sendToAnotherService(String encryptedTransactionId, String encryptedAccount, String encryptedInDebt, String encryptedHave, String encryptedTime) {
        // Endpoint của service khác
        String url = "http://localhost:8080/sento";

        // Tạo headers, sử dụng đúng lớp HttpHeaders từ Spring
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Tạo đối tượng yêu cầu (Request Body) với dữ liệu đã mã hóa
        String requestBody = String.format("{\"transactionId\":\"%s\", \"account\":\"%s\", \"inDebt\":\"%s\", \"have\":\"%s\", \"time\":\"%s\"}",
                encryptedTransactionId, encryptedAccount, encryptedInDebt, encryptedHave, encryptedTime);

        // Gửi POST request
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(url,  HttpMethod.POST, request, String.class);

        // Kiểm tra phản hồi từ service khác
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Dữ liệu đã được gửi thành công.");
        } else {
            System.err.println("Lỗi khi gửi dữ liệu: " + response.getStatusCode());
        }
    }

}
