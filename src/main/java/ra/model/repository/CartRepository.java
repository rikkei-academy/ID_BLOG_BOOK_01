package ra.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Carts;
@Repository
public interface CartRepository extends JpaRepository<Carts,Integer>{
    Page<Carts> findByCartNameContaining(String name,Pageable pageable);

}
