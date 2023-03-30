package demo.test.app.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author js
 */

@Configuration
public class RabbitConfig {
    @Bean
    Queue log(){
      return new Queue("LOG_REPORT",false);
    }
}
