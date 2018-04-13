package lab.six.dao;

import lab.six.model.Country;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class CountryDao extends JdbcDaoSupport {

	private static final String LOAD_COUNTRIES_SQL = "insert into country (name, code_name) values (?,?)";

	private static final String GET_ALL_COUNTRIES_SQL = "select * from country";
	private static final String GET_COUNTRIES_BY_NAME_SQL = "select * from country where name like :name";
	private static final String GET_COUNTRY_BY_NAME_SQL = "select * from country where name=(?)";
	private static final String GET_COUNTRY_BY_CODE_NAME_SQL = "select * from country where code_name=(?)";

	private static final String UPDATE_COUNTRY_NAME_SQL = "update country SET name=(?) where code_name=(?)";

	protected static final String[][] COUNTRY_INIT_DATA = { { "Australia", "AU" },
			{ "Canada", "CA" }, { "France", "FR" }, { "Hong Kong", "HK" },
			{ "Iceland", "IC" }, { "Japan", "JP" }, { "Nepal", "NP" },
			{ "Russian Federation", "RU" }, { "Sweden", "SE" },
			{ "Switzerland", "CH" }, { "United Kingdom", "GB" },
			{ "United States", "US" } };

	private static final CountryRowMapper COUNTRY_ROW_MAPPER = new CountryRowMapper();

	public List<Country> getCountryList() {
		return getJdbcTemplate().query(GET_ALL_COUNTRIES_SQL, COUNTRY_ROW_MAPPER);
	}

	public List<Country> getCountryListStartWith(String name) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				getDataSource());
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource(
				"name", name + "%");
		return namedParameterJdbcTemplate.query(GET_COUNTRIES_BY_NAME_SQL,
				sqlParameterSource, COUNTRY_ROW_MAPPER);
	}

	public void updateCountryName(String codeName, String newCountryName) {
	    getJdbcTemplate().update(UPDATE_COUNTRY_NAME_SQL, newCountryName, codeName);
	}

	public void loadCountries() {
		for (String[] countryData : COUNTRY_INIT_DATA) {
			getJdbcTemplate().update(LOAD_COUNTRIES_SQL, countryData[0], countryData[1]);
		}
	}

	public Country getCountryByCodeName(String codeName) {
		return getJdbcTemplate().queryForObject(GET_COUNTRY_BY_CODE_NAME_SQL, COUNTRY_ROW_MAPPER, codeName);
	}

	public Country getCountryByName(String name)
			throws CountryNotFoundException {
		List<Country> countryList = getJdbcTemplate().query(GET_COUNTRY_BY_NAME_SQL, COUNTRY_ROW_MAPPER, name);
		if (countryList.isEmpty()) {
			throw new CountryNotFoundException();
		}
		return countryList.get(0);
	}
}
