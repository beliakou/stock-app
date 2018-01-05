package com.epam.mentoring.data.model.dto.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
public class ProductWithQuantityView {
    private Integer id;
    private String productName;
    private Integer quantity;
    private HashMap<String, Object> links;

    public ProductWithQuantityView(Integer id, String productName, Integer quantity) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.links = new HashMap<String, Object>();
    }
}