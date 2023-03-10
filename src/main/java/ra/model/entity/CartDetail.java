package ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ra.model.entity.Book;
import javax.persistence.*;

@Entity
@Data
@Table(name = "CartDetail")
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartDetailId")
    private int cartDetailId;
    @Column (name = "Price")
    private float price;
    @Column(name = "Quantity")
    private int quantity;
    @Column (name = "Status")
    private boolean statusCartDetail;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BookId")
    private Book book;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Carts carts;

}
