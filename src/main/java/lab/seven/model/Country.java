package lab.seven.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="COUNTRY")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Country implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	int id;

	@Column(name = "name")
	String name;

	@Column(name = "code_name")
	String codeName;

	public Country(String name, String codeName) {
		this.name = name;
		this.codeName = codeName;
	}


}
