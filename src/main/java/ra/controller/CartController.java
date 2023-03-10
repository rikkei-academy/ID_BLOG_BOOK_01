package ra.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ra.dto.request.CartConfirm;
import ra.dto.request.CartDetailRequest;
import ra.model.entity.Book;
import ra.model.entity.CartDetail;
import ra.model.entity.Carts;
import ra.model.entity.Users;
import ra.model.sendEmail.ProvideSendEmail;
import ra.model.service.BookService;
import ra.model.service.CartDetailService;
import ra.model.service.CartService;
import ra.model.service.UserService;
import ra.security.CustomUserDetails;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/v1/cart")
@AllArgsConstructor
public class CartController {
    private ProvideSendEmail provideSendEmail;
    private CartService cartService;
    private BookService bookService;
    private UserService userService;
    private CartDetailService cartDetailService;

        @GetMapping("/getPagingAndSort")
    public ResponseEntity<Map<String, Object>> getPagingAndSort(
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
            Page<Carts> carts = cartService.getAllList(pageable);
            data.put("carts", carts.getContent());
            data.put("total", carts.getSize());
            data.put("totalItems", carts.getTotalElements());
            data.put("totalPages", carts.getTotalPages());
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/addToCart")
    public ResponseEntity<?> addToCart(@RequestBody CartDetailRequest cartDetailRequest, @RequestParam String action) {
        CartDetail cartDetail = null;
        try {
            cartDetail = cartDetailService.findByBook_BookIdAndCarts_CartId(cartDetailRequest.getBookId(), cartDetailRequest.getCartId());
            if (cartDetail != null) {
                if (action.equals("Add more")) {
                    cartDetail.setQuantity(cartDetail.getQuantity() + cartDetailRequest.getQuantity());
                } else if (action.equals("Edit")) {
                        cartDetail.setQuantity(cartDetailRequest.getQuantity());
                }
                cartDetailService.saveOrUpdate(cartDetail);
                return new ResponseEntity<>(cartDetail, HttpStatus.OK);
            } else {
                cartDetail = new CartDetail();
                cartDetail.setCarts(cartService.findById(cartDetailRequest.getCartId()));
                cartDetail.setBook(bookService.getById(cartDetailRequest.getBookId()));
                cartDetail.setQuantity(cartDetailRequest.getQuantity());
                cartDetail.setPrice(cartDetailRequest.getPrice());
                cartDetail.setStatusCartDetail(true);
                CartDetail result = cartDetailService.saveOrUpdate(cartDetail);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteCartDetail")
    public ResponseEntity<?> deleteCartDetail(@RequestParam int detailId) {
        try {
            cartDetailService.deleteByCartDetailId(detailId);
            return ResponseEntity.ok().body("Delete successfully");
        } catch (Exception ex) {
            return ResponseEntity.ok().body("Delete error");
        }
    }
    @PutMapping("/checkout")
    public ResponseEntity<?>checkout(@RequestBody CartConfirm confirm){
        CustomUserDetails customUserDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Carts cart =cartService.findById(confirm.getCartId());
        try {
            Carts result= cartService.saveOrUpdate(cartService.mapCartConfirmToCart(cart,confirm));
            for (CartDetail detail : result.getCartDetails()) {
                Book book= detail.getBook();
                book.setQuantity(book.getQuantity()-detail.getQuantity());
                bookService.saveOrUpdate(book);
            }
            String subject = "Payment successfully: " + result.getCartName();
            String mess = "Thanks for payment. Thank you for your purchase. Your order is being confirmed. Delivery time will be updated after successful confirmation. Please check your email for the latest information.\n" +
                    "Detail oder:\n";
            String sDetail = "";
            float total=0;
            for (CartDetail detail : result.getCartDetails()) {
                sDetail += detail.getBook().getBookName() +  " x" + detail.getQuantity() + " " + " x" + detail.getPrice() + "vnd" + "\n";
                total+= detail.getQuantity()*detail.getPrice();
            }
            mess = mess + sDetail +
                    "-------------------------------------------------\n" +
                    "Total: " + total*result.getDiscount() + "vnd.\n" +
                    "Full name: " + result.getLastName()+" "+result.getFirstName() + ".\n" +
                    "Phone: " + result.getPhone() + ".\n" +
                    "Address: " + result.getCity()+" "+result.getState()+" "+result.getAddress()
            ;
//            provideSendEmail.sendSimpleMessage(result.getEmail(),
//                    subject, mess);
            Carts newCart = new Carts();
            newCart.setUsers((Users) userService.findById(customUserDetails.getUserId()));
             Carts pendingCart= cartService.saveOrUpdate(newCart);
              return new ResponseEntity<>(pendingCart, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
