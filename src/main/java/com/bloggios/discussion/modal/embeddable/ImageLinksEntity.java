package com.bloggios.discussion.modal.embeddable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.modal.embeddable
 * Created_on - August 29 - 2024
 * Created_at - 22:58
 */

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageLinksEntity {

    @Column(nullable = false, unique = true)
    private String link;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String name;
}
