package com.rak.expense.service.client.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NotificationServiceClient {

    // TODO: Read from Properties
    private String SERVICE_NOTIFICATION_BASE_URL = "http://localhost:8082/api/notification";

    private final RestTemplate restTemplate;

    public void sendNotification(String userId, String description) {
//        return restTemplate.postForObject(SERVICE_NOTIFICATION_BASE_URL + "/notification/category-id/" + userId + "/description/" + description, null);
    }
}
