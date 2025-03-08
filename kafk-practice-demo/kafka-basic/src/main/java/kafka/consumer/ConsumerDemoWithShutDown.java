package kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.CooperativeStickyAssignor;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class ConsumerDemoWithShutDown {
    private static final Logger log = (Logger) LoggerFactory.getLogger(ConsumerDemoWithShutDown.class.getName());
    public static void main(String[] args) {
        log.info("I am Consumer !!");

        String groupId = "my-java-application";
        String topic = "demo_java";
        //create producer properties
        Properties properties = new Properties();
        //connect to localhost
        properties.setProperty("bootstrap.servers","127.0.0.1:9092");

        //set producer properties
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer",StringDeserializer.class.getName());
        properties.setProperty("group.id",groupId);
        properties.setProperty("auto.offset.reset","earliest");
        // none = if we don't have existing consumer group then we fail
        // latest = only the latest messages we need to read
        // earliest = read from beginning

        // partition assignment strategy
        properties.setProperty("partition.assignment.strategy", CooperativeStickyAssignor.class.getName());
        properties.setProperty("group.instance.id", "..."); // strategy for static assignment

        //create consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // Get a reference to the main thread
        final Thread mainThread = Thread.currentThread();

        // adding the shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() ->{
            log.info("Detected a shutdown , let's exit by calling consumer.wakeup()...");
            consumer.wakeup();

            // join the main thread to allow the execution of the code in the main thread
            try {
                mainThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
        try {
            //Subscribe to a topic
            consumer.subscribe(List.of(topic));
            //
            while (true) {
                log.info("Polling");
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : consumerRecords) {
                    log.info("key: " + record.key() + " | Value: " + record.value());
                    log.info("Partition: " + record.partition() + " | Offset: " + record.offset());
                }
            }
        } catch (WakeupException e) {
            log.error("Consumer is starting to shut down",e);
        }catch (Exception e){
            log.error("Unexpected exception in the consumer",e);
        }finally {
            consumer.close();
            log.info("The consumer is now gracefully shut down");
        }
    }
}
