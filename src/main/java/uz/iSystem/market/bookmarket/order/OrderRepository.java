package uz.iSystem.market.bookmarket.order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT * FROM orders WHERE deleted_date IS NULL", nativeQuery = true)
    List<Order> getAllByDeleted_dateIsNull();

    @Query(value = "SELECT * FROM orders WHERE id = :id AND deleted_date IS NULL", nativeQuery = true)
    Optional<Order> getByIdAndDeleted_dateIsNull(Integer id);
}
