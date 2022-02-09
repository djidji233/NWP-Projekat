package raf.edu.rs.projekat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

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

    @Column(name = "CAN_READ_USERS")
    private Boolean CAN_READ_USERS;

    @Column(name = "CAN_CREATE_USERS")
    private Boolean CAN_CREATE_USERS;

    @Column(name = "CAN_UPDATE_USERS")
    private Boolean CAN_UPDATE_USERS;

    @Column(name = "CAN_DELETE_USERS")
    private Boolean CAN_DELETE_USERS;

    @Column(name = "CAN_SEARCH_MACHINES")
    private Boolean CAN_SEARCH_MACHINES;

    @Column(name = "CAN_START_MACHINES")
    private Boolean CAN_START_MACHINES;

    @Column(name = "CAN_STOP_MACHINES")
    private Boolean CAN_STOP_MACHINES;

    @Column(name = "CAN_RESTART_MACHINES")
    private Boolean CAN_RESTART_MACHINES;

    @Column(name = "CAN_CREATE_MACHINES")
    private Boolean CAN_CREATE_MACHINES;

    @Column(name = "CAN_DESTROY_MACHINES")
    private Boolean CAN_DESTROY_MACHINES;

//    @OneToMany
//    private ArrayList<Machine> machines;

}
