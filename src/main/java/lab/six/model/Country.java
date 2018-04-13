package lab.six.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Country implements Serializable{

	static final long serialVersionUID = 1L;

	int id;

    String name;

    String codeName;

    public Country(String name, String codeName) {
        this.name = name;
        this.codeName = codeName;
    }
}
