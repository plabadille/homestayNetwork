package controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import persons.Person;
import persons.PersonDB;
import housings.Housing;
import housings.HousingsDBHandler;
import housings.SQLHousingDB;
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
    public String visualiseAll(HttpSession session) {
        this.personDB.initialize();
        this.housingOfferDB.initialize();
        Utils.initializeSession(session,this.personDB);
        return "home";
    }

    @RequestMapping(value={"/adminPanel"})
    public String adminPanel(HttpSession session) {
        this.personDB.initialize();
        this.housingOfferDB.initialize();
        //TO DO: update model session storage (getAllOffers)
        Utils.initializeSession(session,this.personDB);
        return "adminPanel";
    }

    @RequestMapping(value={"/searchProperty"})
    public String searchProperty(HttpSession session) {
        this.personDB.initialize();
        this.housingOfferDB.initialize();
        //TO DO: update model session storage (getAllAvailableOffers)
        Utils.initializeSession(session,this.personDB);
        return "searchProperty";
    }

    @RequestMapping(value="/accountManagement")
    public String accountManagement(HttpSession session, Model model) throws Exception {
        long userId = Utils.getConnectedUser(session).getId();
        this.housingOfferDB.initialize();

        SQLHousingDB db = HousingsDBHandler.getDb();

        Collection<HousingOffer> housingOffers = this.housingOfferDB.getAllUserHousing(userId);
        List<Housing> housings = new ArrayList<Housing>();
        for (HousingOffer housingOffer : housingOffers) {
            Housing housing = db.find(housingOffer.getIdHousing());
            if (!housings.contains(housing)) {
                housings.add(housing);
            }
        }
        model.addAttribute("housings", housings);

        for (Person person: (List<Person>)session.getAttribute("allPersons")) {
            if (person.getId().equals(userId)) {
                model.addAttribute("user",person);
                break;
            }
        }

        return "accountManagement";
    }

}
