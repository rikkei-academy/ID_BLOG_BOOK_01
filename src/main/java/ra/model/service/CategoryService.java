package ra.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category getById(int catalogId);
    void deleteById(int catalogId);
    Category saveOrUpdate(Category category);
    List<Category> searchName(String studentName);
    List<Category> sortByName(String direction);
    Page<Category> getPagging(Pageable pageable);
}
