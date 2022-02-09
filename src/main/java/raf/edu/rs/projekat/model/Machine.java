package raf.edu.rs.projekat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "MACHINES")
public class Machine {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long machineId;

    @Column(nullable = false)
    private String name;

    @Column
    private MachineStatus status;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @JsonIgnore
    private User createdBy; // referenca na korisnika koji je napravio ovu masinu

    @Column
    private Date createdAt;

    @Column
    private Boolean active; // soft delete

}
