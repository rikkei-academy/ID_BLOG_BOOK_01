package ra.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contactId")
    private int contactId;
    @Column(name = "Address")
    private String address;
    @Column(name="Email")
    private String email;
    @Column (name = "Phone")
    private String phone;
    @Column (name = "status")
    private boolean status;
}
