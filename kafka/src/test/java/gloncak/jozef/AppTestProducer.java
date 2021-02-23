package gloncak.jozef;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.junit.Test;

import java.util.Properties;

/**
 * Demonstruje pracu s KAFKA producerom
 */
public class AppTestProducer {

    /**
     * Odosielanie sprav na brokera v urcitych casovych intervaloch s 10 roznymi klucmi
     * @throws InterruptedException
     */
    @Test
    public void testWithPartitions() throws InterruptedException {

        Properties props = new Properties();
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, Serdes.String().serializer().getClass().getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, Serdes.String().serializer().getClass().getName());
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<>("streams-plaintext-input", "key"+i%10, "value"+i));
            Thread.sleep(400);
        }
        producer.flush();
        producer.close();

    }
}
