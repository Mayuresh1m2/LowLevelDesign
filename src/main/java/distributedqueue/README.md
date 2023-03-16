[Problem Link](https://workat.tech/machine-coding/practice/design-distributed-queue-cuudq0sk0v14)

# Problem Statement

Design an In-Memory Distributed Queue like Kafka.

# Requirements

1. The queue should be in-memory and should not require access to the file system.
1. There can be multiple topics in the queue.
1. A (string) message can be published on a topic by a producer/publisher and
   consumers/subscribers can subscribe to the topic to receive the messages.
1. There can be multiple producers and consumers.
1. A producer can publish to multiple topics.
1. A consumer can listen to multiple topics.
1. The consumer should print "<consumer_id> received <message>" on receiving the message.
1. The queue system should be multi-threaded, i.e., messages can be produced or consumed in
   parallel by different producers/consumers.

# Input/Output Format

1. You do not need to take input from the command-line.
1. Create 2 topics: topic1 and topic2
1. Create 2 producers: inMemoryDistributedQueueProducer1, and inMemoryDistributedQueueProducer2
1. Create 5 consumers: consumer1, consumer2, consumer3, consumer4, and consumer5
1. Make all 5 consumers subscribe to topic1
1. Make consumers 1, 3, and 4 subscribe to topic2
1. Make inMemoryDistributedQueueProducer1 publish message "Message 1" to topic1
1. Make inMemoryDistributedQueueProducer1 publish message "Message 2" to topic1
1. Make inMemoryDistributedQueueProducer2 publish message "Message 3" to topic1
1. Make inMemoryDistributedQueueProducer1 publish message "Message 4" to topic2
1. Make inMemoryDistributedQueueProducer2 publish message "Message 5" to topic2

# Expectations

1. Make sure that you have a working and demonstrable code
1. Make sure that the code is functionally correct
1. Code should be modular and readable
1. Separation of concern should be addressed
1. Please do not write everything in a single file (if not coding in C/C++)
1. Code should easily accommodate new requirements and minimal changes
1. There should be a main method from where the code could be easily testable

# [Optional]

1. Write unit tests, if possible
1. No need to create a GUI
1. Optional Requirements
1. Allow consumer groups. A consumer group can have multiple consumers and consumers mention
   their consumer group while subscribing to a topic. A message can be consumed by only one
   consumer per consumer group.