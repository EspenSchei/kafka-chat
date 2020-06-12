# Local development

## Start Zookeeper, Kafka and create a topic
1. `zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties`
2. `kafka-server-start /usr/local/etc/kafka/server.properties`
3. `kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafka-chat`
