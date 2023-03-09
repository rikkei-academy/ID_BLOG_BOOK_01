package ra.dto.response;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import ra.model.entity.Roles;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
public class UserDto{
    private int userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String state;
    private String city;
    private String post;
    private String phone;
    private String avatar;
    private LocalDate birtDate;
    private boolean statusUser;
    private int ranks;
    private List<String> listRoles ;

}
