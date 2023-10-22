package sk.tmconsulting.eshoptricka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.tmconsulting.eshoptricka.model.Objednavka;
import sk.tmconsulting.eshoptricka.model.Pouzivatel;
import sk.tmconsulting.eshoptricka.repository.ObjednavkaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObjednavkaService {
    @Autowired // Tato anotacia spravi ObjednavkaRepository objednavkaRepositoryObjekt = new ObjednavkaRepository
    private ObjednavkaRepository objednavkaRepositoryObjekt;

    public void ulozObjednavku(Objednavka objednavkaObjekt) {
        objednavkaRepositoryObjekt.save(objednavkaObjekt);
    }

    public List<Objednavka> ziskajVsetkyObjednavky() {
        return objednavkaRepositoryObjekt.findAll();
    }

    public void aktualizujObjednavku(Objednavka objednavkaObjekt) {
        objednavkaRepositoryObjekt.save(objednavkaObjekt);
    }

    public Objednavka ziskajObjednavkuPodlaId(long id) {
        return objednavkaRepositoryObjekt.findById(id).get();
    }

    public void odstranObjednavku(long id) {
        objednavkaRepositoryObjekt.deleteById(id);
    }

    public List<Objednavka> ziskajObjednavkyPodlaPouzivatela(String username) {
        return objednavkaRepositoryObjekt.findByUsername(username);
    }

}
