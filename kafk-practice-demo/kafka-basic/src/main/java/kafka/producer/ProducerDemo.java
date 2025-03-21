package kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


public class ProducerDemo {
    private static final Logger log = (Logger) LoggerFactory.getLogger(ProducerDemo.class.getName());
    public static void main(String[] args) {
        log.info("I am Producer !!");
        //create producer properties
        Properties properties = new Properties();
        //connect to localhost
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");

        //set producer properties
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        // by default it is uses Stiky partition
//        properties.setProperty("batch.size","400");
//        properties.setProperty("partitioner.class", RoundRobinPartitioner.class.getName());

        // create producer
        KafkaProducer<String,String> producer = new KafkaProducer<>(properties);
        //create a producer record
        ProducerRecord<String,String> producerRecord = new ProducerRecord<>("demo_java","Hello CallBack !!");

        //send data
        producer.send(producerRecord, (metadata, exception) -> {
            if(exception == null){
                log.info("Received new metadata \n" +
                        "Topic: "+ metadata.topic()+"\n"+
                        "Partition: "+ metadata.partition()+"\n"+
                        "Offset: "+ metadata.offset()+"\n"+
                        "Timestamp: "+ metadata.timestamp());
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
