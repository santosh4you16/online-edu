package com.onlineedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
//testing app
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class OnlineEduApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineEduApplication.class, args);
    }

}
