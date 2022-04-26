package uz.iSystem.market.bookmarket.order;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import uz.iSystem.market.bookmarket.user.UserDto;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {

    private Integer id;
    private UserDto user;
    private Integer userId;
    private String requirement;
    private String contact;
    private String address;
    private LocalDateTime deliveryDate;
    private LocalDateTime deliveredDate;
    private String status;
}
