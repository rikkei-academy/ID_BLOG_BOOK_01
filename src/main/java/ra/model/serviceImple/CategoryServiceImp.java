package ra.model.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.model.entity.Category;
import ra.model.repository.CategoryRepository;
import ra.model.service.CategoryService;

import java.util.Collections;
import java.util.List;
@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(int catalogId) {
        return categoryRepository.findById(catalogId).get();
    }

    @Override
    public void deleteById(int catalogId) {
        categoryRepository.deleteById(catalogId);

    }

    @Override
    public Category saveOrUpdate(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> searchName(String catalogName) {
        return categoryRepository.findByCatalogNameContaining(catalogName);
    }

    @Override
    public List<Category> sortByName(String direction) {
        if (direction.equals("asc")) {
            return categoryRepository.findAll(Sort.by("catalogName").ascending());
        } else {
            return categoryRepository.findAll(Sort.by("catalogName").descending());
        }
    }

    @Override
    public Page<Category> getPagging(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
}

