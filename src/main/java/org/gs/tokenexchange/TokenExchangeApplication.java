package org.gs.tokenexchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TokenExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TokenExchangeApplication.class, args);
//		new SpringApplicationBuilder()
//				.parent(VaultConfiguration.class)
//				.child(TokenExchangeApplication.class)
//				.run(args);
    }

}
