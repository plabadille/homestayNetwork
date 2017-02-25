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
import model.HousingOffer;
import model.HousingOfferDB;

@Controller
public class HousingsController {

    @Autowired
    private HousingOfferDB housingOfferDB;

    @RequestMapping(value="/addHousing", method=RequestMethod.POST)
    public String addHousing(@RequestParam Map<String,String> requestParams, HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
        long userId = Utils.getConnectedUser(session).getId();
        long housingId;

        SQLHousingDB db = HousingsDBHandler.getDb();
        int surface = Integer.parseInt(requestParams.get("surface"));
        int nbRoom = Integer.parseInt(requestParams.get("nbRoom"));
        int gardenSurface = Integer.parseInt(requestParams.get("gardenSurface"));
        String isApartment = requestParams.get("isApartment");
        String address = requestParams.get("address");
        String country = requestParams.get("country");

        if (isApartment == null) {
            housingId = db.add(new Home(country, surface, nbRoom, address, gardenSurface));
        } else {
            housingId = db.add(new Apartment(country, surface, nbRoom, address));
        }

        this.housingOfferDB.initialize();
        this.housingOfferDB.create(new HousingOffer(housingId, userId));

        redirectAttributes.addFlashAttribute("message", "Ajout effectuée !");

        return "redirect:/accountManagement";
    }


    @RequestMapping(value={"/editHousing/{id}"}, method=RequestMethod.POST)
    public String editHousing (@PathVariable("id") long id, @RequestParam Map<String,String> requestParams, HttpSession session, RedirectAttributes redirectAttributes, Model model) throws Exception {
        SQLHousingDB db = HousingsDBHandler.getDb();

        int surface = Integer.parseInt(requestParams.get("surface"));
        int nbRoom = Integer.parseInt(requestParams.get("nbRoom"));
        int gardenSurface = Integer.parseInt(requestParams.get("gardenSurface"));
        String isApartment = requestParams.get("isApartment");
        String address = requestParams.get("address");
        String country = requestParams.get("country");

        Housing housing = db.find(id);
        housing.setCountry(country);
        housing.setSurface(surface);
        housing.setNbRoom(nbRoom);
        housing.setAddress(address);

        if (isApartment == null) {
            Home home = (Home) housing;
            home.setGardenSurface(gardenSurface);
            db.update(home);
        } else {
            db.update((Apartment) housing);
        }

        redirectAttributes.addFlashAttribute("message", "Modifications effectuée !");

        return "redirect:/editHousing/" + id;
    }

    @RequestMapping(value={"/editHousing/{id}"})
    public String editHousing (@PathVariable("id") long id, HttpSession session, Model model) throws Exception {
        SQLHousingDB db = HousingsDBHandler.getDb();
        Housing housing = db.find(id);
        model.addAttribute("isApartment", housing instanceof Apartment);
        model.addAttribute("housing", housing);
        model.addAttribute("offers", this.housingOfferDB.getAllOfferByHousing(id));
        return "editHousing";

    }
}
