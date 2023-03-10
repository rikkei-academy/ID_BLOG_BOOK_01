package ra.model.service;

import ra.model.entity.LikeBook;

public interface LikeBookService extends RootService<LikeBook,Integer> {
    LikeBook findByBook_BookIdAndUsers_UserId(int bookId, int userId);
    int countByBook_BookIdAndLikeBookStatus(int bookId, boolean status);
}
