package com.company.dtoRequest;

import com.company.enums.NotificationType;
import lombok.Data;

@Data
public class SubscriptionDTO {
    private Integer channelId;
    private String typ;
    private String status;
}
