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
import persons.Person;
import model.HousingOffer;
import model.HousingOfferDB;

@Controller
public class HousingOfferController {

    @Autowired
    private HousingOfferDB housingOfferDB;

    //addOffer
    @RequestMapping(value="/addOffer", method=RequestMethod.POST)
    public String addOffer(@RequestParam Map<String,String> requestParams, HttpSession session, RedirectAttributes redirectAttributes) {

        long idOffer = Long.parseLong(requestParams.get("idOffer"));
        long startTimestamp = Long.parseLong(requestParams.get("startDate"));
        long endTimestamp = Long.parseLong(requestParams.get("stopDate"));

        HousingOffer offer = this.housingOfferDB.find(idOffer);
        String message = null;

        if (offer.isRegistred()) { //stage 1
            offer.setBeginDate(startTimestamp);
            offer.setEndDate(endTimestamp);
            this.housingOfferDB.update(offer);

            message="L'offre a bien été ajouté";
        } else { //stage 2 or 3
            // *************
            //TO DO, check date conflict
            // *************
            HousingOffer newOffer = new HousingOffer(offer.getIdHousing(), offer.getIdOwner());
            newOffer.setBeginDate(startTimestamp);
            newOffer.setEndDate(endTimestamp);
            this.housingOfferDB.create(newOffer);

            message="L'offre a bien été ajouté";
        }

        redirectAttributes.addFlashAttribute("message",message);
        return "redirect:/accountManagement/" + offer.getIdOwner();

    }

    @RequestMapping(value="/editOffer", method=RequestMethod.POST)
    public String editOffer(@RequestParam Map<String,String> requestParams, HttpSession session, RedirectAttributes redirectAttributes) {

        long idOffer = Long.parseLong(requestParams.get("idOffer"));
        long startTimestamp = Long.parseLong(requestParams.get("startDate"));
        long endTimestamp = Long.parseLong(requestParams.get("stopDate"));

        HousingOffer offer = this.housingOfferDB.find(idOffer);

        offer.setBeginDate(startTimestamp);
        offer.setEndDate(endTimestamp);
        this.housingOfferDB.update(offer);

        String message="L'offre a bien été mise à jour";

        redirectAttributes.addFlashAttribute("message",message);
        return "redirect:/accountManagement/" + offer.getIdOwner();

    }

    @RequestMapping(value="deleteOffer", method=RequestMethod.GET)
    public String deleteOffer(@RequestParam("idOffer") long idOffer, HttpSession session, RedirectAttributes redirectAttributes) {

        HousingOffer offer = this.housingOfferDB.find(idOffer);
        long ownerId = offer.getIdOwner();
        this.housingOfferDB.delete(offer);
        String message="L'offre a bien été supprimée";
        if (Utils.isConnected(session)) {
            redirectAttributes.addFlashAttribute("message",message);
            return "redirect:/accountManagement/" + ownerId;
        } else { //an admin moderate the offer
            return "redirect:/adminPanel";
        }
        
    }

    @RequestMapping(value="bookOffer", method=RequestMethod.GET)
    public String bookOffer(@RequestParam("idOffer") long idOffer, HttpSession session, RedirectAttributes redirectAttributes) {
        long activeUser = (long) Utils.getConnectedUser(session).getId();

        HousingOffer offer = this.housingOfferDB.find(idOffer);
        
        offer.setIdGuest(activeUser);
        this.housingOfferDB.update(offer);
        String message="L'offre a bien été supprimée";
        
        redirectAttributes.addFlashAttribute("message",message);
        return "redirect:/accountManagement/" + activeUser;
    }
    

}
