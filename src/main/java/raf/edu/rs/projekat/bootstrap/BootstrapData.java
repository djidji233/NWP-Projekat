package raf.edu.rs.projekat.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import raf.edu.rs.projekat.model.Machine;
import raf.edu.rs.projekat.model.MachineStatus;
import raf.edu.rs.projekat.model.User;
import raf.edu.rs.projekat.repository.MachineRepository;
import raf.edu.rs.projekat.repository.UserRepository;

import java.sql.Date;
import java.util.Calendar;


@Component
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final MachineRepository machineRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public BootstrapData(UserRepository userRepository, MachineRepository machineRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.machineRepository = machineRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Loading Data...");

//        String[] FIRST_NAME_LIST = {"John", "Justine", "Ahsan", "Leja", "Jad", "Vernon", "Cara", "Eddison", "Eira", "Emily"};
//        String[] LAST_NAME_LIST = {"Booker", "Summers", "Reyes", "Rahman", "Crane", "Cairns", "Hebert", "Bradshaw", "Shannon", "Phillips"};

        User user1 = new User();
        user1.setFirstName("Admir");
        user1.setLastName("Admirovic");
        user1.setUsername("admin");
        user1.setPassword(this.passwordEncoder.encode("admin"));
        user1.setCAN_CREATE_USERS(true);
        user1.setCAN_READ_USERS(true);
        user1.setCAN_UPDATE_USERS(true);
        user1.setCAN_DELETE_USERS(true);
        user1.setCAN_SEARCH_MACHINES(true);
        user1.setCAN_START_MACHINES(true);
        user1.setCAN_STOP_MACHINES(true);
        user1.setCAN_RESTART_MACHINES(true);
        user1.setCAN_CREATE_MACHINES(true);
        user1.setCAN_DESTROY_MACHINES(true);
        this.userRepository.save(user1);

        Machine machine1 = new Machine();
        machine1.setName("machine1");
        machine1.setActive(true);
        machine1.setCreatedAt(new Date(Calendar.getInstance().getTimeInMillis()));
        machine1.setStatus(MachineStatus.STOPPED);
        machine1.setCreatedBy(user1);
        machineRepository.save(machine1);

//        User user2 = new User();
//        user2.setFirstName(FIRST_NAME_LIST[2]);
//        user2.setLastName(LAST_NAME_LIST[2]);
//        user2.setUsername("user2");
//        user2.setPassword(this.passwordEncoder.encode("user2"));
//        this.userRepository.save(user2);
//
//        User user3 = new User();
//        user3.setFirstName(FIRST_NAME_LIST[3]);
//        user3.setLastName(LAST_NAME_LIST[3]);
//        user3.setUsername("user3");
//        user3.setPassword(this.passwordEncoder.encode("user3"));
//        this.userRepository.save(user3);

        System.out.println("Data loaded!");
    }
}
