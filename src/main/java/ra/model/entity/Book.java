package ra.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Data
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookId")
    private int bookId;
    @Column(name = "BookName")
    private String bookName;
    @Column(name = "Descriptions")
    private String descriptions;
    @Column(name = "BookTitle")
    private String bookTitle;
    @Column(name = "ISBN")
    private String isbn;
    @Column(name = "EditionLanguage",columnDefinition = "nvarchar(50)")
    private String editionLanguage;
    @Column(name = "DatePublished")
    private Date datePublished;
    @Column(name = "Publisher")
    private String publisher;
    @Column(name = "ImportPrice")
    private float importPrice;
    @Column(name ="ExportPrice" )
    private float exportPrice;
    @Column(name ="Quantity" )
    private int quantity;
    @Column(name ="Sale" )
    private int sale;
    @Column(name ="BookStatus" )
    private boolean bookStatus;

}