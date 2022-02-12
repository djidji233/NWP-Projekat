package raf.edu.rs.projekat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "FIRST_NAME", length = 32, nullable = false)
    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @Column(name = "LAST_NAME", length = 32, nullable = false)
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Column(name = "USERNAME", nullable = false)
    @NotBlank(message = "Username is mandatory")
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    @NotBlank(message = "Password is mandatory")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column
    private Boolean can_read_users;

    @Column
    private Boolean can_create_users;

    @Column
    private Boolean can_update_users;

    @Column
    private Boolean can_delete_users;

    @Column
    private Boolean can_search_machines;

    @Column
    private Boolean can_start_machines;

    @Column
    private Boolean can_stop_machines;

    @Column
    private Boolean can_restart_machines;

    @Column
    private Boolean can_create_machines;

    @Column
    private Boolean can_destroy_machines;

    @OneToMany(mappedBy = "createdBy",
                cascade = CascadeType.ALL)
    private List<Machine> machines;

}
