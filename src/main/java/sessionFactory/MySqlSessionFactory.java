package sessionFactory;

import entity.*;
import org.hibernate.cfg.*;
import org.hibernate.SessionFactory;

import java.util.Properties;

public class MySqlSessionFactory {
    public SessionFactory prepareRelationalDb() {
        final SessionFactory sessionFactory;
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.JAKARTA_JDBC_DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        properties.put(Environment.JAKARTA_JDBC_URL, "jdbc:p6spy:mysql://localhost:3306/world");
        properties.put(Environment.JAKARTA_JDBC_USER, "root");
        properties.put(Environment.JAKARTA_JDBC_PASSWORD, "admin");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "validate");
        properties.put(Environment.STATEMENT_BATCH_SIZE, "100");

        sessionFactory = new Configuration()
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(CountryLanguage.class)
                .addProperties(properties)
                .buildSessionFactory();
        return sessionFactory;
    }
}
