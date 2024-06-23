package com.rak.expense.service.client.notification.mapper;

import com.rak.expense.service.client.notification.dto.NotificationCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationDtoMapper {

    public NotificationCreateDto map(String userId, String message) {

        return NotificationCreateDto.builder()
                .userId(userId)
                .message(message)
                .build();
    }
}
