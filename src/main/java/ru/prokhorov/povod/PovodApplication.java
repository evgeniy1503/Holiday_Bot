package ru.prokhorov.povod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class PovodApplication {

    public static void main(String[] args) {
        SpringApplication.run(PovodApplication.class, args);
    }

}
