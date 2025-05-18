package lab.mongoDb.springboot_mongodb.configuration;

import lombok.extern.log4j.Log4j2;
import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class FakerConfig {

    @Bean
    public Faker getFakerInstance() {
        log.info("******************creating faker singleton*******************");
        return new Faker();
    }
}
