package uz.iSystem.market.bookmarket.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.iSystem.market.bookmarket.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = ("user_id"), insertable = false, updatable = false)
    private User user;

    @Column(name = ("user_id"))
    private Integer userId;

    @Column(name = ("requirement"))
    private String requirement;

    @Column(name = ("contact"))
    private String contact;

    @Column(name = ("address"))
    private String address;

    @Column(name = ("delivery_date"))
    private LocalDateTime deliveryDate;

    @Column(name = ("delivered_date"))
    private LocalDateTime deliveredDate;

    @Column(name = ("status"))
    private String status;

    @Column(name = "created_date")
    private LocalDateTime created_date;

    @Column(name = "updated_date")
    private LocalDateTime updated_date;

    @Column(name = "deleted_date")
    private LocalDateTime deleted_date;
}
