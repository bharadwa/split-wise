package org.split.splitwise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SplitWiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SplitWiseApplication.class, args);
    }

}
