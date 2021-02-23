package gloncak.jozef;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.util.Properties;


public class AppTestJoin {

    protected TestInputTopic<String, Long> inputTopic1;
    protected TestInputTopic<String, Long> inputTopic2;
    protected TestOutputTopic<String, String> outputTopic;

    protected TopologyTestDriver testDriver;

    @Test
    public void testJoining() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "test");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "dummy:1234");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Long().getClass().getName());


        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Long> source1 = builder.stream("input-topic1");
        KStream<String, Long> source2 = builder.stream("input-topic2");
        source1.join(source2,
                (leftValue, rightValue) ->  ""+leftValue+rightValue,
                JoinWindows.of(Duration.ofMinutes(5))

            ).to("output-topic", Produced.with(Serdes.String(), Serdes.String()));

        Topology topology = builder.build();
        testDriver = new TopologyTestDriver(topology, props);

        this.inputTopic1 = testDriver.createInputTopic("input-topic1",
                new Serdes.StringSerde().serializer(),
                new Serdes.LongSerde().serializer());
        inputTopic2 = testDriver.createInputTopic("input-topic2",
                new Serdes.StringSerde().serializer(),
                new Serdes.LongSerde().serializer());
        outputTopic = testDriver.createOutputTopic("output-topic",
                new Serdes.StringSerde().deserializer(),
                new Serdes.StringSerde().deserializer());


        // topic1: *(key1, 12)
        // topic2: ()
        inputTopic1.pipeInput("key1", 12L);
        Assert.assertTrue(outputTopic.isEmpty());

        // topic1: (key1, 12), *(key2, 15)
        // topic2: ()
        inputTopic1.pipeInput("key2", 15L);
        Assert.assertTrue(outputTopic.isEmpty());

        // topic1: (key1, 12), (key2, 15)
        // topic2: *(key2, 22)
        // output: *(key2, 1522)
        inputTopic2.pipeInput("key2", 22L);
        assertEquals(outputTopic.readKeyValue(), "key2", "1522");
        Assert.assertTrue(outputTopic.isEmpty());

        // topic1: (key1, 12), (key2, 15)
        // topic2: (key2, 22), *(key3, 25)
        // output:
        inputTopic2.pipeInput("key3", 25L);
        Assert.assertTrue(outputTopic.isEmpty());


        // topic1: (key1, 12), (key2, 15)
        // topic2: (key2, 22), (key3, 25), *(key2, 26)
        // output: *(key2, 1526)
        inputTopic2.pipeInput("key2", 26L);
        assertEquals(outputTopic.readKeyValue(), "key2", "1526");
        Assert.assertTrue(outputTopic.isEmpty());

        // topic1: (key1, 12), (key2, 15), *(key3, 12)
        // topic2: (key2, 22), (key3, 25), (key2, 26)
        // output: *(key3, 1225)
        inputTopic1.pipeInput("key3", 12L);
        assertEquals(outputTopic.readKeyValue(), "key3", "1225");
        Assert.assertTrue(outputTopic.isEmpty());
    }

    @After
    public void afterTest() {
        testDriver.close();
    }

    protected void assertEquals(KeyValue<String, String> readKeyValue, String key, String value) {
        Assert.assertEquals(key, readKeyValue.key);
        Assert.assertEquals(value, readKeyValue.value);
    }
    protected void assertWinEquals(KeyValue<Windowed<String>, Long> readKeyValue, String key, Long value) {
        Assert.assertEquals(key, readKeyValue.key.key());
        Assert.assertEquals(value, readKeyValue.value);
    }


}
