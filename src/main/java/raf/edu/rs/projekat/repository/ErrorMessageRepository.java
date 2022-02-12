package raf.edu.rs.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.edu.rs.projekat.model.ErrorMessage;
import raf.edu.rs.projekat.model.Machine;

@Repository
public interface ErrorMessageRepository extends JpaRepository<ErrorMessage, Long> {
}
