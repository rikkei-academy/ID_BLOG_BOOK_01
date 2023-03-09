package ra.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Contact;
@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {
    Page<Contact> findByEmailOrPhone(String email, String phone, Pageable pageable);
}
