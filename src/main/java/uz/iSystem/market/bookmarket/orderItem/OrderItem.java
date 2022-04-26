package uz.iSystem.market.bookmarket.orderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.iSystem.market.bookmarket.order.Order;
import uz.iSystem.market.bookmarket.product.Product;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = ("order_id"), insertable = false, updatable = false)
    private Order order;

    @Column(name = ("order_id"))
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = ("product_id"), insertable = false, updatable = false)
    private Product product;

    @Column(name = ("product_id"))
    private Integer productId;

    @Column(name = ("status"))
    private String status;

    @Column(name = "created_date")
    private LocalDateTime created_date;

    @Column(name = "updated_date")
    private LocalDateTime updated_date;

    @Column(name = "deleted_date")
    private LocalDateTime deleted_date;
}
