package ra.model.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.model.entity.LikeBook;
import ra.model.repository.LikeBookRepository;
import ra.model.service.LikeBookService;

import java.util.List;

@Service
public class LikeBookServiceImple implements LikeBookService {
    @Autowired
    private LikeBookRepository likeBookRepository;


    @Override
    public Page<LikeBook> getAllList(Pageable pageable) {
        return null;
    }

    @Override
    public LikeBook saveOrUpdate(LikeBook likeBook) {
        return likeBookRepository.save(likeBook);
    }

    @Override
    public LikeBook findById(Integer id) {
        return null;
    }

    @Override
    public Page<LikeBook> findByName(String name, Pageable pageable) {
        return null;
    }

    @Override
    public List<LikeBook> findAll() {
        return likeBookRepository.findAll();
    }

    @Override
    public LikeBook findByBook_BookIdAndUsers_UserId(int bookId, int userId) {
        return likeBookRepository.findByBook_BookIdAndUsers_UserId(bookId, userId);
    }

    @Override
    public int countByBook_BookIdAndLikeBookStatus(int bookId, boolean status) {
        return likeBookRepository.countByBook_BookIdAndLikeBookStatus(bookId, status);
    }


}
