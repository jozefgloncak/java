package gloncak.jozef;

import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class AppTestSerDes extends AppBase {

    private TestInputTopic<String, Person> inputTopicCustom;
    private TestOutputTopic<String, Person> outputTopicCustom;

    public static class Person {
        private String firstName;
        private String surname;

        public Person(String firstName, String surname) {
            this.firstName = firstName;
            this.surname = surname;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getSurname() {
            return surname;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(firstName, person.firstName) &&
                    Objects.equals(surname, person.surname);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstName, surname);
        }
    }

    public static class PersonSerializer implements Serializer<Person> {

        @Override
        public void configure(Map<String, ?> configs, boolean isKey) {

        }

        @Override
        public byte[] serialize(String topic, Person data) {
            return (data.firstName + "|" + data.surname).getBytes();
        }

        @Override
        public byte[] serialize(String topic, Headers headers, Person data) {
            return serialize(topic, data);
        }

        @Override
        public void close() {

        }
    }

    public static class PersonDeserializer implements Deserializer<Person> {

        @Override
        public void configure(Map<String, ?> configs, boolean isKey) {

        }

        @Override
        public Person deserialize(String topic, byte[] data) {
            String allData = new String(data);
            String[] splittedAllData = allData.split("\\|");
            if (splittedAllData.length == 2) {
                return new Person(splittedAllData[0], splittedAllData[1]);
            }
            return null;
        }

        @Override
        public Person deserialize(String topic, Headers headers, byte[] data) {
            return deserialize(topic, data);
        }

        @Override
        public void close() {

        }
    }


    public static class PersonSerdes extends Serdes.WrapperSerde<Person> {

        public PersonSerdes() {
            super(new PersonSerializer(), new PersonDeserializer());
        }
    }

    PersonSerdes personSerdes = new PersonSerdes();

    @Override
    protected Topology provideTopology() {
        StreamsBuilder sb = new StreamsBuilder();
        KStream<String, Person> stream = sb.stream("inputTopicCustomSerDes",
                Consumed.with(Serdes.String(), personSerdes));
//        stream.map((key, value) ->
//                new KeyValue<>(key,(value.surname+" "+value.firstName).toUpperCase()))
//                .to("outputTopicCustomSerDes", Produced.with(Serdes.String(), Serdes.String()));
                stream.to("outputTopicCustomSerDes", Produced.with(Serdes.String(), personSerdes));
        return sb.build();
    }

    @Override
    protected void topiky() {
        inputTopicCustom = testDriver.createInputTopic("inputTopicCustomSerDes",
                Serdes.String().serializer(),
                personSerdes.serializer());
        outputTopicCustom = testDriver.createOutputTopic("outputTopicCustomSerDes",
                Serdes.String().deserializer(),
                personSerdes.deserializer());
    }

    @Test
    public void customSerdesTest() {
        Person person1 = new Person("Martin", "Martinovic");
        inputTopicCustom.pipeInput("M", person1);
        Person person2 = new Person("Martin", "Martinovic");
        inputTopicCustom.pipeInput("A", person2);

        KeyValue<String, Person> kv1 = outputTopicCustom.readKeyValue();
        Assert.assertEquals("M", kv1.key);
        Assert.assertEquals(person1, kv1.value);
        Assert.assertNotSame(person1, kv1.value);

        KeyValue<String, Person> kv2 = outputTopicCustom.readKeyValue();
        Assert.assertEquals("A", kv2.key);
        Assert.assertEquals(person2, kv2.value);
        Assert.assertNotSame(person2, kv2.value);
        Assert.assertThat(kv2.value, is(equalTo(person2)));


        Assert.assertTrue(outputTopicCustom.isEmpty());
    }
}
