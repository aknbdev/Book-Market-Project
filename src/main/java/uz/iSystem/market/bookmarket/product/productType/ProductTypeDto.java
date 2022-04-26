package uz.iSystem.market.bookmarket.product.productType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductTypeDto {

    private Integer id;
    private String name;
    private String status;
}
