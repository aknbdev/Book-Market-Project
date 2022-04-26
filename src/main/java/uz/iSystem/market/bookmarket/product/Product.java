package uz.iSystem.market.bookmarket.product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.iSystem.market.bookmarket.product.productType.ProductType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = ("name"))
    private String name;

    @Column(name = ("description"))
    private String description;

    @Column(name = ("price"))
    private Double price;

    @Column(name = ("rate"))
    private Integer rate;

    @Column(name = ("visible"))
    private Boolean visible;

    @ManyToOne
    @JoinColumn(name = ("product_type_id"), insertable = false, updatable = false)
    private ProductType productType;

    @Column(name = ("product_type_id"))
    private Integer productTypeId;

    @Column(name = ("status"))
    private String status;

    @Column(name = "created_date")
    private LocalDateTime created_date;

    @Column(name = "updated_date")
    private LocalDateTime updated_date;

    @Column(name = "deleted_date")
    private LocalDateTime deleted_date;
}
