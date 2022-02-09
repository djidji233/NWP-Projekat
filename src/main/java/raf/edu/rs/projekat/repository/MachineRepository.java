package raf.edu.rs.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.edu.rs.projekat.model.Machine;
import raf.edu.rs.projekat.model.User;

@Repository
public interface MachineRepository  extends JpaRepository<Machine, Long> {
}
