package lab.seven.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lab.seven.dao.CountryDao;
import lab.seven.model.Country;

@Repository
public class CountryJpaDaoImpl extends AbstractJpaDao implements CountryDao {

	@Override
	public void save(Country country) {
		EntityManager em = emf.createEntityManager();
		em.persist(country);
	}

	@Override
	public List<Country> getAllCountries() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Country", Country.class)
				 .getResultList();
	}

	@Override
	public Country getCountryByName(String name) {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("SELECT c from Country c WHERE c.name LIKE :name", Country.class)
				 .setParameter("name", name)
				 .getSingleResult();
	}
}
