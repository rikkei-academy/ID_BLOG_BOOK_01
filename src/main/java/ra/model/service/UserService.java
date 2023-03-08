package ra.model.service;

import ra.model.entity.Users;


public interface UserService extends RootService<Users,Integer> {
    Users findUsersByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);

}
