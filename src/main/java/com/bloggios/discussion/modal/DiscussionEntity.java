package com.bloggios.discussion.modal;

import com.bloggios.discussion.constants.ServiceConstants;
import com.bloggios.discussion.modal.embeddable.ImageLinksEntity;
import com.bloggios.discussion.modal.embeddable.TopicsEmbeddable;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.modal
 * Created_on - August 29 - 2024
 * Created_at - 22:48
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = ServiceConstants.DISCUSSION_TABLE)
public class DiscussionEntity {

    @Id
    @GeneratedValue(generator = "randomUUID")
    @GenericGenerator(name = "randomUUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String discussionId;

    @Column(nullable = false)
    private String title;
    private String details;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String detailsText;

    @Column(nullable = false)
    private String userId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;

    private String remoteAddress;

    @ElementCollection
    private List<ImageLinksEntity> imageLinks = new ArrayList<>();

    @ElementCollection
    private List<TopicsEmbeddable> topics = new ArrayList<>();

    @Column(nullable = false)
    private String apiVersion;

    @Column(nullable = false)
    private String version;
}
