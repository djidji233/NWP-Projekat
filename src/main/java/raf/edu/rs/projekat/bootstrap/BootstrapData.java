package raf.edu.rs.projekat.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import raf.edu.rs.projekat.model.User;
import raf.edu.rs.projekat.repository.UserRepository;

@Component
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public BootstrapData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Loading Data...");

        String[] FIRST_NAME_LIST = {"John", "Justine", "Ahsan", "Leja", "Jad", "Vernon", "Cara", "Eddison", "Eira", "Emily"};
        String[] LAST_NAME_LIST = {"Booker", "Summers", "Reyes", "Rahman", "Crane", "Cairns", "Hebert", "Bradshaw", "Shannon", "Phillips"};

        User user1 = new User();
        user1.setFirstName(FIRST_NAME_LIST[1]);
        user1.setLastName(LAST_NAME_LIST[1]);
        user1.setUsername("user1");
        user1.setPassword(this.passwordEncoder.encode("user1"));
        this.userRepository.save(user1);

        User user2 = new User();
        user2.setFirstName(FIRST_NAME_LIST[2]);
        user2.setLastName(LAST_NAME_LIST[2]);
        user2.setUsername("user2");
        user2.setPassword(this.passwordEncoder.encode("user2"));
        this.userRepository.save(user2);

        User user3 = new User();
        user3.setFirstName(FIRST_NAME_LIST[3]);
        user3.setLastName(LAST_NAME_LIST[3]);
        user3.setUsername("user3");
        user3.setPassword(this.passwordEncoder.encode("user3"));
        this.userRepository.save(user3);

        System.out.println("Data loaded!");
    }
}
