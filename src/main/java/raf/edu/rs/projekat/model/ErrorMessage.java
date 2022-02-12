package raf.edu.rs.projekat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ERROR_MESSAGE")
public class ErrorMessage {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long errorId;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Long machineId;

    @Column(nullable = false)
    private String methodName;

    @Column(nullable = false)
    private String errorMessage;

}
