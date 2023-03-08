package ra.payload.respone;


import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
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
    private List<String> listRoles;

    public JwtResponse(String token, String userName, String firstName, String lastName, String email, String address, String state, String city, String post, String phone, String avatar, LocalDate birtDate, boolean statusUser, int ranks, List<String> listRoles) {
        this.token = token;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.state = state;
        this.city = city;
        this.post = post;
        this.phone = phone;
        this.avatar = avatar;
        this.birtDate = birtDate;
        this.statusUser = statusUser;
        this.ranks = ranks;
        this.listRoles = listRoles;
    }
}
