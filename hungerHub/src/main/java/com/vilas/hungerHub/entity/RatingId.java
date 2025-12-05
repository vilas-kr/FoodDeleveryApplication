package com.vilas.hungerHub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingId {

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "menu_id")
    private String menuId;

}
