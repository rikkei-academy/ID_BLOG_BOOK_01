package ra.model.repository;

import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.model.entity.Book;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findByBookNameContaining(String bookName);
    @Query(value = "select * from book b join wishlist w on b.bookId = w.bookId where w.userId = :uId",nativeQuery = true)
    List<Book> getAllWishList(@Param("uId") int userId);
}
