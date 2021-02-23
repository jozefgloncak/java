package gloncak.jozef;

import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.junit.Test;

public class AppTestCount extends AppBase {

    @Override
    protected Topology provideTopology() {
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Long> source = builder.stream("input-topic");
        KTable<String, Long> countTable = source.groupByKey().count();
        countTable.toStream().to("output-topic");

        return builder.build();
    }

    @Test
    public void countTest() {
        inputTopic.pipeInput("key", 42L);
        assertEquals(outputTopic.readKeyValue(), "key", 1L);

        inputTopic.pipeInput("key2", 15L);
        assertEquals(outputTopic.readKeyValue(), "key2", 1L);

        inputTopic.pipeInput("key2", 21L);
        assertEquals(outputTopic.readKeyValue(), "key2", 2L);

        inputTopic.pipeInput("key2", 45L);
        assertEquals(outputTopic.readKeyValue(), "key2", 3L);

        inputTopic.pipeInput("key", 16L);
        assertEquals(outputTopic.readKeyValue(), "key", 2L);

    }

}
