package com.example.pgm.service;
import com.example.pgm.dto.OrderResponseDTO;
import com.example.pgm.dto.PaymentDTO;

import java.util.List;

public interface  PaymentService {
    PaymentDTO createPayment(PaymentDTO dto);
    PaymentDTO getPaymentById(Long id);
    List<PaymentDTO> getAllPayments();
    void deletePayment(Long id);
    OrderResponseDTO createPaymentOrder(long amount);

}
