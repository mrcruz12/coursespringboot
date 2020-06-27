package br.com.dars.springboot.config;

import br.com.dars.springboot.services.EmailService;
import br.com.dars.springboot.services.impl.DBServiceImpl;
import br.com.dars.springboot.services.impl.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBServiceImpl dbService;

    @Bean
    public boolean instantiateDataBase() throws ParseException {
        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }

}
