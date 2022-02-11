package raf.edu.rs.projekat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync(proxyTargetClass = true)
public class ProjekatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjekatApplication.class, args);
    }

}
