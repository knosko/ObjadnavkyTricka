package sk.tmconsulting.eshoptricka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.tmconsulting.eshoptricka.model.Pouzivatel;

public interface PouzivatelRepository extends JpaRepository<Pouzivatel, Long> {
    Pouzivatel findByUsername(String username);
}
