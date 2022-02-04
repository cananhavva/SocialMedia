package com.cananarslan.controller;

import com.cananarslan.dto.request.RegisterRequestDto;
import com.cananarslan.repository.entity.User;
import com.cananarslan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.cananarslan.repository.entity.User.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/dologin")
    public ResponseEntity<User> doLogin(String username, String password) {
        Optional<User> user = userService.findByUsernameAndPassword(username, password);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.ok(new User());
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequestDto dto){
        userService.saveReturnUser(builder()
                .username(dto.getEmail())
                .password(dto.getPassword())
                .build());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findall")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
}
