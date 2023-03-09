package ra.model.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.model.entity.Author;
import ra.model.repository.AuthorRepository;
import ra.model.service.AuthorService;

import java.util.List;

@Service
public class AuthorServiceImple implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public Page<Author> listUser(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Author saveOrUpdate(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author findById(Integer id) {
        return authorRepository.findById(id).get();
    }

    @Override
    public Page<Author> findByName(String name, Pageable pageable) {
        return authorRepository.findByAuthorNameContaining(name,pageable);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }


}
