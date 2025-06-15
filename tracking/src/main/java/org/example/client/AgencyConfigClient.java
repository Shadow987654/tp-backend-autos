package org.example.client;

import org.example.dto.response.config.AgencyConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AgencyConfigClient {

    @Value("${api.config.url}")
    private String configUrl;
    
    private final RestTemplate restTemplate;
    
    public AgencyConfigClient() {
        this.restTemplate = new RestTemplate();
    }
    
    public AgencyConfig getAgencyConfig() {
        return restTemplate.getForObject(configUrl, AgencyConfig.class);
    }
}
