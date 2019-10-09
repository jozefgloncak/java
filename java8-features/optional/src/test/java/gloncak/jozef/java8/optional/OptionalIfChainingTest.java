package gloncak.jozef.java8.optional;

import gloncak.jozef.java8.optional.dto.Address;
import gloncak.jozef.java8.optional.dto.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;


public class OptionalIfChainingTest {

    private static final String NO_STREET = ":::no street specified";

    @Test
    public void goldenPathTest() {
        Optional<Student> student = Optional.ofNullable(provideStudentBySurname("Adamovic"));
        String street = student
                .map(Student::getAddress)
                .map(Address::getStreet)
                .orElseGet(() -> NO_STREET);
        Assert.assertEquals("Adamovicova", street);
    }

    @Test
    public void streetMissingTest() {
        Optional<Student> student = Optional.ofNullable(provideStudentBySurname("Benadik"));
        String street = student
                .map(Student::getAddress)
                .map(Address::getStreet)
                .orElseGet(() -> NO_STREET);
        Assert.assertNotNull(student.isPresent());
        Assert.assertNotNull(student.get().getAddress());
        Assert.assertNull(student.get().getAddress().getStreet());
        Assert.assertEquals(NO_STREET, street);
    }

    @Test
    public void addressMissingTest() {
        Optional<Student> student = Optional.ofNullable(provideStudentBySurname("Cibulka"));
        String street = student
                .map(Student::getAddress)
                .map(Address::getStreet)
                .orElseGet(() -> NO_STREET);
        Assert.assertNotNull(student.isPresent());
        Assert.assertNull(student.get().getAddress());
        Assert.assertEquals(NO_STREET, street);
    }

    @Test
    public void notExistingStudentTest() {
        Optional<Student> student = Optional.ofNullable(provideStudentBySurname("Demovic"));
        String street = student
                .map(Student::getAddress)
                .map(Address::getStreet)
                .orElseGet(() -> NO_STREET);
        Assert.assertTrue(!student.isPresent());
        Assert.assertEquals(NO_STREET, street);
    }

    private Student provideStudentBySurname(String surname) {
        switch (surname) {
            case "Adamovic": return new Student(new Address("Adamovicova"));
            case "Benadik": return new Student(new Address());
            case "Cibulka": return new Student();
            default: return null;
        }
    }
}
