package ra.model.service;

import org.springframework.stereotype.Repository;
import ra.model.entity.ERole;
import ra.model.entity.Roles;

import java.util.Optional;

@Repository
public interface RoleService {
    Optional<Roles> findByRoleName(ERole roleName);
}