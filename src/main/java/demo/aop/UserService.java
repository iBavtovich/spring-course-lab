package demo.aop;

import lab.model.Person;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Log4j2
@Service
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserService {
    PersonDao personDao;
    Map<Integer, Person> cache = new HashMap<>();

    public Person getPerson(int id) {
        log.info("Call method getPerson with id {}", id);
        if (!SecurityContext.getPerson().hasRight("GetUser"))
            throw new AuthException("Permission Denied");
        try {
            Person person;
            if (cache.containsKey(id)) person = cache.get(id);
            else cache.put(id, person = personDao.get(id));
            log.info("User info is: {}", person);
            return person;
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
}
