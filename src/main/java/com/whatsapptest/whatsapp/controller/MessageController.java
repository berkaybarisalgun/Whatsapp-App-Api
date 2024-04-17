package com.whatsapptest.whatsapp.controller;

import com.whatsapptest.whatsapp.service.WhatsappApiService;
import com.whatsapptest.whatsapp.dto.MessageBodyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final WhatsappApiService whatsappApiService;

    public MessageController(WhatsappApiService whatsAppApiService) {
        this.whatsappApiService = whatsAppApiService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody MessageBodyDto messageBodyDto) {
        try {
            String response = whatsappApiService.sendMessage(messageBodyDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Burada uygun bir şekilde hata yönetimi yapılmalıdır.
            return ResponseEntity.status(500).body("Message could not be sent.");
        }
    }
}


