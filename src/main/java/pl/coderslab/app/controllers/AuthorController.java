package pl.coderslab.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.models.Author;
import pl.coderslab.app.models.Book;
import pl.coderslab.app.repositories.AuthorDao;
import pl.coderslab.app.repositories.BookDao;

@Controller
public class AuthorController {

    private final AuthorDao authorDao;
    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }
    @RequestMapping("/author/add")
    @ResponseBody
    public String hello() {
        Author author = new Author();
        author.setFirstName("Thinking in Java");
        author.setLastName("Bruce Eckel");
        authorDao.saveAuthor(author);
        return "Id dodanej książki to:"
                + author.getId();
    }

    @RequestMapping("/author/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable long id) {
        Author author = authorDao.findById(id);
        return author.toString();
    }


    @RequestMapping("/author/update/{id}/{firstName}")
    @ResponseBody
    public String updateBook(@PathVariable long id, @PathVariable String firstName ) {
        Author author = authorDao.findById(id);
        author.setFirstName(firstName);
        authorDao.update(author);
        return author.toString();
    }

    @RequestMapping("/author/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Author author = authorDao.findById(id);
        authorDao.delete(author);
        return "deleted";
    }

}
