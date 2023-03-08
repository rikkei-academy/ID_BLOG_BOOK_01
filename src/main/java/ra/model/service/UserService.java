package ra.model.service;

import ra.dto.response.UserDto;
import ra.model.entity.Users;

import java.util.List;


public interface UserService extends RootService<UserDto,Integer> {
    Users findUsersByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    List<UserDto> getAllByFilter(List<String> listFilter);
}
