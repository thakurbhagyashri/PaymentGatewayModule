package com.example.pgm.mapper;

import com.example.pgm.dto.PaymentDTO;
import com.example.pgm.entities.Payment;
import com.example.pgm.entities.User;

public class PaymentMapper {

    public static PaymentDTO toDTO(Payment entity) {
        return PaymentDTO.builder()
                .id(entity.getId())
                .paymentMethod(entity.getPaymentMethod())
                .amount(entity.getAmount())
                .status(entity.getStatus())
                .transactionId(entity.getTransactionId())
                .razorpayOrderId(entity.getRazorpayOrderId())
                .razorpayPaymentId(entity.getRazorpayPaymentId())
                .razorpaySignature(entity.getRazorpaySignature())
                .createdAt(entity.getCreatedAt())
                .userId(entity.getUser().getId())
                .build();
    }

    public static Payment toEntity(PaymentDTO dto, User userEntity) {
        return Payment.builder()
                .id(dto.getId())
                .paymentMethod(dto.getPaymentMethod())
                .amount(dto.getAmount())
                .status(dto.getStatus())
                .transactionId(dto.getTransactionId())
                .razorpayOrderId(dto.getRazorpayOrderId())
                .razorpayPaymentId(dto.getRazorpayPaymentId())
                .razorpaySignature(dto.getRazorpaySignature())
                .user(userEntity)
                .build();
    }
}
