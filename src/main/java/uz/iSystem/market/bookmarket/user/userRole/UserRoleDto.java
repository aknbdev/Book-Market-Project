package uz.iSystem.market.bookmarket.user.userRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRoleDto {
    private Integer id;
    private String name;
    private String status;
}
