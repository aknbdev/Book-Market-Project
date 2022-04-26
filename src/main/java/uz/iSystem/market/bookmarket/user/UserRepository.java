package uz.iSystem.market.bookmarket.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM users WHERE deleted_date IS NULL", nativeQuery = true)
    List<User> getAllByDeletedDateIsNull();

    @Query(value = "SELECT * FROM users WHERE email = :email AND contact = :contact AND deleted_date IS NULL", nativeQuery = true)
    Optional<User> getByEmailOrContactAndDeleted_dateIsNull(String email, String contact);

    @Query(value = "SELECT * FROM users WHERE id = :id AND deleted_date IS NULL", nativeQuery = true)
    Optional<User> getByIdAndDeleted_dateIsNull(Integer id);

    @Query(value = "SELECT * FROM users WHERE phone = :phone AND deleted_date IS NULL", nativeQuery = true)
    Optional<User> getByPhoneAndDeletedAtIsNull(String phone);

    @Query(value = "SELECT * FROM users WHERE phone = :phone AND password = :password AND deleted_date IS NULL", nativeQuery = true)
    @Transactional
    Optional<User> authorize(String phone, String password);
}
