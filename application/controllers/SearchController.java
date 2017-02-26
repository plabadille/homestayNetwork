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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Map;

import housings.Home;
import housings.Apartment;
import housings.Housing;
import housings.HousingsDBHandler;
import housings.SQLHousingDB;
import model.HousingOffer;
import model.HousingOfferDB;

@Controller
public class SearchController {

    @Autowired
    private HousingOfferDB housingOfferDB;

    @RequestMapping(value={"/searchProperty"})
    public String searchProperty(HttpSession session) {
        return "searchProperty";
    }

    @RequestMapping(value={"/searchProperty"}, method=RequestMethod.POST)
    public String searchProperty(@RequestParam Map<String,String> requestParams, HttpSession session, Model model) throws Exception {
        long userId = Utils.getConnectedUser(session).getId();
        String country = requestParams.get("country");
        long begin = createTimestamp(requestParams.get("startDate"));
        long end = createTimestamp(requestParams.get("endDate"));

        this.housingOfferDB.initialize();
        Collection<HousingOffer> offers = this.housingOfferDB.getAllAvailableOffers(begin, end, userId);
        SQLHousingDB db = HousingsDBHandler.getDb();

        Collection<HousingOffer> filteredOffers = new ArrayList<HousingOffer>();
        Collection<Housing> filteredHousings = new ArrayList<Housing>();
        for (HousingOffer offer : offers) {
            Housing housing = db.find(offer.getIdHousing());
            if (housing.getCountry().equals(country)) {
                filteredOffers.add(offer);
                filteredHousings.add(db.find(offer.getIdHousing()));
            }
        }

        model.addAttribute("offers", filteredOffers);
        model.addAttribute("housings", filteredHousings);

        return "searchProperty";
    }

    private long createTimestamp(String date) throws Exception {
        return new SimpleDateFormat("dd/MM/yyyy").parse(date).getTime();
    }
}
