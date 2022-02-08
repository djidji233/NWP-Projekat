package raf.edu.rs.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import raf.edu.rs.projekat.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);

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
