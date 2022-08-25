package pl.coderslab.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.models.Book;
import pl.coderslab.app.models.Publisher;
import pl.coderslab.app.repositories.PublisherDao;

@Controller
public class PublisherController {

    public PublisherController(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    private final PublisherDao publisherDao;


    @RequestMapping("/publisher/add")
    @ResponseBody
    public String hello(){
        Publisher publisher = new Publisher();
        publisher.setName("PWN");
        publisherDao.savePublisher(publisher);
        return "Id dodanego wydawnictwa to:" + publisher.getId();
    }
    @RequestMapping("/publisher/get/{id}")
    @ResponseBody
    public String getPublisher(@PathVariable Long id){
        Publisher publisher = publisherDao.findById(id);
        return publisher.toString();
    }

    @RequestMapping("/publisher/delete/{id}")
    @ResponseBody
    public String deletePublisher(@PathVariable Long id){
        publisherDao.delete(publisherDao.findById(id));
        return "Usunięto wydawnictwo";
    }

    @RequestMapping("/publisher/update/{id}/{name}")
    @ResponseBody
    public String updateBook(@PathVariable long id, @PathVariable String name ) {
        Publisher publisher = publisherDao.findById(id);
        publisher.setName(name);
        return publisher.toString();
    }

}
