package ra.model.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.dto.request.ContactRequest;
import ra.model.entity.Contact;
import ra.model.repository.ContactRepository;
import ra.model.service.ContactService;
@Service
public class ContactServiceImple implements ContactService {
    @Autowired private ContactRepository contactRepo;
    @Override
    public Page<Contact> getAllList(Pageable pageable) {
        return contactRepo.findAll(pageable);
    }

    @Override
    public Contact saveOrUpdate(Contact contact) {
        return contactRepo.save(contact);
    }

    @Override
    public Contact findById(Integer id) {
        return contactRepo.findById(id).get();
    }

    @Override
    public Page<Contact> findByName(String name ,Pageable pageable) {
        return null;
    }
    @Override
    public Page<Contact> findByEmailOrPhone(String email,String phone,Pageable pageable ){
        return contactRepo.findByEmailOrPhone(email, phone, pageable);
    }


}
