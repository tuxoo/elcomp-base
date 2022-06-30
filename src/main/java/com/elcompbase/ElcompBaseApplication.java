package com.elcompbase;

import com.elcompbase.config.property.ApplicationProperty;
import com.elcompbase.config.property.CacheProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({ApplicationProperty.class, CacheProperty.class})
@SpringBootApplication
public class ElcompBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElcompBaseApplication.class, args);
	}

}
