package no.espen.chat.api

import no.espen.chat.config.KafkaConstants
import no.espen.chat.model.Message
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
class ChatController(private val kafkaTemplate: KafkaTemplate<String, Message>) {

    @PostMapping(value = ["/api/send"], consumes = ["application/json"], produces = ["application/json"])
    fun sendMessage(@RequestBody message: Message) {
        message.timestamp = Instant.now()

        try {
            //Sending the message to kafka topic queue
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, message).get()
        } catch (e: Exception) {
            println(e)
            throw RuntimeException(e)
        }
    }
}