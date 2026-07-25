package com.alvin.auth.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.alvin.auth.model.User;
import com.alvin.auth.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(UserRepository repository) {

        return args -> {

            if (!repository.existsByUsername("admin")) {

                BCryptPasswordEncoder encoder =
                        new BCryptPasswordEncoder();

                User admin = new User();

                admin.setUsername("admin");
                admin.setEmail("admin@gmail.com");
                admin.setFullName("Administrator");
                admin.setPassword(
                        encoder.encode("admin123")
                );

                admin.setRole("ADMIN");
                admin.setIsActive(true);

                repository.save(admin);

                System.out.println(
                        "==================================");
                System.out.println(
                        "ADMIN DEFAULT BERHASIL DIBUAT");
                System.out.println(
                        "username : admin");
                System.out.println(
                        "password : admin123");
                System.out.println(
                        "==================================");
            }

        };
    }

}