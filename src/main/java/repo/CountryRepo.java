package repo;

import entity.Country;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;

import java.util.List;

public class CountryRepo {
    private final SessionFactory sessionFactory;

    public CountryRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Country> getAll() {
        Query<Country> query = sessionFactory.getCurrentSession().createQuery("from Country c join fetch c.languages", Country.class);
        return query.list();
    }
}
