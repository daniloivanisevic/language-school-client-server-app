/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.evidencijaKursa;

/**
 *
 * @author dakik
 */
import domen.ApstraktniDomenskiObjekat;
import model.EvidencijaKursa;
import model.StavkaEvidencijeKursa;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

public class KreirajEvidencijaKursaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof EvidencijaKursa)) {
            throw new Exception("Poslati objekat nije tipa Evidencija kursa!");
        }

        EvidencijaKursa evidencija = (EvidencijaKursa) objekat;
        if (evidencija.getStavke() == null || evidencija.getStavke().isEmpty()) {
            throw new Exception("Evidencija kursa mora da sadrzi barem jednu stavku.");
        }
        for (StavkaEvidencijeKursa stavka : evidencija.getStavke()) {
            if (stavka.getDatumPrisustva() != null
                    && (stavka.getDatumPrisustva().isBefore(evidencija.getDatumUpisa())
                    || stavka.getDatumPrisustva().isAfter(evidencija.getDatumZavrsavanja()))) {
                throw new Exception(
                        "Datum prisustva mora biti izmedju datuma upisa i datuma zavrsavanja."
                );
            }

        }

    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        EvidencijaKursa ek = (EvidencijaKursa) objekat;

        // 1. Sačuvaj glavni objekat EvidencijaKursa i dobij automatski generisani ID
        int idEK = broker.addReturnKey(ek);
        ek.setIdEvidencijaKursa(idEK);

        System.out.println("Novi ID Evidencije kursa je: " + ek.getIdEvidencijaKursa());

        // 2. Postavi ID evidencije na svaku stavku  i sačuvaj je
        for (StavkaEvidencijeKursa stavka : ek.getStavke()) {
            stavka.setEvidencija(ek);
            broker.add(stavka);
        }
    }

}
