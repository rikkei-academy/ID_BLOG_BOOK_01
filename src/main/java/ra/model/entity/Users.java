package ra.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private int userId;
    @Column(name = "UserName")
    private String userName;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "Email")
    private String email;
    @Column(name = "Passwords")
    private String passwords;
    @Column(name = "Address")
    private String address;
    @Column(name = "State")
    private String state;
    @Column(name = "City")
    private String city;
    @Column(name = "Post")
    private String post;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Avatar")
    private String avatar;
    @Column(name = "BirtDate")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birtDate;
    @Column(name = "StatusUser")
    private boolean statusUser;
    @Column(name = "Ranks")
    private int ranks;
    //tạo bảng user_role
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "user_role", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Roles> listRoles = new HashSet<>();

    //tạo bảng wishList
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "wishlist",joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "bookId"))
    private Set<Book> wishList = new HashSet<>();
    //tạo bảng Star
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "star",joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "bookId"))
    private Set<Book> star = new HashSet<>();

    @OneToMany(mappedBy = "users")
    @JsonIgnore
    private List<LikeBook> listLikeBook = new ArrayList<>();
    @OneToMany(mappedBy = "users")
    @JsonIgnore
    List<Carts> cartList = new ArrayList<>();

}
