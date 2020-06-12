package no.espen.chat.config

import no.espen.chat.model.Message
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
class ConsumerConfig {

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Message> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Message>()
        factory.consumerFactory = consumerFactory()
        return factory
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Message> {
        return DefaultKafkaConsumerFactory(consumerConfigurations(), StringDeserializer(), JsonDeserializer(Message::class.java))
    }

    @Bean
    fun consumerConfigurations(): Map<String, Any> {
        val configurations: MutableMap<String, Any> = HashMap()
        configurations[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = KafkaConstants.KAFKA_BROKER
        configurations[ConsumerConfig.GROUP_ID_CONFIG] = KafkaConstants.GROUP_ID
        configurations[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        configurations[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        configurations[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "latest"
        return configurations
    }
}