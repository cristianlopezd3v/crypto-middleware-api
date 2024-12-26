package crypto.middleware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@Configuration
public class CryptoMiddlewareAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoMiddlewareAPIApplication.class, args);
	}

}
