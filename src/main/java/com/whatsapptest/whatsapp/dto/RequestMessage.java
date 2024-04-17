package com.whatsapptest.whatsapp.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class RequestMessage {
    private String messagingProduct;
    private String to;
    private String text;
}
