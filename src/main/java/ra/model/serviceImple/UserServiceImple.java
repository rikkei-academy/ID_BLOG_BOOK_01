package ra.model.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.dto.response.UserDto;
import ra.model.entity.Users;
import ra.model.repository.UserRepository;
import ra.model.service.UserService;

import java.sql.SQLException;
import java.util.List;

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
    public List<UserDto> getAllByFilter(List<String> listFilter) {
        List<Users> usersList= userRepository.findAll();

        return null;
    }

    @Override
    public Page<UserDto> listUser(Pageable pageable) {
        return null;
    }

    @Override
    public UserDto saveOrUpdate(UserDto users) {
        return null;
    }

    @Override
    public UserDto findById(Integer id) {
        return null;
    }

    @Override
    public Page<UserDto> findByName(String name, Pageable pageable) {
        return null;
    }
}
