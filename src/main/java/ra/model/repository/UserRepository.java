package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.model.entity.Users;

public interface UserRepository extends JpaRepository<Users,Integer> {
    Users findUsersByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
