package com.example.securitytest.restController;
import com.example.securitytest.common.LogUtils;
import com.example.securitytest.common.RSAUtils;
import com.example.securitytest.common.ResponseWrapper;
import com.example.securitytest.dto.TransactionHistoryDTO;
import com.example.securitytest.service.ItranSactionService;
import com.example.securitytest.service.impl.TranSactionService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import java.security.*;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("training")
public class TransactionRestController {
    private final ItranSactionService tranSactionService;

    private final PrivateKey privateKey; // PrivateKey sẽ được inject từ configuration
    private final PublicKey publicKey;
    /**
     * Xử lý yêu cầu thêm giao dịch vào hệ thống. Nếu có lỗi xảy ra trong quá trình thêm giao dịch,
     * trả về thông báo lỗi chi tiết cho người dùng.
     *
     * @param transactionHistoryDTO đối tượng chứa thông tin giao dịch cần thêm vào hệ thống.
     * @return {@code ResponseEntity<ResponseWrapper<TransactionHistoryDTO>>} đối tượng phản hồi chứa thông tin giao dịch
     *         và kết quả thực hiện.
     * @throws MethodArgumentNotValidException nếu dữ liệu đầu vào không hợp lệ, không thỏa mãn các ràng buộc của DTO.
     * @throws ConstraintViolationException nếu dữ liệu vi phạm các ràng buộc từ JSR 303 (như giá trị trống hoặc không hợp lệ).
     */
    @PostMapping("/transaction")
    public ResponseEntity<ResponseWrapper<TransactionHistoryDTO>> transactionHistory(
            @RequestBody TransactionHistoryDTO transactionHistoryDTO) {
        try {
            // Thực hiện logic thêm giao dịch
            tranSactionService.addTranSaction(transactionHistoryDTO);

            // Trả về kết quả thành công
            return ResponseEntity.ok(new ResponseWrapper<>(transactionHistoryDTO, "Success"));
        }catch (MethodArgumentNotValidException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseWrapper<>(null, "Dữ liệu không hợp lệ, vui lòng kiểm tra lại"));

        } catch (ConstraintViolationException e) {
            System.err.println("Lỗi ràng buộc dữ liệu: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseWrapper<>(null, "Dữ liệu không hợp lệ, vi phạm ràng buộc"));

        }catch (Exception e) {
            // Che dữ liệu nhạy cảm
            String maskedTransactionId = transactionHistoryDTO.getTransactionId() != null
                    ? LogUtils.maskSensitiveData(transactionHistoryDTO.getTransactionId())
                    : "?";
            String maskedAccount = transactionHistoryDTO.getAccount() != null
                    ? LogUtils.maskSensitiveData(transactionHistoryDTO.getAccount())
                    : "?";
            String maskedInDebt = transactionHistoryDTO.getInDebt() != null
                    ? LogUtils.maskSensitiveData(transactionHistoryDTO.getInDebt().toString())
                    : "?";
            String maskedHave = transactionHistoryDTO.getHave() != null
                    ? LogUtils.maskSensitiveData(transactionHistoryDTO.getHave().toString())
                    : "?";
            String maskedTime = transactionHistoryDTO.getTime() != null
                    ? LogUtils.maskSensitiveData(transactionHistoryDTO.getTime().toString())
                    : "?";

            // Log lỗi với thông tin đã che
            System.err.println("Lỗi xử lý dữ liệu: " + e.getMessage());
            System.err.printf("TransactionID: %s, Account: %s, InDebt: %s, Have: %s, Time: %s%n",
                    maskedTransactionId, maskedAccount, maskedInDebt, maskedHave, maskedTime);

            // Trả về phản hồi lỗi
            ResponseWrapper<TransactionHistoryDTO> errorResponse = new ResponseWrapper<>(null, "Không có sản phẩm nào");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    /**
     * Nhận và giải mã giao dịch từ request, sau đó xử lý dữ liệu giao dịch và lưu trữ hoặc thực hiện hành động khác.
     * Các tham số trong yêu cầu được mã hóa trước khi gửi đến API này và được giải mã lại trước khi xử lý.
     *
     * @param transactionId mã giao dịch (đã mã hóa).
     * @param account tài khoản giao dịch (đã mã hóa).
     * @param inDebt số tiền nợ (đã mã hóa).
     * @param have trạng thái có tiền hay không (đã mã hóa).
     * @param time thời gian giao dịch (đã mã hóa).
     * @return {@code ResponseEntity<String>} đối tượng phản hồi với thông báo kết quả xử lý sau khi giải mã và xử lý.
     * @throws BadPaddingException nếu có lỗi trong quá trình giải mã dữ liệu.
     */
    @PostMapping("/receive")
    public ResponseEntity<String> receiveTransaction(
            @RequestParam String transactionId,
            @RequestParam String account,
            @RequestParam String inDebt,
            @RequestParam String have,
            @RequestParam String time) {

        try {

            // tạo mã dữ liệu từ các tham số
            String encryptedTransactionId =RSAUtils. encrypt(transactionId, publicKey);
            String encryptedAccount = RSAUtils.encrypt(account, publicKey);
            String encryptedInDebt = RSAUtils.encrypt(inDebt, publicKey);
            String encryptedHave = RSAUtils.encrypt(have, publicKey);
            String encryptedTime =RSAUtils. encrypt(time, publicKey);
            // Giải mã dữ liệu từ các tham số
            String decryptedTransactionId = RSAUtils.decrypt(encryptedTransactionId, privateKey);
            String decryptedAccount = RSAUtils.decrypt(encryptedAccount, privateKey);
            String decryptedInDebt = RSAUtils.decrypt(encryptedInDebt, privateKey);
            String decryptedHave = RSAUtils.decrypt(encryptedHave, privateKey);
            String decryptedTime = RSAUtils.decrypt(encryptedTime, privateKey);
            // Tạo đối tượng DTO để lưu trữ hoặc xử lý


            // Chuyển đổi dữ liệu về kiểu dữ liệu ban đầu
            Double inDebtValue = Double.parseDouble(decryptedInDebt);
            Boolean haveValue = Boolean.parseBoolean(decryptedHave);
            LocalDateTime timeValue = LocalDateTime.parse(decryptedTime);
            TransactionHistoryDTO transactionDTO = new TransactionHistoryDTO();
            transactionDTO.setTransactionId(decryptedTransactionId);
            transactionDTO.setAccount(decryptedAccount);
            transactionDTO.setInDebt(inDebtValue);
            transactionDTO.setTime(timeValue);
            transactionDTO.setHave(haveValue);

            tranSactionService.processTransaction(transactionDTO);

            // Xử lý dữ liệu
            return ResponseEntity.ok("Dữ liệu đã được giải mã và xử lý.");
        } catch (BadPaddingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi giải mã dữ liệu: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi khác: " + e.getMessage());
        }
    }



}
