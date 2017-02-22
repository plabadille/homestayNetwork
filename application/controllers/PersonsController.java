package controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import persons.Person;
import persons.PersonDB;

@Controller
public class PersonsController {
    
    @Autowired
    private PersonDB personDB;

    @RequestMapping(value="/addUser", method=RequestMethod.POST)
    public String addUser(@RequestParam Map<String,String> requestParams, HttpSession session, RedirectAttributes redirectAttributes) {
        
        String name = requestParams.get("name");
        String firstName = requestParams.get("firstName");
        String email = requestParams.get("email");

        Utils.initializeSession(session,this.personDB);
        String message = null;

        if (!name.isEmpty() && !firstName.isEmpty() && !email.isEmpty()) {
            //we create the new user
            Person person = new Person(name, firstName, email);
            //we add this user in the db
            this.personDB.create(person);
            //we update the session storage
            ((List<Person>)session.getAttribute("allPersons")).add(person);
            
            message="L'utilisateur " + firstName + " " + name + " a bien été ajouté.";
        } else {
            message="Il y a des erreurs dans le formulaire.";
        }
        redirectAttributes.addFlashAttribute("message",message);
        return "redirect:/home";
    }

    //doesn't work: the user is find but the hibernate method doesn't work
    //TO DO: debug personDB.delete(String email)
    @RequestMapping(value="/deleteUser", method=RequestMethod.GET)
    public String addUser(@RequestParam("email") String email, HttpSession session, RedirectAttributes redirectAttributes) {

        Utils.initializeSession(session,this.personDB);
        String message = null;

        if (!email.isEmpty()) {
            //we get the user
            Person person = personDB.find(email);
            //we delete this user in the db
            this.personDB.delete(email);
            //we update the session storage
            ((List<Person>)session.getAttribute("allPersons")).remove(person);
            
            message="L'utilisateur " + person.getFirstName() + " " + person.getName() + " a bien été supprimé";
        } else {
            message="Fatal error, unknow user.";
        }
        redirectAttributes.addFlashAttribute("message",message);
        return "redirect:/home";
    }

}
