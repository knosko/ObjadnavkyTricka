package sk.tmconsulting.eshoptricka.model;

import javax.persistence.*;
import java.sql.Date;


@Entity
public class Objednavka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String popis;
    private Farba farba;
    private String znacka;
    private Velkost velkost;
    @Column(nullable = false)
    private int pocet;
    private Date datumObjednavky;
    private String menoZakaznika;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pouzivatel_id")
    private Pouzivatel pouzivatel;

/*    farby, značku, veľkosť, (príp. ďalšie atribúty) a objednané množstvo (počet kusov), dátum objednávky + meno zákazníka.*/

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }


    public String getZnacka() {
        return znacka;
    }

    public void setZnacka(String znacka) {
        this.znacka = znacka;
    }

    public Velkost getVelkost() {
        return velkost;
    }

    public void setVelkost(Velkost velkost) {
        this.velkost = velkost;
    }

    public int getPocet() {
        return pocet;
    }

    public void setPocet(int pocet) {
        this.pocet = pocet;
    }

    public Date getDatumObjednavky() {
        return datumObjednavky;
    }

    public void setDatumObjednavky(Date datumObjednavky) {
        this.datumObjednavky = datumObjednavky;
    }

    public String getMenoZakaznika() {
        return menoZakaznika;
    }

    public void setMenoZakaznika(String menoZakaznika) {
        this.menoZakaznika = menoZakaznika;
    }

    public Farba getFarba() {
        return farba;
    }

    public void setFarba(Farba farba) {
        this.farba = farba;
    }

    public Pouzivatel getPouzivatel() {
        return pouzivatel;
    }

    public void setPouzivatel(Pouzivatel pouzivatel) {
        this.pouzivatel = pouzivatel;
    }
}
