package gloncak.jozef;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.TestOutputTopic;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.kstream.internals.WindowedSerializer;
import org.junit.Test;

import java.time.*;

public class AppTestCountWindowed extends AppBase {
    private TestOutputTopic<Windowed<String>, Long> windowedOutputTopic;
    private TimeWindowedSerializer<String> windowedSerializer =
            new TimeWindowedSerializer<>(Serdes.String().serializer());
    private TimeWindowedDeserializer<String> windowedDeserializer =
            new TimeWindowedDeserializer<String>(Serdes.String().deserializer());
    private Serde<Windowed<String>> windowedSerde = Serdes.serdeFrom(windowedSerializer,windowedDeserializer);

    @Override
    protected Topology provideTopology() {
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Long> source = builder.stream("input-topic");
        KGroupedStream<String, Long> groupedStream = source.groupByKey();
        KTable<Windowed<String>, Long> windowedCountTable = groupedStream.windowedBy(TimeWindows.of(Duration.ofSeconds(10))).count();


        windowedCountTable.toStream().to("windowed-output-topic", Produced.with(windowedSerde, Serdes.Long()));

        return builder.build();
    }

    @Override
    protected void topiky() {
        windowedOutputTopic = testDriver.createOutputTopic("windowed-output-topic",
                        windowedSerde.deserializer(),
                        new Serdes.LongSerde().deserializer());
    }

    //vytvoria sa postupne suhrne sucty pre kluc "key" pre 3 okna
    //- 10:33:44 - 10:33:49
    //- 10:35:44 - 10:35:49
    //- 10:33:51 - 10:33:56
    @Test
    public void countTest() {

        inputTopic.pipeInput("key", 42L,
                ZonedDateTime.of(2021, 1, 20, 10, 33, 44, 0, ZoneId.systemDefault()).toInstant());
        assertWinEquals(windowedOutputTopic.readKeyValue(), "key", 1L);


        inputTopic.pipeInput("key", 15L,
                ZonedDateTime.of(2021, 1, 20, 10, 33, 45, 0, ZoneId.systemDefault()).toInstant());
        assertWinEquals(windowedOutputTopic.readKeyValue(), "key", 2L);

        inputTopic.pipeInput("key", 21L,
                ZonedDateTime.of(2021, 1, 20, 10, 33, 46, 0, ZoneId.systemDefault()).toInstant());
        assertWinEquals(windowedOutputTopic.readKeyValue(), "key", 3L);

        inputTopic.pipeInput("key", 45L,
                ZonedDateTime.of(2021, 1, 20, 10, 35, 44, 0, ZoneId.systemDefault()).toInstant());
        assertWinEquals(windowedOutputTopic.readKeyValue(), "key", 1L);

        inputTopic.pipeInput("key", 21L,
                ZonedDateTime.of(2021, 1, 20, 10, 33, 51, 0, ZoneId.systemDefault()).toInstant());
        assertWinEquals(windowedOutputTopic.readKeyValue(), "key", 1L);


        inputTopic.pipeInput("key", 16L,
                ZonedDateTime.of(2021, 1, 20, 10, 35, 45, 0, ZoneId.systemDefault()).toInstant());
        assertWinEquals(windowedOutputTopic.readKeyValue(), "key", 2L);

        inputTopic.pipeInput("key", 21L,
                ZonedDateTime.of(2021, 1, 20, 10, 33, 52, 0, ZoneId.systemDefault()).toInstant());
        assertWinEquals(windowedOutputTopic.readKeyValue(), "key", 2L);
    }

}
