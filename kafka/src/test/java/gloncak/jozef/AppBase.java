package gloncak.jozef;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.Windowed;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public abstract class AppBase {

    protected TestInputTopic<String, Long> inputTopic;
    protected TestOutputTopic<String, Long> outputTopic;

    protected TopologyTestDriver testDriver;
    @Before
    public void testInicialize() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "test");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "dummy:1234");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Long().getClass().getName());

        testDriver = new TopologyTestDriver(provideTopology(), props);
        topiky();

        inputTopic = testDriver.createInputTopic("input-topic",
                new Serdes.StringSerde().serializer(),
                new Serdes.LongSerde().serializer());
        outputTopic = testDriver.createOutputTopic("output-topic",
                new Serdes.StringSerde().deserializer(),
                new Serdes.LongSerde().deserializer());

    }

    @After
    public void testEnd() {
        testDriver.close();
    }

    protected abstract Topology provideTopology();
    protected void topiky() {

    }

    protected void assertEquals(KeyValue<String, Long> readKeyValue, String key, Long value) {
        Assert.assertEquals(key, readKeyValue.key);
        Assert.assertEquals(value, readKeyValue.value);
    }
    protected void assertWinEquals(KeyValue<Windowed<String>, Long> readKeyValue, String key, Long value) {
        Assert.assertEquals(key, readKeyValue.key.key());
        Assert.assertEquals(value, readKeyValue.value);
    }


}
