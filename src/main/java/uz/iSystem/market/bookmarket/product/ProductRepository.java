package uz.iSystem.market.bookmarket.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM products WHERE deleted_date IS NULL", nativeQuery = true)
    List<Product> getAllByDeleted_dateIsNull();

    @Query(value = "SELECT * FROM products WHERE id = :id AND deleted_date IS NULL", nativeQuery = true)
    Optional<Product> getByIdAndDeleted_dateIsNull(Integer id);
}
