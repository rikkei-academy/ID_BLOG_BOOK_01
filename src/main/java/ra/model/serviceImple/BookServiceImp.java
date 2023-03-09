package ra.model.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.model.entity.Book;
import ra.model.repository.BookRepository;
import ra.model.service.BookService;

import java.util.List;
@Service
public class BookServiceImp implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book getById(int bookId) {
        return bookRepository.findById(bookId).get();
    }

    @Override
    public void deleteById(int bookId) {
        bookRepository.deleteById(bookId);

    }

    @Override
    public Book saveOrUpdate(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> searchName(String bookName) {
        return bookRepository.findByBookNameContaining(bookName);
    }

    @Override
    public List<Book> sortByName(String direction) {
        if (direction.equals("asc")) {
            return bookRepository.findAll(Sort.by("bookName").ascending());
        } else {
            return bookRepository.findAll(Sort.by("bookName").descending());
        }
    }

    @Override
    public Page<Book> getPagging(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
}
