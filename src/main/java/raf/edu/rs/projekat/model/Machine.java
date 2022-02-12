package raf.edu.rs.projekat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MACHINES")
public class Machine {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long machineId;

    @Column(nullable = false)
    private String name;

    @Column//(columnDefinition = "varchar(64) default 'STOPPED'")
    @Enumerated(EnumType.STRING)
    private MachineStatus status;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    //@JsonIgnore
    private User createdBy; // referenca na korisnika koji je napravio ovu masinu

    @Column
    private Date createdAt;

    @Column
    private Boolean active; // soft delete

}
