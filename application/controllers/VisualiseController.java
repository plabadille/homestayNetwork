package controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VisualiseController {

    @RequestMapping(value="/")
    public String welcome () {
         return "redirect:/home";
    }

    @RequestMapping(value={"/home"})
    public String visualiseAll (HttpSession session) {
        return "home";
    }

}
