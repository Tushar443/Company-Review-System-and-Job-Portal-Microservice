package wikimedia;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WikimediaChangesProducer {
    private static final Logger logger = LoggerFactory.getLogger(WikimediaChangesProducer.class.getName());

    public static void main(String[] args) throws InterruptedException {
        logger.info("Hello I am Wiki Producer");

        String BOOTSTRAP_SERVER = "127.0.0.1:9092";
        //create producer properties
        Properties properties = new Properties();
        //connect to localhost
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVER);

        //set producer properties
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

//        set safe producer config below kafka <=2.8
//        properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,"true");
//        properties.setProperty(ProducerConfig.ACKS_CONFIG,"all");
//        properties.setProperty(ProducerConfig.RETRIES_CONFIG, Integer.toString(Integer.MAX_VALUE));

        // for high throughput
//        properties.setProperty(ProducerConfig.LINGER_MS_CONFIG,"20");
//        properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG,Integer.toString(32*1024));
//        properties.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG,"snappy");

        // create producer
        KafkaProducer<String,String> producer = new KafkaProducer<>(properties);

        final String topic = "wikimedia.recentchange";
        EventHandler eventHandler = new WikimediaChangeHandler(producer,topic);
        String URL = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(URL));
        EventSource eventSource = builder.build();


        //start the producer in another thread
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}
