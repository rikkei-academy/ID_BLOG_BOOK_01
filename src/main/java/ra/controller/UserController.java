package ra.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.dto.response.UserDto;
import ra.model.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;
        @GetMapping("/getAllByFilter")
    public ResponseEntity<?>getAllByFilter(@RequestBody List<String> listFilter) {
            List<UserDto> userDtoList = userService.getAllByFilter(listFilter);
            return ResponseEntity.ok().body(userDtoList);
        }
    @GetMapping("/getPaggingAndSortByName")
    public ResponseEntity<Map<String,Object>> getPaggingAndSortByName(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam String direction){
        Sort.Order order;
        if (direction.equals("asc")){
            order=new Sort.Order(Sort.Direction.ASC,"catalogName");
        }else{
            order=new Sort.Order(Sort.Direction.DESC,"catalogName");
        }
        Pageable pageable = PageRequest.of(page,size,Sort.by(order));
        Page<UserDto> users = userService.listUser(pageable);
        Map<String,Object> data = new HashMap<>();
        data.put("catalogs",users.getContent());
        data.put("total",users.getSize());
        data.put("totalItems",users.getTotalElements());
        data.put("totalPages",users.getTotalPages());
        return  new ResponseEntity<>(data, HttpStatus.OK);
    }

}
