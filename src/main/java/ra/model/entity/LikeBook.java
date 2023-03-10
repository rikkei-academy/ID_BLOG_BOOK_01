package ra.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "likebook")
public class LikeBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likeBookId")
    private int likeBookId;
    @Column(name = "likeBookStatus")
    private boolean likeBookStatus;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "UserId")
    private Users users;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId", referencedColumnName = "BookId")
    private Book book;
}
