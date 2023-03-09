package ra.dto.request;
import lombok.Data;

import java.time.LocalDate;
@Data
public class UserUpdate {
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
}
