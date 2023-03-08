package ra.dto.response;

import lombok.Data;
import ra.model.entity.Roles;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Data
public class UserDto {
    private int userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String passwords;
    private String address;
    private String state;
    private String city;
    private String post;
    private String phone;
    private String avatar;
    private LocalDate birtDate;
    private boolean statusUser;
    private int ranks;
    private Set<Roles> listRoles = new HashSet<>();
}
