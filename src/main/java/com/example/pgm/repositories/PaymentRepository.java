package com.example.pgm.repositories;
import com.example.pgm.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUser_Id(Long userId);
    Payment findByTransactionId(String transactionId);
    List<Payment> findByStatus(String status);
}
