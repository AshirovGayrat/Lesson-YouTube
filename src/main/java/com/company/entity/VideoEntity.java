package com.company.entity;

import com.company.enums.VideoStatus;
import com.company.enums.VideoType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "video")
public class VideoEntity extends BaseEntity{

    @Column(name = "video_name")
    private String videoName;
    private String description;
    private String key;

    @Column(name = "preview_id")
    private String previewId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preview_id", insertable = false, updatable = false)
    private AttachEntity preview;

    @Column(name = "attach_id")
    private String attachId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id", insertable = false, updatable = false)
    private AttachEntity attach;

    @Column(name = "channel_id")
    private Integer channelId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", insertable = false, updatable = false)
    private ChannelEntity channel;

    @Column(name = "playlist_id")
    private Integer playlistId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name =  "playlist_id", insertable = false, updatable = false)
    private PlaylistEntity playlist;

    @Column(name = "category_id")
    private Integer category_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;

    @Column(name = "view_count")
    private int viewCount;
    @Column(name = "shared_count")
    private int sharedCount;
    @Enumerated(EnumType.STRING)
    private VideoType type;
    @Enumerated(EnumType.STRING)
    private VideoStatus status;
    @Column(name = "published_date")
    private LocalDateTime publishedDate;
}
