package com.whatsapptest.whatsapp.service;

import com.whatsapptest.whatsapp.dto.MessageBodyDto;
import com.whatsapptest.whatsapp.dto.RequestMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class WhatsappApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WhatsappApiService.class);

    private final String baseApiUrl;
    private final String token;
    private final RestTemplate restTemplate;


    public WhatsappApiService(@Value("${whatsapp.api.url}") String baseApiUrl,
                              @Value("${whatsapp.api.token}") String token) {
        this.baseApiUrl = baseApiUrl;
        this.token = token;
        this.restTemplate = new RestTemplate();
    }

    public String sendMessage(MessageBodyDto message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);


        RequestMessage requestMessage = RequestMessage.builder()
                .messagingProduct("whatsapp")
                .to(message.getNumber())
                .text(message.getText())
                .build();

        HttpEntity<RequestMessage> request = new HttpEntity<>(requestMessage, headers);

        try {
            return restTemplate.postForObject(baseApiUrl + "/messages", request, String.class);
        } catch (Exception e) {
            LOGGER.error("error: {}", e.getMessage(), e);
            throw new RuntimeException("failed to send message", e);
        }
    }
}
