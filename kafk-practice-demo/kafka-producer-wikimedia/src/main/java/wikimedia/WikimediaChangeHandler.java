package wikimedia;


import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WikimediaChangeHandler implements EventHandler {
    private final Logger logger = LoggerFactory.getLogger(WikimediaChangeHandler.class.getSimpleName());
    KafkaProducer<String,String> kafkaProducer;
    String topic;

    public WikimediaChangeHandler(KafkaProducer<String, String> producer, String topic) {
        this.kafkaProducer = producer;
        this.topic = topic;
    }

    @Override
    public void onOpen() throws Exception {
        //nothing here
    }

    @Override
    public void onClosed() throws Exception {
        kafkaProducer.close();
    }

    @Override
    public void onMessage(String event, MessageEvent messageEvent) throws Exception {
        logger.info(messageEvent.getData());
        kafkaProducer.send(new ProducerRecord<>(topic,messageEvent.getData()));
    }

    @Override
    public void onComment(String comment) throws Exception {
        // nothing here
    }

    @Override
    public void onError(Throwable t) {
        logger.error("Error in Stream Reading ",t);
    }
}
