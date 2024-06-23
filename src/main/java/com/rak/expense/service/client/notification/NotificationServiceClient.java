package com.rak.expense.service.client.notification;

import com.rak.expense.service.client.notification.dto.NotificationCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NotificationServiceClient {

    // TODO: Read from Properties
    private String SERVICE_NOTIFICATION_BASE_URL = "http://localhost:8082/api";

    private final RestTemplate restTemplate;

    // TODO: We could have some messaging queue in place for failure recovery
    public void sendNotification(NotificationCreateDto dto) {
        restTemplate.postForObject(SERVICE_NOTIFICATION_BASE_URL + "/notifications", dto, NotificationCreateDto.class);
    }
}
