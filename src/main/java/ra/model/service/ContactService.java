package ra.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.dto.request.ContactRequest;
import ra.model.entity.Contact;

public interface ContactService extends RootService<Contact,Integer>{
    Page<Contact> findByEmailOrPhone(String email, String phone, Pageable pageable);

}
