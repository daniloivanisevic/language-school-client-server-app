/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.evidencijaKursa;

import java.util.List;
import model.EvidencijaKursa;
import model.StavkaEvidencijeKursa;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author dakik
 */
public class UcitajStavkeEvidencijeKursaSO extends ApstraktnaGenerickaOperacija {

    private List<StavkaEvidencijeKursa> listaStavki;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof EvidencijaKursa)) {
            throw new Exception("Nije poslat ispravan objekat za pretragu stavki evidencija kursa.");
        }
    }
    
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        EvidencijaKursa ek = (EvidencijaKursa) param;
        String uslov = " JOIN nastavnaJedinica ON stavkaEvidencijeKursa.idNastavnaJedinica = nastavnaJedinica.id "
                + "WHERE stavkaEvidencijeKursa.idEvidencijaKursa = " + ek.getIdEvidencijaKursa();
        this.listaStavki = broker.getAll(new StavkaEvidencijeKursa(), uslov);
    }

    public List<StavkaEvidencijeKursa> getListaStavki() {
        return listaStavki;
    }

}
