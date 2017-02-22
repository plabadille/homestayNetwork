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
public class AddPersonsController {
    
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
            personDB.create(person);
            //we update the session storage
            ((List<Person>)session.getAttribute("allPersons")).add(person);
            
            message="L'utilisateur " + firstName + " " + name + " a bien &eacute;t&eacute; ajout&eacute;";
        } else {
            message="Il y a des erreurs dans le formulaire.";
        }
        redirectAttributes.addFlashAttribute("message",message);
        return "redirect:/home";
    }

}
