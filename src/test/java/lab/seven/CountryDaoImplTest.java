package lab.seven;

import java.util.List;

import lab.seven.dao.CountryDao;
import lab.seven.model.Country;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Illustrates basic use of Hibernate as a JPA provider.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:lab7orm.xml")
public class CountryDaoImplTest {

	private Country exampleCountry = new Country("Australia", "AU");

	@Autowired
	private CountryDao countryDao;

	@Test
	public void testSaveCountry() {
		countryDao.save(exampleCountry);

		List<Country> countryList = countryDao.getAllCountries();
		assertThat(countryList.size(), is(12));
		assertThat(countryList.get(0), is(exampleCountry));
	}

	@Test
	public void testGetAllCountries() {
		countryDao.save(new Country("Canada", "CA"));

		List<Country> countryList = countryDao.getAllCountries();
		assertThat(countryList.size(), is(12));
	}

	@Test
	public void testGetCountryByName() {

		Country country = countryDao.getCountryByName("Australia");
		assertThat(country, is(exampleCountry));
	}

}
