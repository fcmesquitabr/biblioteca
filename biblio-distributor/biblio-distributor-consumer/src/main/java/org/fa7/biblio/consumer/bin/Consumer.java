package org.fa7.biblio.consumer.bin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableConfigurationProperties
@ComponentScan("org.fa7.biblio.consumer")
@EntityScan(basePackages = {"org.fa7.biblio.consumer.bean"})
@EnableJpaRepositories(basePackages = {"org.fa7.biblio.consumer.repository"})
@SpringBootApplication
public class Consumer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Consumer.class, args);
    }

}
