package sk.tmconsulting.eshoptricka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.tmconsulting.eshoptricka.model.Pouzivatel;
import sk.tmconsulting.eshoptricka.repository.PouzivatelRepository;

@Service
public class PouzivatelService {
    @Autowired
    PouzivatelRepository pouzivatelRepositoryObjekt;

    public void ulozPouzivatela(Pouzivatel pouzivatelObjekt) {
        pouzivatelRepositoryObjekt.save(pouzivatelObjekt);
    }

    public Pouzivatel najdiPodlaUsername(String username) {
        return pouzivatelRepositoryObjekt.findByUsername(username);
    }
}
