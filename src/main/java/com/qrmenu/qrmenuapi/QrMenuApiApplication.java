package com.qrmenu.qrmenuapi;

import com.qrmenu.data.DeskRepository;
import com.qrmenu.domain.Desk;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan("com.qrmenu.domain")
public class QrMenuApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(QrMenuApiApplication.class, args);
    }

    /*@Bean
    public CommandLineRunner dataLoader(DeskRepository repo){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                repo.save(new Desk());
            }
        };
    }
    */
}
