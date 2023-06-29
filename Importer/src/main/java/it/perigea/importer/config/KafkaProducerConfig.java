package it.perigea.importer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/* 
 * Questa classe serve a configurare kafka, viene creata un'istanza di ProducerFactory<String, String> con le configurazioni impostate
 * nell'application properties. In questo modo possiamo usarla per creare il bean kafkaTemplate sul quale verrà chiamato il metodo send.
 * */

@EnableKafka
@Configuration
public class KafkaProducerConfig {

    @Bean
    KafkaTemplate<String, String> responseKafkaTemplate(ProducerFactory<String, String> responseProducerFactory) {
        return new KafkaTemplate<>(responseProducerFactory);
    }
    
}
