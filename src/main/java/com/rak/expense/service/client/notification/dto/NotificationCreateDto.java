package com.rak.expense.service.client.notification.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationCreateDto {

    private String userId;
    private String message;

}
