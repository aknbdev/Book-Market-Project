package uz.iSystem.market.bookmarket.user;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.iSystem.market.bookmarket.user.userRole.UserRole;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = ("users"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = ("name"))
    private String name;

    @Column(name = ("surname"))
    private String surname;

    @Column(name = ("email"))
    private String email;

    @Column(name = ("password"))
    private String password;

    @Column(name = ("contact"))
    private String contact;

    @Column(name = ("image_id"))
    private Integer imageId;

    @ManyToOne
    @JoinColumn(name = ("user_role_id"), updatable = false, insertable = false)
    private UserRole userRole;

    @Column(name = ("user_role_id"))
    private Integer userRoleId;

    @Column(name = ("status"))
    private String status;

    @Column(name = "created_date")
    private LocalDateTime created_date;

    @Column(name = "updated_date")
    private LocalDateTime updated_date;

    @Column(name = "deleted_date")
    private LocalDateTime deleted_date;
}
