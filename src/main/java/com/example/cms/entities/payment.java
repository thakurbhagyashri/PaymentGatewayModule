package com.example.cms.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_Transaction")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String paymentMethod;
    private Double amount;
    // private String currency;
    private String status;
    private String transactionId;
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;

    private LocalDateTime createdAt;

    @PrePersist
    public void OnCreate(){
        this.createdAt = LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private user user1;
}