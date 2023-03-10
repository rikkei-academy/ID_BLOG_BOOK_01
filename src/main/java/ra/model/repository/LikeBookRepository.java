package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.LikeBook;

@Repository
public interface LikeBookRepository extends JpaRepository<LikeBook,Integer> {
    LikeBook findByBook_BookIdAndUsers_UserId(int bookId, int useId);

    int countByBook_BookIdAndLikeBookStatus(int bookId, boolean status);
}
