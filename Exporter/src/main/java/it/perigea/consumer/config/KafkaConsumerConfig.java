package it.perigea.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import it.perigea.consumer.entities.Response;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Bean
    DefaultKafkaConsumerFactory<String, Response> responseConsumerFactory() {
    	
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "Response");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, Response.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Response.class));
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Response> responseKafkaListenerContainerFactory() {
   
        ConcurrentKafkaListenerContainerFactory<String, Response> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(responseConsumerFactory());
        return factory;
    }
}
