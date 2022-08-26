package pl.coderslab.app.repositories;

import org.springframework.stereotype.Repository;
import pl.coderslab.app.models.Author;
import pl.coderslab.app.models.Book;
import pl.coderslab.app.models.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AuthorDao {

    @PersistenceContext
    EntityManager entityManager;
    public void saveAuthor(Author author) {
        entityManager.persist(author);
    }

    public Author findById(long id) {
        return entityManager.find(Author.class, id);
    }

    public void update(Author author) {
        entityManager.merge(author);
    }

    public void delete(Author author) {
        entityManager.remove(entityManager.contains(author) ?
                author : entityManager.merge(author));
    }

    public List<Author> findAll(){
        Query query = entityManager.createQuery("SELECT author FROM Author author");
        return query.getResultList();
    }

}
