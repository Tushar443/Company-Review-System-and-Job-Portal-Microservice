package kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


public class ProducerDemoWithKeys {
    private static final Logger log = (Logger) LoggerFactory.getLogger(ProducerDemoWithKeys.class.getName());
    public static void main(String[] args) {
        log.info("Hello World!!!");
        //create producer properties
        Properties properties = new Properties();
        //connect to localhost
        properties.setProperty("bootstrap.servers","127.0.0.1:9092");

        //set producer properties
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer",StringSerializer.class.getName());
        // by default it is uses Stiky partition
//        properties.setProperty("batch.size","400");
//        properties.setProperty("partitioner.class", RoundRobinPartitioner.class.getName());


        String key = "id"+Math.random();
        // create producer
        KafkaProducer<String,String> producer = new KafkaProducer<>(properties);
        //create a producer record
        String topic="demo_java";
        String value="Hello With Key !!";
        ProducerRecord<String,String> producerRecord = new ProducerRecord<>(topic,key,value);

        //send data
        producer.send(producerRecord, (metadata, exception) -> {
            if(exception == null){
                log.info("Key: "+ key + " | Partition: "+ metadata.partition());
            }else{
                log.error("Error while producing ",exception);
            }
        });


        //tell producer to send all data and block until done -- Synchronous
        producer.flush();
        //flush and close the producer
        producer.close();
        System.out.println("done");
    }
}
