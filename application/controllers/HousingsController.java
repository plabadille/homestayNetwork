package controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

import housings.Home;
import housings.Apartment;
import housings.Housing;
import housings.HousingsDBHandler;
import housings.SQLHousingDB;

@Controller
public class HousingsController {
    @RequestMapping(value="/addHousing", method=RequestMethod.POST)
    public String addHousing(@RequestParam Map<String,String> requestParams, HttpSession session, RedirectAttributes redirectAttributes) throws Exception {

        SQLHousingDB db = HousingsDBHandler.getDb();
        int surface = Integer.parseInt(requestParams.get("surface"));
        int nbRoom = Integer.parseInt(requestParams.get("nbRoom"));
        int gardenSurface = Integer.parseInt(requestParams.get("gardenSurface"));
        String isApartment = requestParams.get("isApartment");
        String address = requestParams.get("address");
        String country = requestParams.get("country");

        if (isApartment == null) {
            db.add(new Home(country, surface, nbRoom, address, gardenSurface));
        } else {
            db.add(new Apartment(country, surface, nbRoom, address));
        }

        return "redirect:/home";
    }


    @RequestMapping(value={"/editHousing/{id}"}, method=RequestMethod.POST)
    public String editHousing (@PathVariable("id") long id, HttpSession session, Model model) throws Exception {

        SQLHousingDB db = HousingsDBHandler.getDb();
        int id = Integer.parseInt(requestParams.get("id"));
        int surface = Integer.parseInt(requestParams.get("surface"));
        int nbRoom = Integer.parseInt(requestParams.get("nbRoom"));
        int gardenSurface = Integer.parseInt(requestParams.get("gardenSurface"));
        String isApartment = requestParams.get("isApartment");
        String address = requestParams.get("address");
        String country = requestParams.get("country");

        if (isApartment == null) {
            db.update(new Home(id, country, surface, nbRoom, address, gardenSurface));
        } else {
            db.update(new Apartment(id, country, surface, nbRoom, address));
        }

        return "redirect:/home";
    }

    @RequestMapping(value={"/editHousing/{id}"})
    public String editHousing (@PathVariable("id") long id, HttpSession session, Model model) {
        // TODO: Retreieve the housing
        Housing housing = new Home("France", 80, 4, "6 rue de la patinoire", 120);
        model.addAttribute("isApartment", housing instanceof Apartment);
        model.addAttribute("housing", housing);
        return "editHousing";
    }
}
