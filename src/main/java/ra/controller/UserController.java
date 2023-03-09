package ra.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ra.dto.request.UserLogin;
import ra.dto.response.JwtResponse;
import ra.dto.response.UserDto;
import ra.jwt.JwtTokenProvider;
import ra.model.entity.Filter;
import ra.model.entity.Users;
import ra.model.service.UserService;
import ra.security.CustomUserDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;
    private UserService userService;

    @GetMapping("/getAllByFilter")
    public ResponseEntity<?> getAllByFilter(@RequestBody List<Filter> list) {
        List<Users> usersList = userService.getAllByFilter(list);
        List<UserDto> userDtos = new ArrayList<>();
        for (Users u : usersList) {
                UserDto userDto= userService.mapUserToUserDto(u);
                userDtos.add(userDto);
        }
        return ResponseEntity.ok().body(userDtos);
    }

    @GetMapping("/getPagingAndSort")
    public ResponseEntity<Map<String, Object>> getPagingAndSortByName(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String direction,
            @RequestParam String sortBy) {
        Sort.Order order;
        if (direction.equals("asc")) {
            order = new Sort.Order(Sort.Direction.ASC, sortBy);
        } else {
            order = new Sort.Order(Sort.Direction.DESC, sortBy);
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));
        Page<Users> users = userService.listUser(pageable);
        Page<UserDto> userDtos = users.map(users1 -> {
            UserDto userDto = userService.mapUserToUserDto(users1);
            return userDto;
        });
        Map<String, Object> data = new HashMap<>();
        data.put("usersDto", userDtos.getContent());
        data.put("total", userDtos.getSize());
        data.put("totalItems", userDtos.getTotalElements());
        data.put("totalPages", userDtos.getTotalPages());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> loginUser(@RequestBody UserLogin userLogin) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.getUserName(), userLogin.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails customUserDetail = (CustomUserDetails) authentication.getPrincipal();
        //Sinh JWT tra ve client
        String jwt = tokenProvider.generateToken(customUserDetail);
        System.out.println(jwt);
        //Lay cac quyen cua user
        List<String> listRoles = customUserDetail.getAuthorities().stream()
                .map(item -> item.getAuthority()).collect(Collectors.toList());
        JwtResponse response = new JwtResponse(customUserDetail.getUserId(), customUserDetail.getFirstName(), customUserDetail.getLastName(), jwt, customUserDetail.getUsername(), customUserDetail.getEmail(),
                customUserDetail.getAddress(), customUserDetail.getState(), customUserDetail.getCity(), customUserDetail.getPost(), customUserDetail.getPhone(), customUserDetail.getAvatar(), customUserDetail.getRanks(), listRoles);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/searchByUserName")
    public ResponseEntity<Map<String, Object>> searchByUserName(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String userName) {
        Map<String, Object> data = new HashMap<>();
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Users> users = userService.findByName(userName, pageable);
            Page<UserDto> userDtos = users.map(users1 -> {
                UserDto userDto = userService.mapUserToUserDto(users1);
                return userDto;
            });
            data.put("usersDto", userDtos.getContent());
            data.put("total", userDtos.getSize());
            data.put("totalItems", userDtos.getTotalElements());
            data.put("totalPages", userDtos.getTotalPages());
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/blockUser")
    public ResponseEntity<?> blockUser(Integer userId) {
        try {
            CustomUserDetails customUserDetail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Users users = (Users) userService.findById(userId);
            if (customUserDetail.getAuthorities().size() > users.getListRoles().size()) {
                users.setStatusUser(false);
                userService.saveOrUpdate(users);
            }
            return ResponseEntity.ok("BlockUser successfully");
        } catch (Exception e) {
            return new ResponseEntity<>("BlockUser Error", HttpStatus.BAD_REQUEST);
        }
    }
}
