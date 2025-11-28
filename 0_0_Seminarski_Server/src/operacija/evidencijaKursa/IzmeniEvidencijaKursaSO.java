package operacija.evidencijaKursa;

import java.util.List;
import model.EvidencijaKursa;
import model.StavkaEvidencijeKursa;
import operacija.ApstraktnaGenerickaOperacija;

public class IzmeniEvidencijaKursaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof EvidencijaKursa)) {
            throw new Exception("Sistem ne može da zapamti evidenciju kursa.");
        }

        EvidencijaKursa evidencija = (EvidencijaKursa) objekat;

        if (evidencija.getUcenik() == null || evidencija.getProfesor() == null) {
            throw new Exception("Morate odabrati učenika i profesora za izmenu evidencije.");
        }

        if (evidencija.getStavke() == null || evidencija.getStavke().isEmpty()) {
            throw new Exception("Evidencija kursa mora da sadrži barem jednu stavku evidencije kursa.");
        }

        for (StavkaEvidencijeKursa stavka : evidencija.getStavke()) {
            if (stavka.getDatumPrisustva() == null) {
                continue;
            }

            if (stavka.getDatumPrisustva().isBefore(evidencija.getDatumUpisa())
                    || stavka.getDatumPrisustva().isAfter(evidencija.getDatumZavrsavanja())) {
                throw new Exception(
                        "Datum prisustva (" + stavka.getDatumPrisustva()
                        + ") mora biti između datuma upisa (" + evidencija.getDatumUpisa()
                        + ") i završetka (" + evidencija.getDatumZavrsavanja() + ").");
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        EvidencijaKursa ek = (EvidencijaKursa) objekat;

        // izmena osnovne evidencije
        broker.edit(ek);

        
        List<StavkaEvidencijeKursa> stavkeServer = broker.getAll(
                new StavkaEvidencijeKursa(),
                " JOIN nastavnaJedinica ON stavkaEvidencijeKursa.idNastavnaJedinica = nastavnaJedinica.id "
                + "WHERE stavkaEvidencijeKursa.idEvidencijaKursa = " + ek.getIdEvidencijaKursa());

        List<StavkaEvidencijeKursa> stavkeKlijent = ek.getStavke();

        // azuriranje i brisanje
        for (StavkaEvidencijeKursa stavkaServer : stavkeServer) {
            boolean postojiNaKlijentu = false;
            for (StavkaEvidencijeKursa stavkaKlijent : stavkeKlijent) {
                if (stavkaServer.getRb() == stavkaKlijent.getRb()) {
                    postojiNaKlijentu = true;
                    stavkaKlijent.setEvidencija(ek);
                    broker.edit(stavkaKlijent);
                    break;
                }
            }
            if (!postojiNaKlijentu) {
                broker.delete(stavkaServer);
            }
        }

        // dodavanje novih
        for (StavkaEvidencijeKursa stavkaKlijent : stavkeKlijent) {
            boolean postojiNaServeru = false;
            for (StavkaEvidencijeKursa stavkaServer : stavkeServer) {
                if (stavkaKlijent.getRb() == stavkaServer.getRb()) {
                    postojiNaServeru = true;
                    break;
                }
            }
            if (!postojiNaServeru) {
                stavkaKlijent.setEvidencija(ek);
                broker.add(stavkaKlijent);
            }
        }

    }
}
