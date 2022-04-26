package uz.iSystem.market.bookmarket.product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import uz.iSystem.market.bookmarket.product.productType.ProductTypeDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer rate;
    private Boolean visible;
    private ProductTypeDto productType;
    private Integer productTypeId;
    private String status;
}
