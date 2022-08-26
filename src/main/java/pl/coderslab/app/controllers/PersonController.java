package pl.coderslab.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.models.*;
import pl.coderslab.app.repositories.PersonDao;
import pl.coderslab.app.repositories.PersonDetailsDao;

@Controller
public class PersonController {

    private final PersonDao personDao;
    private final PersonDetailsDao personDetailsDao;

    public PersonController(PersonDao personDao, PersonDetailsDao personDetailsDao) {
        this.personDao = personDao;
        this.personDetailsDao = personDetailsDao;
    }

    @RequestMapping("/person/add")
    @ResponseBody
    public String hello() {

        Person person = new Person();
        person.setLogin("12345");
        person.setEmail("222@www");
        person.setPassword("sasdasd");

        PersonDetails personDetails = new PersonDetails();
        personDetails.setFirstName("zdzislaw");
        personDetails.setFirstName("zasada");
        personDetails.setPerson(person);


        person.setPersonDetails(personDetails);
        personDao.savePerson(person);


        return "Id dodanej osoby to:" + person.getId();
    }

    @RequestMapping("/person/get/{id}")
    @ResponseBody
    public String getPerson(@PathVariable Long id){
        Person person = personDao.findById(id);
        return person.toString();
    }

    @RequestMapping("/person/delete/{id}")
    @ResponseBody
    public String deletePerson(@PathVariable Long id){
        personDao.delete(personDao.findById(id));
        return "Usunięto Osobe";
    }

    @RequestMapping("/person/update/{id}/{email}")
    @ResponseBody
    public String updatePerson(@PathVariable long id, @PathVariable String email ) {
        Person person = personDao.findById(id);
        person.setEmail(email);
        return person.toString();
    }

}
