package ra.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Contact;
import ra.model.service.ContactService;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/v1/contact")
public class ContactController {
    @Autowired private ContactService contactService;
    @GetMapping("/getPagingAndSort")
    public ResponseEntity<Map<String, Object>> getPagingAndSortByName(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String direction,
            @RequestParam String sortBy) {
        Map<String, Object> data = new HashMap<>();
        try {
            Sort.Order order;
                if (direction.equals("asc")) {
                order = new Sort.Order(Sort.Direction.ASC, sortBy);
            } else {
                order = new Sort.Order(Sort.Direction.DESC, sortBy);
            }
            Pageable pageable = PageRequest.of(page, size, Sort.by(order));
            Page<Contact> contacts = contactService.getAllList(pageable);
            data.put("contacts", contacts.getContent());
            data.put("total", contacts.getSize());
            data.put("totalItems", contacts.getTotalElements());
            data.put("totalPages", contacts.getTotalPages());
            return new ResponseEntity<>(data, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/searchByNameOrPhone")
    public ResponseEntity<Map<String, Object>> searchByNameOrPhone(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String email,
            @RequestParam String phone){
        Map<String, Object> data = new HashMap<>();
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Contact> contacts = contactService.findByEmailOrPhone(email,phone,pageable);
            data.put("contacts", contacts.getContent());
            data.put("total", contacts.getSize());
            data.put("totalItems", contacts.getTotalElements());
            data.put("totalPages", contacts.getTotalPages());
            return new ResponseEntity<>(data, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/creatContact")
    public ResponseEntity<?>creatContact(@RequestBody Contact contact){
        try {
            Contact result=contactService.saveOrUpdate(contact);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/updateContact")
    public ResponseEntity<?> updateContact(@RequestBody Contact contact){
        try {
            Contact result = contactService.saveOrUpdate(contact);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteContact")
    public ResponseEntity<?>deleteContact(@RequestParam int contactId){
        try{
            Contact contact =contactService.findById(contactId);
            contact.setStatus(false);
            Contact result=contactService.saveOrUpdate(contact);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }
}
