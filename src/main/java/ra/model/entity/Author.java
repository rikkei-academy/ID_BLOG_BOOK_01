package ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authorId")
    private int authorId;
    @Column(name = "authorName")
    private String authorName;
    @Column(name = "authorStatus")
    private boolean authorStatus;
    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<Book> listBook = new ArrayList<>();
}
