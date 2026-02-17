package practice;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("practice");
        SessionFactory sessionFactory = context.getBean(SessionFactory.class);
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        Movie movie1 = new Movie("Титаник", "Драма", 1995);
        Movie movie2 = new Movie("Терминатор", "Боевик", 200);
        Movie movie3 = new Movie("Шурик", "Комедия", 1975);
        session.persist(movie1);
        session.persist(movie2);
        session.persist(movie3);
        session.getTransaction().commit();

        session.beginTransaction();
        List<Movie> movies = session.createQuery("FROM Movie m", Movie.class).getResultList();
        System.out.println("-".repeat(10));
        for (Movie movie : movies) {
            System.out.println(movie);
        }
        System.out.println("-".repeat(10));
        List<Movie> byGenre = session.createQuery("FROM Movie m where m.genre = :genre", Movie.class)
                .setParameter("genre","Комедия")
                .getResultList();

        for (Movie movie : byGenre) {
            System.out.println(movie);
        }
        System.out.println("-".repeat(10));
        session.getTransaction().commit();

        session.beginTransaction();
        Movie movieFindById = session.find(Movie.class, 1L);
        movieFindById.setTitle("Бременские музыканты");
        session.getTransaction().commit();

        session.beginTransaction();
        Movie deleteMovie = session.find(Movie.class, 2L);
        session.remove(deleteMovie);
        session.getTransaction().commit();

        List<Movie> movieList = session.createQuery("FROM Movie m", Movie.class).getResultList();
        System.out.println("-".repeat(10));
        for (Movie movie : movieList) {
            System.out.println(movie);
        }


        session.close();

    }
}
