package uz.iSystem.market.bookmarket.orderItem;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import uz.iSystem.market.bookmarket.order.OrderDto;
import uz.iSystem.market.bookmarket.product.ProductDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemDto {

    private Integer id;
    private OrderDto order;
    private Integer orderId;
    private ProductDto product;
    private Integer productId;
    private String status;
}
