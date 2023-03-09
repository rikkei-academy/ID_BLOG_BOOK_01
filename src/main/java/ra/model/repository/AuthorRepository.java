package ra.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Author;


@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    Page<Author> findByAuthorNameContaining(String name, Pageable pageable);
}
