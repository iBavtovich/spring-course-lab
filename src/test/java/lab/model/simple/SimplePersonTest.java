package lab.model.simple;

import lab.model.Person;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.*;

@FieldDefaults(level = PRIVATE)
class SimplePersonTest {

    static final String APPLICATION_CONTEXT_XML_FILE_NAME = "ioc.xml";

    BeanFactory context = new ClassPathXmlApplicationContext(
            APPLICATION_CONTEXT_XML_FILE_NAME);

    @Test
    void testInitPerson() {
        Person person = context.getBean("person", Person.class);
        assertEquals(getExpectedPerson(), person);
    }

    public static Person getExpectedPerson() {

        return SimplePerson.builder()
                .age(35)
                .broke(false)
                .country(new SimpleCountry("Russia", "RU"))
                .contact(new SimpleContact("EMAIL", "asd@asd.ru"))
                .contact(new SimpleContact("TELEPHONE", "+55 11 99999-5555"))
                .firstName("John")
                .lastName("Smith")
                .height(1.78f)
                .isProgrammer(true)
                .build();
    }
}