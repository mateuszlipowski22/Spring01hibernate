package pl.coderslab.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.models.Author;
import pl.coderslab.app.models.Book;
import pl.coderslab.app.models.Publisher;
import pl.coderslab.app.repositories.AuthorDao;
import pl.coderslab.app.repositories.BookDao;
import pl.coderslab.app.repositories.PublisherDao;

import java.util.List;
import ch.qos.logback.classic.Logger;


@Controller
public class BookController {
    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;
    private final Logger logger;


    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao, Logger logger) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
        this.logger = logger;
    }


    @RequestMapping("/book/add")
    @ResponseBody
    public String hello() {
        Author author1 = authorDao.findById(1L);
        Author author2 = authorDao.findById(2L);

        Publisher publisher = new Publisher();
        publisher.setName("publisher");
        publisherDao.savePublisher(publisher);

        Book book = new Book();
        book.setTitle("Thinking in Java ");
        book.getAuthors().add(author1);
        book.getAuthors().add(author2);
        bookDao.saveBook(book);
        return "Id dodanej książki to:" + book.getId();
    }

    @RequestMapping("/book/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        return book.toString();
    }


    @RequestMapping("/book/update/{id}/{title}")
    @ResponseBody
    public String updateBook(@PathVariable long id, @PathVariable String title ) {
        Book book = bookDao.findById(id);
        book.setTitle(title);
        bookDao.update(book);
        return book.toString();
    }

    @RequestMapping("/book/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return "deleted";
    }

    @RequestMapping("/book/all")
    @ResponseBody
    public String findAll() {
        List<Book> all = bookDao.findAll();
        all.forEach(b -> logger.info(b.toString()));
        return "findAll";
    }
}
