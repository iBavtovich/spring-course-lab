package demo.aop;

import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
public class SecurityContext {

    public static User getPerson() {
        return new User();
    }
}
