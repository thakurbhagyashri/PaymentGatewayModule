package com.example.pgm.controller;
import com.example.pgm.dto.OrderResponseDTO;
import com.example.pgm.dto.PaymentDTO;
import com.example.pgm.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

//    @PostMapping
//    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO dto) {
//        return ResponseEntity.ok(paymentService.createPayment(dto));
//   }
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/createOrder")
    public ResponseEntity<OrderResponseDTO> createPaymentOrder(@RequestParam("amount") long amount) {
        return ResponseEntity.ok(paymentService.createPaymentOrder(amount));
    }
}
