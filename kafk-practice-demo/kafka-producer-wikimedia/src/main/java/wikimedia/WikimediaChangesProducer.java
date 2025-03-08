package wikimedia;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.StreamException;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.EventHandler;
import java.net.URI;
import java.util.Properties;

public class WikimediaChangesProducer {
    private static final Logger logger = LoggerFactory.getLogger(WikimediaChangesProducer.class.getName());

    public static void main(String[] args) throws StreamException {
        logger.info("Hello I am Wiki Producer");

        String BOOTSTRAP_SERVER = "127.0.0.1:9092";
        //create producer properties
        Properties properties = new Properties();
        //connect to localhost
        properties.setProperty("bootstrap.servers",BOOTSTRAP_SERVER);

        //set producer properties
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer",StringSerializer.class.getName());

        // create producer
        KafkaProducer<String,String> producer = new KafkaProducer<>(properties);

        String TOPIC = "wikimedia.recentchange";
        EventHandler eventHandler = TODO;
        String URL = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(URL));
        EventSource eventSource = builder.build();


        //start the producer in another thread
        eventSource.start();

    }
}
