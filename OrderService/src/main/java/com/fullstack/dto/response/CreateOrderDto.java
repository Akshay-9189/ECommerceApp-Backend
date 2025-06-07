package com.fullstack.dto.response;

import com.fullstack.model.OrderDetails;
import com.fullstack.vo.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateOrderDto {

    private OrderDetails orderDetails;

    private Product product;
}
