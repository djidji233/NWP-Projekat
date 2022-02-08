package raf.edu.rs.projekat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "FIRST_NAME", length = 32)
    //@NotBlank(message = "First name is mandatory")
    private String firstName;

    @Column(name = "LAST_NAME", length = 32)
    //@NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Column(name = "USERNAME")
    @NotBlank(message = "Username is mandatory")
    private String username;

    @Column(name = "PASSWORD")
    @NotBlank(message = "Password is mandatory")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;



}
