package org.fa7.biblio.producer.bin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@EnableConfigurationProperties
@ComponentScan("org.fa7.biblio.producer")
@SpringBootApplication
public class Producer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Producer.class, args);
    }

}
