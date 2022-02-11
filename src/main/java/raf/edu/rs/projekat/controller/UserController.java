package raf.edu.rs.projekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import raf.edu.rs.projekat.model.User;
import raf.edu.rs.projekat.service.UserService;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers(@RequestHeader(name = "Authorization") String token) throws UnsupportedEncodingException {
        return userService.findAll(token);
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserById(@PathVariable("userId") Long userId,@RequestHeader(name = "Authorization") String token) throws UnsupportedEncodingException {
        Optional<User> optionalUser = userService.findById(userId,token);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody User user,@RequestHeader(name = "Authorization") String token) throws UnsupportedEncodingException {
        return userService.create(user,token);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User user,@RequestHeader(name = "Authorization") String token) throws UnsupportedEncodingException{
        return userService.update(user,token);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long id,@RequestHeader(name = "Authorization") String token) throws UnsupportedEncodingException {
        userService.deleteById(id,token);
        return ResponseEntity.ok().build();
    }


//    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public User create(@Valid @RequestBody User user) {
//        return this.userService.create(user);
//    }
//
//    @GetMapping
//    public Page<User> all(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
//        return this.userService.paginate(page, size);
//    }

//    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
//    public User me() {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        return this.userService.findByUsername(username);
//    }


}
