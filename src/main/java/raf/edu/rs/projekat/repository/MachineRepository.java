package raf.edu.rs.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import raf.edu.rs.projekat.model.Machine;
import raf.edu.rs.projekat.model.MachineStatus;
import raf.edu.rs.projekat.model.User;

import java.util.Date;
import java.util.List;

@Repository
public interface MachineRepository  extends JpaRepository<Machine, Long> {
    List<Machine> findAllByName(String name);
    //List<Machine> findAllByStatusExists(List<String> status);
    List<Machine> findAllByCreatedAtAfter(Date dateFrom);
    List<Machine> findAllByCreatedAtBefore(Date dateTo);
}
