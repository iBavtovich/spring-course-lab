package lab.six;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import lab.six.dao.CountryDao;
import lab.six.model.Country;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:lab6jdbc.xml")
public class JdbcTest{

	@Autowired
	private CountryDao countryDao;
	
    private ArrayList<Country> expectedCountryList = new ArrayList<>();
    private List<Country> expectedCountryListStartsWithA = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        initExpectedCountryLists();
        countryDao.loadCountries();
    }

    
    @Test
    @DirtiesContext
    public void testCountryList() {
        List<Country> countryList = countryDao.getCountryList();
        assertNotNull(countryList);
        assertThat(countryList.size(), is(expectedCountryList.size()));
        for (int i = 0; i < expectedCountryList.size(); i++) {
            assertThat(countryList.get(i), is(expectedCountryList.get(i)));
        }
    }

    @Test
    @DirtiesContext
    public void testCountryListStartsWithA() {
        List<Country> countryList = countryDao.getCountryListStartWith("A");
        assertNotNull(countryList);
        assertThat(countryList.size(), is(expectedCountryListStartsWithA.size()));
        for (int i = 0; i < expectedCountryListStartsWithA.size(); i++) {
            assertThat(countryList.get(i), is(expectedCountryListStartsWithA.get(i)));
        }
    }

    @Test
    @DirtiesContext
    public void testCountryChange() {
        countryDao.updateCountryName("RU", "Russian Federation");
        Country selectedCountry = countryDao.getCountryByCodeName("RU");
        assertThat(selectedCountry, is(new Country(selectedCountry.getId(), "Russian Federation", "RU")));
    }

    private void initExpectedCountryLists() {
         for (int i = 0; i < CountryDao.COUNTRY_INIT_DATA.length; i++) {
             String[] countryInitData = CountryDao.COUNTRY_INIT_DATA[i];
             Country country = new Country(i+1, countryInitData[0], countryInitData[1]);
             expectedCountryList.add(country);
             if (country.getName().startsWith("A")) {
                 expectedCountryListStartsWithA.add(country);
             }
         }
     }
}