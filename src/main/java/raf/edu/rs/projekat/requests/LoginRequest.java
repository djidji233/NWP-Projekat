package raf.edu.rs.projekat.requests;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
