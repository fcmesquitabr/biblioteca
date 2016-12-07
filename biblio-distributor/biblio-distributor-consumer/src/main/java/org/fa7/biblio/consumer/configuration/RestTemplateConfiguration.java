package org.fa7.biblio.consumer.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "biblio.rest.connection")
    public HttpComponentsClientHttpRequestFactory customHttpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory();
    }

    @Bean
    public RestTemplate biblioRestTemplate() {
        return new RestTemplate(customHttpRequestFactory());
    }
}
