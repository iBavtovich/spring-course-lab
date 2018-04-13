package lab.model.simple;

import lab.model.Contact;
import lab.model.Country;
import lab.model.Person;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimplePerson implements Person {
    String firstName;
    String lastName;
    Country country;
    int age;
    float height;
    boolean isProgrammer;
    boolean broke;

    @Singular
    List<Contact> contacts;
}
