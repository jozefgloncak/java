package gloncak.jozef;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.junit.Assert;
import org.junit.Test;

public class AppTestFilter extends AppBase {

    protected Topology provideTopology() {

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Long> source = builder.stream("input-topic", Consumed.with(new Serdes.StringSerde(),
                new Serdes.LongSerde()));
        source.filter((key, value) -> value > 20).to("output-topic", Produced.with(new Serdes.StringSerde(),
                new Serdes.LongSerde()));

        return builder.build();
    }

    @Test
    public void filterTest() {

        inputTopic.pipeInput("key", 42L);
        inputTopic.pipeInput("key", 16L);
        inputTopic.pipeInput("key", 15L);
        inputTopic.pipeInput("key", 21L);

        KeyValue<String, Long> outputVal = outputTopic.readKeyValue();
        Assert.assertEquals("key", outputVal.key);
        Assert.assertEquals((Long) 42L, outputVal.value);

        outputVal = outputTopic.readKeyValue();
        Assert.assertEquals("key", outputVal.key);
        Assert.assertEquals((Long) 21L, outputVal.value);

        Assert.assertTrue(outputTopic.isEmpty());
    }

}
