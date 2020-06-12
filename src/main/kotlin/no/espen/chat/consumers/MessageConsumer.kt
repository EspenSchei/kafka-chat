package no.espen.chat.consumers

import no.espen.chat.config.KafkaConstants
import no.espen.chat.model.Message
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component


@Component
class MessageConsumer(val template: SimpMessagingTemplate) {

    @KafkaListener(topics = [KafkaConstants.KAFKA_TOPIC], groupId = KafkaConstants.GROUP_ID)
    fun listen(message: Message) {
        template.convertAndSend("/topic/group", message)
    }
}