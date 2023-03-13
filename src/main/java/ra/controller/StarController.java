package ra.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ra.dto.request.StarRequest;
import ra.model.entity.*;
import ra.model.service.BookService;
import ra.model.service.CartDetailService;
import ra.model.service.StarService;
import ra.model.service.UserService;
import ra.security.CustomUserDetails;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/v1/star")
@AllArgsConstructor
public class StarController {
    @Autowired
    private StarService starService;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private CartDetailService cartDetailService;

    @PostMapping("/creat_new_star")
    public ResponseEntity<?> creatNewStar(@RequestBody StarRequest request) {
        try {
            Star star = starService.mapRequestToStar(request);
            CustomUserDetails usersChangePass = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Users users = userService.findUsersByUserName(usersChangePass.getUsername());
            Book book = bookService.getById(request.getBookId());
            List<Users> usersList = new ArrayList<>();
            List<CartDetail> listCartDetail = cartDetailService.findByBook_BookId(book.getBookId());
            for (CartDetail cd : listCartDetail) {
                usersList.add(cd.getCarts().getUsers());
            }
            boolean check =false;
            for (Users user : usersList) {
                if (user.getUserId()==users.getUserId()){
                    check =true;
                }
            }
            Star starNew = new Star();
            if (check){
                starNew.setStarPoint(request.getStarPoint());
                starNew.setUsers(users);
                starNew.setBook(book);
                starService.saveOrUpdate(starNew);
            }
            Star result=  starService.saveOrUpdate(starNew);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_best_star")
    public ResponseEntity<?> getBestStarByProductId() {
        return null;
    }

}
