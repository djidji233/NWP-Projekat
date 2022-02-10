package raf.edu.rs.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import raf.edu.rs.projekat.model.Machine;
import raf.edu.rs.projekat.model.MachineStatus;
import raf.edu.rs.projekat.model.User;

import java.sql.Date;
import java.util.List;

@Repository
public interface MachineRepository  extends JpaRepository<Machine, Long> {
//    @Query("SELECT u FROM User u WHERE u.ime LIKE CONCAT('%',:ime,'%') AND u.prezime LIKE CONCAT('%',:prezime,'%') AND u.tip.ime LIKE CONCAT('%',:tip,'%') AND u.grupa.ime LIKE CONCAT('%',:grupa,'%')")
//    List<User> searchUsers(@Param("ime") String ime, @Param("prezime") String prezime, @Param("tip") String tip, @Param("grupa") String grupa);

    @Query("SELECT m FROM Machine m WHERE LOWER(m.name) LIKE CONCAT('%',LOWER(:name),'%') OR m.status IN :status OR m.createdAt >= :dateFrom OR m.createdAt < :dateTo")
    List<Machine> search(@Param("name") String name, @Param("status") List<String> status, @Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);
}
