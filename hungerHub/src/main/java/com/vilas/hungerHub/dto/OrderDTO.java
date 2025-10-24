package com.vilas.hungerHub.dto;

import com.vilas.hungerHub.entity.Payment;
import com.vilas.hungerHub.entity.Status;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String id;
    private String userId;
    private String restaurantId;
    private List<OrderItemDTO> orderItems;
    private BigDecimal totalAmount;
    private Status status;
    private LocalDateTime orderDate;
    private Payment paymentType;

}
