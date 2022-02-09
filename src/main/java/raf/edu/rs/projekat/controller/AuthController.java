package raf.edu.rs.projekat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import raf.edu.rs.projekat.model.User;
import raf.edu.rs.projekat.requests.LoginRequest;
import raf.edu.rs.projekat.responses.LoginResponse;
import raf.edu.rs.projekat.service.UserService;
import raf.edu.rs.projekat.util.JwtUtil;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (Exception   e){
            e.printStackTrace();
            return ResponseEntity.status(401).build();
        }
        //this.userService.loggedIn(loginRequest.getUsername());
        User u = userService.findByUsername(loginRequest.getUsername());

        return ResponseEntity.ok(new LoginResponse(jwtUtil.generateToken(loginRequest.getUsername(),u.getCAN_CREATE_USERS(),u.getCAN_READ_USERS(), u.getCAN_UPDATE_USERS(), u.getCAN_DELETE_USERS(), u.getCAN_SEARCH_MACHINES(), u.getCAN_START_MACHINES(), u.getCAN_STOP_MACHINES(), u.getCAN_RESTART_MACHINES(), u.getCAN_CREATE_MACHINES(), u.getCAN_DESTROY_MACHINES())));
    }

}
