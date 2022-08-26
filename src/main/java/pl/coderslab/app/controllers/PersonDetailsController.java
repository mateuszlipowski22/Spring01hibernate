package pl.coderslab.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.models.PersonDetails;
import pl.coderslab.app.repositories.PersonDao;
import pl.coderslab.app.repositories.PersonDetailsDao;

@Controller
public class PersonDetailsController {

    private final PersonDao personDao;
    private final pl.coderslab.app.repositories.PersonDetailsDao personDetailsDao;

    public PersonDetailsController(PersonDao personDao, PersonDetailsDao personDetailsDao) {
        this.personDao = personDao;
        this.personDetailsDao = personDetailsDao;
    }

    @RequestMapping("/personDetails/add")
    @ResponseBody
    public String hello() {

        PersonDetails personDetails = new PersonDetails();
        personDetails.setFirstName("zdzislaw");
        personDetails.setFirstName("zasada");

        personDetailsDao.savePersonDetails(personDetails);

        return "Id szczegoly osoby to:" + personDetails.getId();
    }

    @RequestMapping("/personDetails/get/{id}")
    @ResponseBody
    public String getPersonDetails(@PathVariable Long id) {
        PersonDetails personDetails = personDetailsDao.findById(id);
        return personDetails.toString();
    }

    @RequestMapping("/personDetails/delete/{id}")
    @ResponseBody
    public String deletePersonDetails(@PathVariable Long id) {
        personDetailsDao.delete(personDetailsDao.findById(id));
        return "Usunięto szególy osoby";
    }

    @RequestMapping("/personDetails/update/{id}/{name}")
    @ResponseBody
    public String updatePersonDetails(@PathVariable long id, @PathVariable String name) {
        PersonDetails personDetails = personDetailsDao.findById(id);
        personDetails.setFirstName(name);
        return personDetails.toString();
    }

}
