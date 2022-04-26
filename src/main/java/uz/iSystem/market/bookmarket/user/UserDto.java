package uz.iSystem.market.bookmarket.user;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import uz.iSystem.market.bookmarket.user.userRole.UserRoleDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String contact;
    private Integer imageId;
    private UserRoleDto userRole;
    private Integer userRoleId;
    private String token;
    private String status;
}
