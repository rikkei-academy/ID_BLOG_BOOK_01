package ra.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface RootService<T,V> {
    Page<T> getAllList(Pageable pageable);
    T saveOrUpdate(T t);
    T findById(V id);
    Page<T> findByName(String name, Pageable pageable);
}
