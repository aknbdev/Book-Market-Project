package uz.iSystem.market.bookmarket.product.productType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product_types")
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = ("name"))
    private String name;

    @Column(name = ("status"))
    private String status;

    @Column(name = "created_date")
    private LocalDateTime created_date;

    @Column(name = "updated_date")
    private LocalDateTime updated_date;

    @Column(name = "deleted_date")
    private LocalDateTime deleted_date;
}
