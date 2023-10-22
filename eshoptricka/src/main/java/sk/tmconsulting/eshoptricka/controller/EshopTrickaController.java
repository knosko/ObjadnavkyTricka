package sk.tmconsulting.eshoptricka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sk.tmconsulting.eshoptricka.model.Objednavka;
import sk.tmconsulting.eshoptricka.model.Pouzivatel;
import sk.tmconsulting.eshoptricka.service.ObjednavkaService;
import sk.tmconsulting.eshoptricka.service.PouzivatelService;

@Controller
public class EshopTrickaController {
    // Dependency Injection
    @Autowired // on spravi na pozadi ObjednavkaService objednavkaServiceObjekt = new ObjednavkaService();
    ObjednavkaService objednavkaServiceObjekt;

    @Autowired  // Dependency Injection
    PouzivatelService pouzivatelServiceObjekt;

    @GetMapping("/")
    public String uvodnaStranka() {
        return "index";
    }

    @GetMapping("/pridaj-objednavku") // URI adresa
    public String pridajNovuObjednavku(Model model) { // Nazov metody moze byt akykolvek, ale moze byt aj zhodny s URI adresou
        Objednavka objednavkaObjekt = new Objednavka(); // Najprv vytvarame prazdnu objednavku
        model.addAttribute("objednavkaObjektThymeleaf", objednavkaObjekt); // Nazov atributu mozeme nazvat akokolvek, ale potom by sme mali s nim pracovat aj v HTML, resp. Thymeleaf stranke

        // Ziskame aktualne prihlaseneho pouzivatela
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usernamePouzivatela = auth.getName(); // Ziskame username prihlaseneho pouzivatela

        Pouzivatel prihlasenyPouzivatel = pouzivatelServiceObjekt.najdiPodlaUsername(usernamePouzivatela);
        objednavkaObjekt.setPouzivatel(prihlasenyPouzivatel);
        model.addAttribute("pouzivatelObjektThymeleaf", prihlasenyPouzivatel);
        return "pridaj-novu-objednavku"; // Odkaz na stranku s pridanim novej objednavky
    }

    @PostMapping("/uloz-objednavku")
    public String ulozObjednavku(@ModelAttribute("objednavkaObjektThymeleaf") Objednavka objednavkaObjekt) {
        // objednavkaObjekt musi byt ulozena
        objednavkaServiceObjekt.ulozObjednavku(objednavkaObjekt);
        return "uloz-novu-objednavku";
    }

    @PostMapping("/uloz-upravenu-objednavku")
    public String ulozUpravenuObjednavku(@ModelAttribute("objednavkaObjektThymeleaf") Objednavka objednavkaObjekt) {
        // objednavkaObjekt musi byt ulozena
        objednavkaServiceObjekt.aktualizujObjednavku(objednavkaObjekt);
        return "uloz-upravenu-objednavku";
    }

    @GetMapping("/zobraz-vsetky-objednavky")
    public String zobrazObjednavky(Model model) {
        // Ziskame aktualne prihlaseneho pouzivatela
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usernamePouzivatela = auth.getName(); // Ziskame username prihlaseneho pouzivatela
        model.addAttribute("vsetkyObjednavkyThymeleaf", objednavkaServiceObjekt.ziskajObjednavkyPodlaPouzivatela(usernamePouzivatela));
        return "zobraz-vsetky-objednavky";
    }

    @GetMapping("/uprav-objednavku/{id}")
    public String upravObjednavku(@PathVariable(value="id") long id, Model model) {
        Objednavka objednavkaObjektPodlaId = objednavkaServiceObjekt.ziskajObjednavkuPodlaId(id);
        model.addAttribute("objednavkaObjektPodlaIdThymeleaf", objednavkaObjektPodlaId);
        return "uprav-objednavku";
    }

    @GetMapping("odstran-objednavku/{id}")
    public String odstranObjednavku(@PathVariable(value="id") long id) {
        objednavkaServiceObjekt.odstranObjednavku(id);
        return "objednavka-odstranena";
    }

    @GetMapping("/login") // URI adresa, ktoru vidim v internetovom prehliadaci. Tu nam vygeneroval WebSecurityConfig v metode configure a my musime na to nejakym sposobom reagovat ...
    public String loginFormular() {
        return "login"; // ... cize zobrazime login.html, ktory sa nachadza v templates
    }

/*    @GetMapping("/logout")
    public String logoutFormular() {
        return "logout"; // Presmerovanie na hlavnu stranku
    }*/

    @GetMapping("/registruj-pouzivatela")
    public String registrujPouzivatela(Model model) {
        Pouzivatel pouzivatelObjekt = new Pouzivatel();
        model.addAttribute("pouzivatelObjektThymeleaf", pouzivatelObjekt);
        return "registruj-noveho-pouzivatela";
    }

    @PostMapping("/uloz-pouzivatela") // PostMapping ocakava, resp. spracuje obsah formularovych prvkov
    public String ulozPouzivatela(@ModelAttribute("pouzivatelObjektThymeleaf") Pouzivatel pouzivatelObjekt) {
        // objednavkaObjekt musi byt ulozena
        pouzivatelServiceObjekt.ulozPouzivatela(pouzivatelObjekt);
        return "uloz-noveho-pouzivatela";
    }

}
