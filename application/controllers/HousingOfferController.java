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
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException; 

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
    public String addOffer(@RequestParam Map<String,String> requestParams, HttpSession session, RedirectAttributes redirectAttributes) throws ParseException {


        long housingId = Long.parseLong(requestParams.get("housingId"));
        String startDate = requestParams.get("startDate");
        String endDate = requestParams.get("endDate");

                System.out.println("TOOOOOOTOOOOOO:" + housingId);
                System.out.println("deb: "+startDate+" fin: "+endDate);


        int offerNb = this.housingOfferDB.countHousingOfferById(housingId);

        System.out.println("nb:"+offerNb);
        HousingOffer offer = this.housingOfferDB.findByHousingId(housingId);
        String message = null;
        long startTimestamp;
        long endTimestamp;

        if (this.dateFormatIsCorrect(startDate) && this.dateFormatIsCorrect(endDate)) {
            try {
                startTimestamp = this.createTimestamp(startDate);
                endTimestamp = this.createTimestamp(endDate);
            } catch (Throwable e) {
                throw e;
            }

            if (offerNb == 1 && offer.isRegistred()) { //stage 1
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
        } else { //incorrect date format
            message="Le format de date n'est pas correct (JJ/MM/AAAA)";
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
    
    private long createTimestamp(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date timestamp = dateFormat.parse(date);
            return timestamp.getTime();
        } catch (Exception e) {
            throw e;
        }
    }

    private boolean dateFormatIsCorrect(String date) {
        String[] parts = date.split("/");

        if (parts.length != 3) {
            return false;
        }
        if (parts[0].length() != 2) {
            return false;
        }
        if (parts[1].length() != 2) {
            return false;
        }
        if (parts[2].length() != 4) {
            return false;
        }

        return true;
    }

}
