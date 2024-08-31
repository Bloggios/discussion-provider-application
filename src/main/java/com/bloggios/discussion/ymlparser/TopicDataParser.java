package com.bloggios.discussion.ymlparser;

import com.bloggios.discussion.payload.TopicsYmlDataProvider;
import com.bloggios.discussion.ymlparser.factory.YmlFileMapParserFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.ymlparser
 * Created_on - August 30 - 2024
 * Created_at - 22:40
 */

@PropertySource(value = "classpath:topics-data.yml", factory = YmlFileMapParserFactory.class)
@ConfigurationProperties(prefix = "topics-data")
@Configuration
@Getter
@Setter
public class TopicDataParser {

    public Map<String, TopicsYmlDataProvider> provider = new HashMap<>();
}
