package uz.iSystem.market.bookmarket.orderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.iSystem.market.bookmarket.order.Order;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    @Query(value = "SELECT * FROM order_items WHERE deleted_date IS NULL", nativeQuery = true)
    List<OrderItem> getAllByDeleted_dateIsNull();

    @Query(value = "SELECT * FROM order_items WHERE id = :id AND deleted_date IS NULL", nativeQuery = true)
    Optional<OrderItem> getByIdAndDeleted_dateIsNull(Integer id);
}
