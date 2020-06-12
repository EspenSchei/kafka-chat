package no.espen.chat.config

import no.espen.chat.model.Message
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory


@EnableKafka
@Configuration
class ProducerConfig {

    @Bean
    fun producerFactory(): ProducerFactory<String, Message> {
        return DefaultKafkaProducerFactory(producerConfigurations())
    }

    @Bean
    fun producerConfigurations(): Map<String, Any> {
        val configurations: MutableMap<String, Any> = HashMap()
        configurations[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = KafkaConstants.KAFKA_BROKER
        configurations[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configurations[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return configurations
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Message> {
        return KafkaTemplate(producerFactory())
    }
}