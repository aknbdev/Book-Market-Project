package uz.iSystem.market.bookmarket.user.userRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    @Query(value = "SELECT * FROM user_roles where deleted_date is null", nativeQuery = true)
    List<UserRole> getAllByDeletedDateIsNull();

    @Query(value = "SELECT * FROM user_roles where id = :id and deleted_date is null", nativeQuery = true)
    Optional<UserRole> findByIdAndDeleted_dateIsNull(Integer id);

    @Query(value = "SELECT * FROM user_roles where name = :name and deleted_date is null", nativeQuery = true)
    Optional<UserRole> getBynameAndDeletedDateIsNull(String name);
}
