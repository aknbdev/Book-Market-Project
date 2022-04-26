package uz.iSystem.market.bookmarket.product.productType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {

    @Query(value = "SELECT * FROM product_types WHERE deleted_date IS NULL", nativeQuery = true)
    List<ProductType> getAllByDeleted_dateIsNull();

    @Query(value = "SELECT * FROM product_types WHERE id = :id AND deleted_date IS NULL", nativeQuery = true)
    Optional<ProductType> getByIdAndDeleted_dateIsNull(Integer id);
}
