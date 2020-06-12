package no.espen.chat.model

import java.time.Instant

class Message {
    var sender: String? = null
    var timestamp: Instant? = null
    var content: String = ""
}
