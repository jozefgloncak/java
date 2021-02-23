package gloncak.jozef;

import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.junit.Test;

public class AppTestCountSum extends AppBase {

    @Override
    protected Topology provideTopology() {
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Long> source = builder.stream("input-topic");
        KTable<String, Long> sumTable = source.groupByKey().aggregate(
                () -> 0L,
                (aggKey, newValue, aggValue) -> aggValue + newValue
        );
        sumTable.toStream().to("output-topic");

        return builder.build();
    }

    @Test
    public void sumTest() {
        inputTopic.pipeInput("key", 42L);
        assertEquals(outputTopic.readKeyValue(), "key", 42L);

        inputTopic.pipeInput("key2", 15L);
        assertEquals(outputTopic.readKeyValue(), "key2", 15L);

        inputTopic.pipeInput("key2", 21L);
        assertEquals(outputTopic.readKeyValue(), "key2", 36L);

        inputTopic.pipeInput("key2", 45L);
        assertEquals(outputTopic.readKeyValue(), "key2", 81L);

        inputTopic.pipeInput("key", 16L);
        assertEquals(outputTopic.readKeyValue(), "key", 58L);
    }
}
