package ra.dto.response;
import lombok.Data;
import ra.model.entity.Category;

import java.util.Date;

@Data
public class DisplayBook {
    private int bookId;
    private String bookName;
    private String descriptions;
    private String bookTitle;
    private Date datePublished;
    private String publisher;
    private float importPrice;
    private float exportPrice;
    private int sale;
    private Category category;
}
