package raf.edu.rs.projekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import raf.edu.rs.projekat.model.User;
import raf.edu.rs.projekat.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IService<User, Long>, UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;

        this.userRepository = userRepository;
    }

    public User create(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public <S extends User> S save(S var1) {
        var1.setPassword(this.passwordEncoder.encode(var1.getPassword()));
        return userRepository.save(var1);
    }

    @Override
    public Optional<User> findById(Long var1) {
        return userRepository.findById(var1);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long var1) {
        userRepository.deleteById(var1);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User myUser = this.findByUsername(username);
        if (myUser == null) {
            throw new UsernameNotFoundException("User name " + username + " not found");
        }

        return new org.springframework.security.core.userdetails.User(myUser.getUsername(), myUser.getPassword(), new ArrayList<>());
    }


//    public Page<User> paginate(Integer page, Integer size) {
//        return this.userRepository.findAll(PageRequest.of(page, size, Sort.by("salary").descending()));
//    }

    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

//    public void loggedIn(String username) {
//        User user = this.userRepository.findByUsername(username);
//        Integer loginCount = user.getLoginCount();
//        try {
//            Thread.sleep(10000);
//
//            user.setLoginCount(loginCount + 1);
//            this.userRepository.save(user);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ObjectOptimisticLockingFailureException exception) {
//            this.loggedIn(username);
//        }
//    }


//    @Scheduled(fixedDelay = 1000)
//    public void scheduleFixedDelayTask() throws InterruptedException {
//        System.out.println(
//                "Fixed delay task - " + System.currentTimeMillis() / 1000);
//        Thread.sleep(2000);
//    }


//    @Scheduled(fixedRate = 3000)
//    public void scheduleFixedRateTaskAsync() throws InterruptedException {
//        System.out.println(
//                "Fixed rate task async - " + System.currentTimeMillis() / 1000);
//        Thread.sleep(5000);
//        System.out.println(
//                "Fixed rate task async - finished " + System.currentTimeMillis() / 1000);
//    }

//    @Scheduled(cron = "0 * * * * *", zone = "Europe/Belgrade")
//    public void increaseUserBalance() {
//        System.out.println("Increasing balance...");
//        this.userRepository.increaseBalance(1);
////        List<User> users = this.userRepository.findAll();
////        for (User user : users) {
////            user.setBalance(user.getBalance() + 1);
////            this.userRepository.save(user);
////        }
//    }
//
//    public User hire(String username, Integer salary) {
//        User user = this.userRepository.findByUsername(username);
//        user.setSalary(salary);
//        this.userRepository.save(user);
//
//        CronTrigger cronTrigger = new CronTrigger("0 * * * * *"); // "0 0 0 25 * *"
//        this.taskScheduler.schedule(() -> {
//            System.out.println("Getting salary...");
//            this.userRepository.increaseBalance(salary);
//        }, cronTrigger);
//
//        return user;
//    }
}
