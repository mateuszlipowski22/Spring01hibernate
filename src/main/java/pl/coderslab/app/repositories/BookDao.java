package pl.coderslab.app.repositories;

import org.springframework.stereotype.Repository;
import pl.coderslab.app.models.Book;
import pl.coderslab.app.models.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookDao {
    @PersistenceContext
    EntityManager entityManager;

    public void saveBook(Book book) {
        entityManager.persist(book);
    }

    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }

    public void update(Book book) {
        entityManager.merge(book);
    }

    public void delete(Book book) {
        entityManager.remove(entityManager.contains(book) ?
                book : entityManager.merge(book));
    }

    public List<Book> findAll(){
        Query query=entityManager.createQuery("SELECT book FROM Book book");
        return query.getResultList();
    }

    public List<Book> findAllByRating(int rating){
        Query query=entityManager.createQuery("SELECT book FROM Book book WHERE book.rating>:rating");
        query.setParameter("rating",rating);
        return query.getResultList();
    }

    public List<Book> findBooksWithPublisher(){
        Query query = entityManager.createQuery("SELECT book FROM Book book JOIN book.publisher");
        return query.getResultList();
    }

    public List<Book> findBooksWithPublishers(Publisher publisher) {
        return entityManager
                .createQuery("SELECT book FROM Book book WHERE book.publisher =:publisher")
                .setParameter("publisher", publisher)
                .getResultList();
    }

    public List<Book> findBooksWithAuthor(){
        return entityManager
                .createQuery("SELECT book FROM Book book WHERE book.authors IS NOT EMPTY")
                .getResultList();
    }
}

