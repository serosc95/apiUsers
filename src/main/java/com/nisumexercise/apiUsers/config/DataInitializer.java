package com.nisumexercise.apiUsers.config;

import com.nisumexercise.apiUsers.entity.User;
import com.nisumexercise.apiUsers.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
                User adminUser = new User();

                adminUser.setName("admin");
                adminUser.setEmail("admin@gmail.com");
                adminUser.setPassword(passwordEncoder.encode("Admin123"));
                adminUser.setCreated(LocalDateTime.now());
                adminUser.setModified(LocalDateTime.now());
                adminUser.setLastLogin(LocalDateTime.now());
                adminUser.setIsactive(false);

                userRepository.save(adminUser);
                System.out.println("Admin user created");
            }
        };
    }
}
