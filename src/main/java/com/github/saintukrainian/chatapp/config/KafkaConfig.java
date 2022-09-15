package com.github.saintukrainian.chatapp.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@ConditionalOnProperty(name = "kafka.enabled")
@EnableKafka
@Configuration
public class KafkaConfig {

  @Value("${spring.kafka.topic}")
  private String topic;

  @Bean
  public NewTopic topic() {
    return TopicBuilder.name(topic).build();
  }
}
