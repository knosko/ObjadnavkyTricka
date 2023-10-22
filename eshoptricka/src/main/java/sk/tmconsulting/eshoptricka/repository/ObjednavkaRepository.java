package sk.tmconsulting.eshoptricka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sk.tmconsulting.eshoptricka.model.Objednavka;

import java.util.List;

public interface ObjednavkaRepository extends JpaRepository<Objednavka, Long> {
    @Query(value = "SELECT objednavka.* FROM objednavka INNER JOIN pouzivatel ON objednavka.pouzivatel_id = pouzivatel.id WHERE username = ?1", nativeQuery = true)
    List<Objednavka> findByUsername(String username);
}
