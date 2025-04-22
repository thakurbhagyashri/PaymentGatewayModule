package com.example.pgm.serviceImpl;
import com.example.pgm.dto.PaymentDTO;
import com.example.pgm.entities.Payment;
import com.example.pgm.entities.User;
import com.example.pgm.mapper.PaymentMapper;
import com.example.pgm.repositories.PaymentRepository;
import com.example.pgm.repositories.UserRepository;
import com.example.pgm.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    @Override
    public PaymentDTO createPayment(PaymentDTO dto) {
        User userEntity = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new com.example.pgm.exceptions.ResourceNotFoundException("User", "ID", dto.getUserId()));
        Payment entity = PaymentMapper.toEntity(dto, userEntity);
        return PaymentMapper.toDTO(paymentRepository.save(entity));
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        Payment entity = paymentRepository.findById(id)
                .orElseThrow(() -> new com.example.pgm.exceptions.ResourceNotFoundException("Payment", "ID", id));
        return PaymentMapper.toDTO(entity);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(PaymentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePayment(Long id) {
        Payment entity = paymentRepository.findById(id)
                .orElseThrow(() -> new com.example.pgm.exceptions.ResourceNotFoundException("Payment", "ID", id));
        paymentRepository.delete(entity);
    }
}
