package com.example.securitytest.service;

import com.example.securitytest.dto.TransactionHistoryDTO;

public interface ItranSactionService {
    /*
     @param transactionHistoryDTO đối tượng chứa thông tin chi tiết về giao dịch, bao gồm:
                                  thêm 2 bản ghi vào co du lieu ( tài khoản nguồn, tài khoản đích)
 *                              - {@code transactionId}: ID của giao dịch (bắt buộc, không được null).
 *                              - {@code account}: Số tài khoản liên quan đến giao dịch (bắt buộc, không được null).
 *                              - {@code inDebt}: Giá trị nợ trong giao dịch (bắt buộc, không được null).
 *                              - {@code have}: Giá trị có trong giao dịch (bắt buộc, không được null).
 *                              - {@code time}: Thời gian phát sinh giao dịch (bắt buộc, không được null).
    */
    TransactionHistoryDTO addTranSaction(TransactionHistoryDTO transactionHistoryDTO) throws Exception;
    /**
     * Xử lý giao dịch bằng cách mã hóa dữ liệu nhạy cảm và gửi đến các service khác.
     *
     * @param transactionHistoryDTO đối tượng chứa thông tin giao dịch cần xử lý.
     * @throws Exception nếu xảy ra lỗi trong quá trình mã hóa hoặc gửi dữ liệu.
     */

    void processTransaction(TransactionHistoryDTO transactionHistoryDTO) throws Exception;
}
