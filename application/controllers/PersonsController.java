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
        String password = requestParams.get("password");
        String equalPassword = requestParams.get("equalPassword");

        String message = null;

        if (password.equals(equalPassword)) {
            if (!name.isEmpty() && !firstName.isEmpty() && !email.isEmpty()) {
                Utils.initializeSession(session,this.personDB);
                //we create the new user
                Person person = new Person(name, firstName, email, password);
                //we add this user in the db
                this.personDB.create(person);
                //we update the session storage
                ((List<Person>)session.getAttribute("allPersons")).add(person);

                message="L'utilisateur " + firstName + " " + name + " a bien été ajouté.";
            } else {
                message="Il y a des erreurs dans le formulaire.";
            }
        } else {
            message="Les deux mots de passe ne concorde pas.";
        }
        redirectAttributes.addFlashAttribute("message",message);
        return "redirect:/home";
    }

    @RequestMapping(value="deleteUser", method=RequestMethod.GET)
    public String addUser(@RequestParam("id") long id, HttpSession session, RedirectAttributes redirectAttributes) {

        Utils.initializeSession(session,this.personDB);
        String message = null;

        if (personDB.personExist(id)) {
            //we delete this user in the db
            this.personDB.delete(id);
            //we update the session storage
            int i = 0;
            for (Person user: (List<Person>)session.getAttribute("allPersons")) {
                if (user.getId().equals(id)) {
                    ((List<Person>)session.getAttribute("allPersons")).remove(i);
                    break;
                }
                i++;
            }
            message="L'utilisateur a bien été supprimé";
        } else {
            message="Fatal error, unknow user.";
        }
        redirectAttributes.addFlashAttribute("message",message);
        return "redirect:/adminPanel";
    }

    @RequestMapping(value="/accountManagement/editUser", method=RequestMethod.POST)
    public String editUser(@RequestParam Map<String,String> requestParams, HttpSession session, RedirectAttributes redirectAttributes) {

        String name = requestParams.get("name");
        String firstName = requestParams.get("firstName");
        String email = requestParams.get("email");
        long id = Long.parseLong(requestParams.get("id"));

        Utils.initializeSession(session,this.personDB);
        String message = null;

        if (!name.isEmpty() && !firstName.isEmpty() && !email.isEmpty()) {
            //we create the new user
            Person person = this.personDB.find(id);
            person.setName(name);
            person.setFirstName(firstName);
            person.setEmail(email);
            //we add this user in the db
            this.personDB.update(id, person);
            //we update the session storage
            int i = 0;
            for (Person user: (List<Person>)session.getAttribute("allPersons")) {
                if (user.getId().equals(id)) {
                    ((List<Person>)session.getAttribute("allPersons")).remove(i);
                    break;
                }
                i++;
            }
            ((List<Person>)session.getAttribute("allPersons")).add(person);

            message="L'utilisateur " + firstName + " " + name + " a bien été modifié.";
        } else {
            message="Il y a des erreurs dans le formulaire.";
        }
        redirectAttributes.addFlashAttribute("message",message);
        return "redirect:/accountManagement";
    }

    @RequestMapping(value="/accountManagement/updatePassword", method=RequestMethod.POST)
    public String updatePassword(@RequestParam Map<String,String> requestParams, HttpSession session, RedirectAttributes redirectAttributes) {

        String password = requestParams.get("password");
        String newPassword = requestParams.get("newPassword");
        String equalPassword = requestParams.get("equalPassword");
        long id = Long.parseLong(requestParams.get("id"));

        String message = null;

        if (!password.isEmpty() && !newPassword.isEmpty() && !equalPassword.isEmpty() ) {
            if (!password.equals(newPassword)) {
                if (newPassword.equals(equalPassword)) {
                    Utils.initializeSession(session,this.personDB);

                    if (this.personDB.isValid(id, password)) {
                        //we update the user password
                        this.personDB.updatePassword(id, newPassword);
                        //we update the session storage
                        Person person = this.personDB.find(id);
                        int i = 0;
                        for (Person user: (List<Person>)session.getAttribute("allPersons")) {
                            if (user.getId().equals(id)) {
                                ((List<Person>)session.getAttribute("allPersons")).remove(i);
                                break;
                            }
                            i++;
                        }
                        ((List<Person>)session.getAttribute("allPersons")).add(person);
                    } else {
                        message="Le mot de passe actuel est incorrect";
                    }
                } else {
                    message="La paire de mot de passe est différente, veuillez les saisir à nouveau";
                }
            } else {
                message="Le nouveau mot de passe est le même que l'ancien";
            }
        }

        redirectAttributes.addFlashAttribute("message",message);
        return "redirect:/accountManagement";
    }

}
