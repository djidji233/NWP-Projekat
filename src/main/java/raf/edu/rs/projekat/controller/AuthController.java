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

        return ResponseEntity.ok(new LoginResponse(jwtUtil.generateToken(loginRequest.getUsername(),u.getCan_create_users(),u.getCan_read_users(), u.getCan_update_users(), u.getCan_delete_users(), u.getCan_search_machines(), u.getCan_start_machines(), u.getCan_stop_machines(), u.getCan_restart_machines(), u.getCan_create_machines(), u.getCan_destroy_machines())));
    }

}
