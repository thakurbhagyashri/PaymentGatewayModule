package com.example.pgm.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponseDTO {

    private String orderId;
    private Double amount;
    private String status;
    //  //
}
