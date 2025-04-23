package com.example.pgm.serviceImpl;
import com.example.pgm.dto.OrderResponseDTO;
import com.example.pgm.dto.PaymentDTO;
import com.example.pgm.entities.Payment;
import com.example.pgm.entities.User;
import com.example.pgm.mapper.PaymentMapper;
import com.example.pgm.repositories.PaymentRepository;
import com.example.pgm.repositories.UserRepository;
import com.example.pgm.service.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
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
    // Create Payment Order (for Razorpay or other payment gateway)
    @Override
    public OrderResponseDTO createPaymentOrder(long amount) {
        try {
            RazorpayClient razorpay = new RazorpayClient("rzp_test_YourKey", "YourSecret");

            JSONObject options = new JSONObject();
//            The reason for multiplying the amount by 100 is because Razorpay API expects
//            the amount to be in "paise", which is the subunit of the Indian rupee (INR). Here's how it works:
            options.put("amount", amount * 100); // Razorpay works in paise
            options.put("receipt", "txn_" + System.currentTimeMillis());
            options.put("payment_capture", 1);

            Order order = razorpay.orders.create(options);

            return OrderResponseDTO.builder()
                    .orderId(order.get("id"))
                    .amount(amount * 1.0)
                    .status(order.get("status"))
                    .build();

        } catch (RazorpayException e) {
            throw new RuntimeException("Error creating Razorpay order: " + e.getMessage());
        }
    }
}
