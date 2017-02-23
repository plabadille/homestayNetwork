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
public class ConnectionController {
    
    @Autowired
    private PersonDB personDB;

    @RequestMapping(value="/connexion", method=RequestMethod.POST)
    public String userConnection(@RequestParam Map<String,String> requestParams, HttpSession session, RedirectAttributes redirectAttributes) {
        
        String email = requestParams.get("email");
        String password = requestParams.get("password");

        String message = null;

        if (!email.isEmpty() && !password.isEmpty()) {
            Utils.initializeSession(session,this.personDB);

            if (!Utils.isConnected(session)) {
                if (this.personDB.isValid(email, password)) {
                    Person person = this.personDB.find(email);
                    Utils.connectUser(session, person);
                    return "redirect:/accountManagement/" + person.getId();
                } else {
                    message="Votre mot de passe ou email est incorrect.";
                }
            } else {
                message="Vous êtes déjà connecté.";
            }
        } else {
            message="Il y a des erreurs dans le formulaire.";
        }

        redirectAttributes.addFlashAttribute("message",message);
        return "redirect:/home";
    }

    @RequestMapping(value="/disconect", method=RequestMethod.GET)
    public String userDisconection(HttpSession session) {
        if (Utils.isConnected(session)) {
            Utils.disconectUser(session);
            if ((boolean)session.getAttribute("admin")) {
                Utils.disconectAdmin(session);
                return "redirect:/adminPanel";
            }
        } else if ((boolean)session.getAttribute("admin")) {
            Utils.disconectAdmin(session);
            return "redirect:/adminPanel";
        }
        return "redirect:/home";
    }

    @RequestMapping(value="/adminConnexion", method=RequestMethod.POST)
    public String adminConnection(@RequestParam Map<String,String> requestParams, HttpSession session, RedirectAttributes redirectAttributes) {
       
        String login = requestParams.get("login");
        String password = requestParams.get("password");

        String message = null;

        if (!login.isEmpty() && !password.isEmpty()) {
            // TEMPORARY
            if (login.equals("admin") && password.equals("admin")) {
                Utils.connectAdmin(session);
            } else {
                message="Combinaison incorrecte";
            }
        } else {
            message="Il y a des erreurs dans le formulaire.";
        }

        redirectAttributes.addFlashAttribute("message",message);
        return "redirect:/adminPanel";
    }

   
}
