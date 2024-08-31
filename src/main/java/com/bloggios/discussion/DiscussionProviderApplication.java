package com.bloggios.discussion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {
        "com.bloggios.discussion",
        "com.bloggios.authenticationconfig",
        "com.bloggios.elasticsearch.configuration"
})
@EnableAsync
public class DiscussionProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscussionProviderApplication.class, args);
    }

}
