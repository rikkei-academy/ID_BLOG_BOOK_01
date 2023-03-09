package ra.model.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.dto.response.UserDto;
import ra.model.entity.Filter;
import ra.model.entity.Roles;
import ra.model.entity.Users;
import ra.model.repository.UserRepository;
import ra.model.service.UserService;
import ra.security.CustomUserDetails;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

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
    public List<Users> getAllByFilter(List<Filter> listFilter) {
        List<Users> usersList = userRepository.findAll();
        Iterator<Users> iterator = usersList.iterator();
        for (int i = 0; i < listFilter.size(); i++) {
            Filter rule = listFilter.get(i);
            switch (rule.getRulesName()) {
                case "status":
                    while (iterator.hasNext()) {
                        Users user = iterator.next();
                        if (user.isStatusUser()!=Boolean.parseBoolean(rule.getRulesValue())) {
                            iterator.remove();
                        }
                    }
                    break;
                case "city":
                    while (iterator.hasNext()) {
                        Users user = iterator.next();
                        if (!user.getCity().equals(rule.getRulesValue())) {
                            iterator.remove();
                        }
                    }
                    break;
                case "state":
                    while (iterator.hasNext()) {
                        Users user = iterator.next();
                        if (!user.getState().equals(rule.getRulesValue())) {
                            iterator.remove();
                        }
                    }
                    break;
            }
        }
        return usersList;
    }
    @Override
    public Page<Users> listUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    @Override
    public Users saveOrUpdate(Users users) {
        return userRepository.save(users);
    }
    @Override
    public Users findById(Integer id) {
        return userRepository.findById(id).get();
    }
    @Override
    public Page<Users> findByName(String name, Pageable pageable) {
        return userRepository.findByUserNameContaining(name, pageable);
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDto mapUserToUserDto(Users users) {
        UserDto userDto = new UserDto();
        userDto.setUserId(users.getUserId());
        userDto.setUserName(users.getUserName());
        userDto.setFirstName(users.getFirstName());
        userDto.setLastName(users.getLastName());
        userDto.setEmail(users.getEmail());
        userDto.setAddress(users.getAddress());
        userDto.setState(users.getState());
        userDto.setCity(users.getCity());
        userDto.setPost(users.getPost());
        userDto.setPhone(users.getPhone());
        userDto.setAvatar(users.getAvatar());
        userDto.setBirtDate(users.getBirtDate());
        userDto.setStatusUser(users.isStatusUser());
        userDto.setRanks(users.getRanks());
        List<Roles> roles = new ArrayList<>(users.getListRoles());
        List<String> stringList = roles.stream().map(roles1 -> roles1.getRoleName().toString()).collect(Collectors.toList());
        userDto.setListRoles(stringList);
        return userDto;
    }

    @Override
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
