package it.perigea.importer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@EnableKafka
@Configuration
public class KafkaProducerConfig {
	
	@Bean
    ProducerFactory<String, String> responseProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
          ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
          "localhost:9092");
        configProps.put(
          ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 	//Modifico il serializer da usare, in questo modo al posto di serializzare in stringhe uso json
          StringSerializer.class);
        configProps.put(
          ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, //Sia per la chiave che per il value.
          JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    KafkaTemplate<String, String> responseKafkaTemplate() {
        return new KafkaTemplate<>(responseProducerFactory());
    }
    
    
}
