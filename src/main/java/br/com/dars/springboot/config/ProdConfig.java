package br.com.dars.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {

//    @Autowired
//    private DBServiceImpl dbService;
//
//    @Bean
//    public boolean instantiateDataBase() throws ParseException {
//        dbService.instantiateTestDatabase();
//        return true;
//    }
}
