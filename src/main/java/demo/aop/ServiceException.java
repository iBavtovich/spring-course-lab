package demo.aop;

import lombok.experimental.FieldDefaults;

import java.sql.SQLException;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
public class ServiceException extends RuntimeException {

    public ServiceException(Exception e) {
        super(e);
    }
}
