package com.company.entity;

import com.company.enums.NotificationType;
import com.company.enums.SubscriptionStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "subscription")
public class SubscriptionEntity extends BaseEntity{

    @Column(name = "channel_id")
    private Integer channelId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", insertable = false, updatable = false)
    private ChannelEntity channel;

    @Column(name = "profile_id")
    private Integer profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
}
