package ra.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Author;
import ra.model.service.AuthorService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/v1/author")
@AllArgsConstructor
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Author> getAllAuthor() {
        return authorService.findAll();
    }

    @GetMapping("/{authorId}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Author getAuthorById(@PathVariable("authorId") int authorId) {
        return authorService.findById(authorId);
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public Author createAuthor(@RequestBody Author author) {
        Author newAuthor = new Author();
        newAuthor.setAuthorName(author.getAuthorName());
        newAuthor.setAuthorStatus(true);
        return authorService.saveOrUpdate(newAuthor);
    }

    @PutMapping("/{authorId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public Author updateAuthor(@PathVariable("authorId") int authorId, @RequestBody Author author) {
        Author authorUpdate = authorService.findById(authorId);
        authorUpdate.setAuthorName(author.getAuthorName());
        authorUpdate.setAuthorStatus(author.isAuthorStatus());
        return authorService.saveOrUpdate(authorUpdate);
    }

    @DeleteMapping("/{authorId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public void deleteAuthor(@PathVariable("authorId") int authorId) {
        Author authorDelete = authorService.findById(authorId);
        authorDelete.setAuthorStatus(false);
        authorService.saveOrUpdate(authorDelete);
    }


    @GetMapping("getListAuthorPaging")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<Map<String, Object>> getPaging(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Author> pageListAuthor = authorService.listUser(pageable);
        Map<String, Object> data = new HashMap<>();
//        Dữ liệu trả về trên 1 trang
        data.put("authors",pageListAuthor.getContent());
//        Tổng bản ghi trên 1 trang
        data.put("total", pageListAuthor.getSize());
//        Tổng dữ liệu
        data.put("totalAuthors", pageListAuthor.getTotalElements());
//        Tổng số trang
        data.put("totalPage", pageListAuthor.getTotalPages());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("search")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<Map<String, Object>> getListAuthorSearchAndPaging(
            @RequestParam("searchName") String searchName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        Map<String, Object> data = new HashMap<>();
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Author> pageListAuthor = authorService.findByName(searchName,pageable);
//        Dữ liệu trả về trên 1 trang
            data.put("authors",pageListAuthor.getContent());
//        Tổng bản ghi trên 1 trang
            data.put("total", pageListAuthor.getSize());
//        Tổng dữ liệu
            data.put("totalAuthors", pageListAuthor.getTotalElements());
//        Tổng số trang
            data.put("totalPage", pageListAuthor.getTotalPages());
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(data,HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("getAuthorPagingAndSortById")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<Map<String, Object>> getPagingAndSortingById(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam("direction") String direction) {
        Sort.Order order = null;
        if (direction.equals("asc")){
            order = new Sort.Order(Sort.Direction.ASC,"authorId");
        } else if (direction.equals("des")){
            order = new Sort.Order(Sort.Direction.DESC,"authorId");
        }
        Pageable pageable = PageRequest.of(page, size,Sort.by(order));
        Page<Author> pageAuthor = authorService.listUser(pageable);
        Map<String, Object> data = new HashMap<>();
//        Dữ liệu trả về trên 1 trang
        data.put("authors",pageAuthor.getContent());
//        Tổng bản ghi trên 1 trang
        data.put("total", pageAuthor.getSize());
//        Tổng dữ liệu
        data.put("totalAuthors", pageAuthor.getTotalElements());
//        Tổng số trang
        data.put("totalPage", pageAuthor.getTotalPages());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("getPagingAndSortByName")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<Map<String, Object>> getPagingAndSortingByName(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam("direction") String direction) {
        Sort.Order order = null;
        if (direction.equals("asc")){
            order = new Sort.Order(Sort.Direction.ASC,"authorId");
        } else if (direction.equals("des")){
            order = new Sort.Order(Sort.Direction.DESC,"authorId");
        }
        Pageable pageable = PageRequest.of(page, size,Sort.by(order));
        Page<Author> pageAuthor = authorService.listUser(pageable);
        Map<String, Object> data = new HashMap<>();
//        Dữ liệu trả về trên 1 trang
        data.put("authors",pageAuthor.getContent());
//        Tổng bản ghi trên 1 trang
        data.put("total", pageAuthor.getSize());
//        Tổng dữ liệu
        data.put("totalAuthors", pageAuthor.getTotalElements());
//        Tổng số trang
        data.put("totalPage", pageAuthor.getTotalPages());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
