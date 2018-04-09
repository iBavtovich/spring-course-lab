package demo.aop;

import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
public class AuthException extends RuntimeException {

    public AuthException(String s) {
    }
}
