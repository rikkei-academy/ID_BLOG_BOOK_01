package ra.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.dto.response.UserDto;
import ra.model.entity.Filter;
import ra.model.entity.Users;

import java.util.List;


public interface UserService extends RootService<Users,Integer> {
    Users findUsersByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    List<Users> getAllByFilter(List<Filter> listFilter);
    UserDto mapUserToUserDto(Users users);
    Users findByEmail(String email);
    List<Users> getAll();

}
