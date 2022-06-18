package com.company.entity;

import com.company.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "playlist")
public class PlaylistEntity extends BaseEntity{

    @Column
    private String name;
    @Column
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "order_num")
    private int orderNum;

    @Column(name = "playlist_photo_id")
    private String playlistPhotoId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_photo_id", insertable = false, updatable = false)
    private AttachEntity playlistPhoto;

    @Column(name = "channel_id")
    private Integer channelId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", insertable = false, updatable = false)
    private ChannelEntity channel;

}
