package practice;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class HibernateConfiguration {


    Configuration configuration = new Configuration();

    @Bean
    public SessionFactory sessionFactory() {
        Configuration configuration = new Configuration();
        configuration
                .addAnnotatedClass(Movie.class)
                .addPackage("com.project")
                .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5431/postgres")
                .setProperty("hibernate.connection.username", "postgres")
                .setProperty("hibernate.connection.password", "root")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.hbm2ddl.auto", "create-drop");
        return configuration.buildSessionFactory();
    }
}
