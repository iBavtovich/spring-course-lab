package demo.aop;

import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
public class User {

    public boolean hasRight(String getUser) {
        return false;
    }
}
