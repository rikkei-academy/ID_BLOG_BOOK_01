package ra.model.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.model.entity.Users;
import ra.model.repository.UserRepository;
import ra.model.service.UserService;

import java.sql.SQLException;

@Service
@Transactional(rollbackFor = SQLException.class)
public class UserServiceImple implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Users findUsersByUserName(String userName) {
        return userRepository.findUsersByUserName(userName);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Page<Users> listUser(Pageable pageable) {
        return null;
    }

    @Override
    public Users saveOrUpdate(Users users) {
        return null;
    }

    @Override
    public Users findById(Integer id) {
        return null;
    }

    @Override
    public Page<Users> findByName(String name, Pageable pageable) {
        return null;
    }
}
