package raf.edu.rs.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import raf.edu.rs.projekat.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

//    @Query("SELECT u FROM User u WHERE u.ime LIKE CONCAT('%',:ime,'%') AND u.prezime LIKE CONCAT('%',:prezime,'%') AND u.tip.ime LIKE CONCAT('%',:tip,'%') AND u.grupa.ime LIKE CONCAT('%',:grupa,'%')")
//    List<User> searchUsers(@Param("ime") String ime, @Param("prezime") String prezime, @Param("tip") String tip, @Param("grupa") String grupa);

//    @Modifying
//    @Query("update User u set u.balance = u.balance + :amount")
//    @Transactional
//    public void increaseBalance(@Param("amount") Integer amount);
//
//    @Query("update User u set u.loginCount = u.loginCount + :amount")
//    @Modifying
//    @Transactional
//    public void increaseLogin(@Param("amount") Integer amount);
}
