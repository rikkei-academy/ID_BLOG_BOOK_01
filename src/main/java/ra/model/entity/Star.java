package ra.model.entity;

import lombok.Data;

import javax.persistence.*;

@Table
@Entity
@Data
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "starId")
    private int starId;
    @Column (name ="starPoint")
    private int starPoint;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private Users users;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId")
    private Book book;
}
