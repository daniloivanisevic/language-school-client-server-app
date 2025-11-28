/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.evidencijaKursa;

import java.util.List;
import model.EvidencijaKursa;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author dakik
 */
public class PretraziEvidencijaKursaSO extends ApstraktnaGenerickaOperacija {

    private List<EvidencijaKursa> evidencije;

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (!(objekat instanceof EvidencijaKursa)) {
            throw new Exception("Greška, prosleđeni parametar mora biti objekat klase Evidencija kursa.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        EvidencijaKursa FindEvidencija = (EvidencijaKursa) objekat;
        
        StringBuilder whereKlauzula = new StringBuilder();
        
        
        // Uvek dodajem JOIN-ove, jer uvek ucitavam ucenika i profesora
        whereKlauzula.append(" JOIN ucenik ON evidencijaKursa.IdUcenik = ucenik.id JOIN "
                + "profesor ON evidencijaKursa.IdProfesor = profesor.id");
        
        // Dodajem WHERE klauzulu, počinjem sa 1=1 da bih lakše dodavao uslove sa AND
        whereKlauzula.append(" WHERE 1=1");
        
        // Dodajem uslove na osnovu popunjenih polja
        if (FindEvidencija.getIdEvidencijaKursa()!= 0) {
            whereKlauzula.append(" AND evidencijaKursa.idEvidencijaKursa = ").append(FindEvidencija.getIdEvidencijaKursa());
        }
        
        if (FindEvidencija.getUcenik()!= null) {
            whereKlauzula.append(" AND evidencijaKursa.IdUcenik = ").append(FindEvidencija.getUcenik().getIdUcenik());
        }
        
        if (FindEvidencija.getProfesor()!= null) {
            whereKlauzula.append(" AND evidencijaKursa.IdProfesor = ").append(FindEvidencija.getProfesor().getIdProfesor());
        }

        
        //whereKlauzula.append(" AND racun.obradjen = ").append(FindEvidencija.isObradjen() ? 1 : 0);

        try {
            evidencije = broker.getAll(new EvidencijaKursa(), whereKlauzula.toString());
        } catch (Exception e) {
            System.out.println("Greska u PretraziEvidencijeKursaSO: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public List<EvidencijaKursa> getEvidencije() {
        return evidencije;
    }

    public void setEvidencije(List<EvidencijaKursa> evidencije) {
        this.evidencije = evidencije;
    }
}
