package controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

import java.util.List;

import persons.Person;
import persons.PersonDB;
import model.HousingOffer;
import model.HousingOfferDB;

@Controller
public class VisualiseController {

    @Autowired
    private PersonDB personDB;
    @Autowired
    private HousingOfferDB housingOfferDB;

    @RequestMapping(value="/")
    public String welcome () {
         return "redirect:/home";
    }

    @RequestMapping(value={"/home"})
    public String visualiseAll (HttpSession session) {
        this.personDB.initialize();
        Utils.initializeSession(session,this.personDB);
        return "home";
    }

    @RequestMapping(value={"/adminPanel"})
    public String adminPanel (HttpSession session) {
        this.personDB.initialize();
        Utils.initializeSession(session,this.personDB);
        return "adminPanel";
    }

    @RequestMapping(value={"/searchProperty"})
    public String searchProperty (HttpSession session) {
        this.personDB.initialize();
        Utils.initializeSession(session,this.personDB);
        return "searchProperty";
    }

    @RequestMapping(value="/accountManagement/{id}")
    public String accountManagement (@PathVariable("id") long id, HttpSession session, Model model) {
        this.housingOfferDB.initialize();

        for (Person person: (List<Person>)session.getAttribute("allPersons")) {
            if (person.getId().equals(id)) {
                model.addAttribute("user",person);
                break;
            }
        }

        return "accountManagement";
    }

}
