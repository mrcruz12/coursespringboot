package br.com.dars.springboot.config;

import br.com.dars.springboot.services.impl.DBServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBServiceImpl dbService;

    @Value("spring.jpa.hibernate.ddl-auto")
    private String strategy;

    @Bean
    public boolean instantiateDataBase() throws ParseException {
        if ("create".equals(strategy)) {
            dbService.instantiateTestDatabase();
            return true;

        }
        return false;
    }
}
