package ra.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ra.model.entity.Roles;
import ra.model.entity.Users;

import java.util.List;

public interface UserRepository extends JpaRepository<Users,Integer> {
    Users findUsersByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    Page<Users> findByUserNameContaining(String name, Pageable pageable);
}
