package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepositiry repository) {
        return args -> {
            Student moaad = new Student(
                    1,
                    "Moaad",
                    "Moaad@gmail.com",
                    LocalDate.of(1991, Month.JUNE, 2)
            );

            Student sara = new Student(
                    2,
                    "Sara",
                    "Sara@gmail.com",
                    LocalDate.of(1994, Month.AUGUST, 20)
            );
            repository.saveAll(List.of(moaad,sara));

        };
    }
}
