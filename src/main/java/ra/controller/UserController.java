package ra.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.model.service.UserService;

import javax.persistence.Column;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;



}
