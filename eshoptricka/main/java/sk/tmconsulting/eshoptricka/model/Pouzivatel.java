package sk.tmconsulting.eshoptricka.model;


import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pouzivatel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String heslo;

    private String meno;

    private String priezvisko;

    @OneToMany(
            mappedBy="pouzivatel", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Objednavka> objednavky;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeslo() {
        return heslo;
    }

    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt(12); // Generovanie soli s parametrom pre pevnosť hasha
        String hashedPassword = BCrypt.hashpw(password, salt); // Zašifrovanie hesla
        return hashedPassword;
    }

    public void setHeslo(String heslo) {
        this.heslo = hashPassword(heslo);;
        //this.heslo = heslo;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }
}
