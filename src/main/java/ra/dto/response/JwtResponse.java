package ra.dto.response;


import ra.model.entity.Carts;

import java.time.LocalDate;
import java.util.List;
public class JwtResponse {
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
    private List<String> listRoles;
    private String token;
    private Carts carts;
    private String type = "Bearer";

    public JwtResponse(int userId,String firstName, String lastName, String token, String userName, String email, String address, String state, String city,
                       String post, String phone, String avatar, int ranks, List<String> listRoles,Carts carts) {
        this.userId=userId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.token = token;
        this.userName=userName;
        this.email=email;
        this.address=address;
        this.state=state;
        this.city=city;
        this.phone=phone;
        this.post=post;
        this.avatar=avatar;
        this.ranks=ranks;
       this.listRoles=listRoles;
       this.carts=carts;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDate getBirtDate() {
        return birtDate;
    }

    public void setBirtDate(LocalDate birtDate) {
        this.birtDate = birtDate;
    }

    public boolean isStatusUser() {
        return statusUser;
    }

    public void setStatusUser(boolean statusUser) {
        this.statusUser = statusUser;
    }

    public int getRanks() {
        return ranks;
    }

    public void setRanks(int ranks) {
        this.ranks = ranks;
    }

    public List<String> getListRoles() {
        return listRoles;
    }

    public void setListRoles(List<String> listRoles) {
        this.listRoles = listRoles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Carts getCarts() {
        return carts;
    }

    public void setCarts(Carts carts) {
        this.carts = carts;
    }
}








