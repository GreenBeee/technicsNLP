package com.lnu.funcprogramming;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author Oleh Marych
 */
@Configuration
public class ApplicationConfiguration {

    @Value("${apiai.client.token}")
    private String apiAiClientToken;

    @Bean
    public RestTemplate restTemplate() {
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Arrays.asList(new ByteArrayHttpMessageConverter(), new StringHttpMessageConverter(), new ResourceHttpMessageConverter(), new SourceHttpMessageConverter<>(), new AllEncompassingFormHttpMessageConverter(),
                new MappingJackson2HttpMessageConverter(jacksonObjectMapper())));
        return restTemplate;
    }

    @Bean
    public ObjectMapper jacksonObjectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean
    public AIDataService getAiDataService() {
        AIConfiguration aiConfiguration = new AIConfiguration(apiAiClientToken);
        return new AIDataService(aiConfiguration);
    }
}
