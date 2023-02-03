package com.csi.dto.response;

import com.csi.model.OrderDetails;
import com.csi.vo.Product;
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
