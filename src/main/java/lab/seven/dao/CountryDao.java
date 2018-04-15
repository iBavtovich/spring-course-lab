package lab.seven.dao;

import java.util.List;

import lab.seven.model.Country;

public interface CountryDao {

	void save(Country country);

	List<Country> getAllCountries();

	Country getCountryByName(String name);

}