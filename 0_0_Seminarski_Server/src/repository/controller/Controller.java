/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.controller;


import operacija.evidencijaKursa.UcitajStavkeEvidencijeKursaSO;
import operacije.nastavnaJedinica.ObrisiNastavnaJedinicaSO;
import operacije.nastavnaJedinica.IzmeniNastavnaJedinicaSO;
import operacije.nastavnaJedinica.UbaciNastavnaJedinicaSO;
import operacije.nastavnaJedinica.UcitajNastavneJediniceSO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.EvidencijaKursa;
import model.Grupa;
import model.NastavnaJedinica;
import model.Kvalifikacija;
import model.Profesor;
import model.StavkaEvidencijeKursa;
import model.Ucenik;
import operacija.*;
import operacija.evidencijaKursa.IzmeniEvidencijaKursaSO;
import operacija.evidencijaKursa.KreirajEvidencijaKursaSO;
import operacija.evidencijaKursa.PretraziEvidencijaKursaSO;
import operacija.evidencijaKursa.UcitajEvidencijaKursaSO;
import operacija.ucenik.*;
import operacija.grupa.*;
import operacija.kvalifikacija.UbaciKvalifikacijaSO;
import operacija.kvalifikacija.UcitajKvalifikacijaSO;
import operacija.profesor.*;



/**
 *
 * @author dakik
 */


public class Controller {

    private static Controller instance;

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Profesor login(Profesor p) throws Exception {
        LogInOperacija operacija = new LogInOperacija();
        operacija.izvrsi(p, null);
        System.out.println("Klasa controller: " + operacija.getProfesor());
        return operacija.getProfesor();
    }

    public List<NastavnaJedinica> ucitajNastavneJedinice() throws Exception {
        UcitajNastavneJediniceSO operacija = new UcitajNastavneJediniceSO();
        operacija.izvrsi(null, null);
        return operacija.getNastavneJedinice();
    }

    public void obrisiNastavnaJedinica(NastavnaJedinica k) throws Exception {
        ObrisiNastavnaJedinicaSO operacija = new ObrisiNastavnaJedinicaSO();
        operacija.izvrsi(k, null);
    }

    public void dodajNastavnaJedinica(NastavnaJedinica noviKurs) throws Exception {
        UbaciNastavnaJedinicaSO operacija = new UbaciNastavnaJedinicaSO();
        operacija.izvrsi(noviKurs, null);
    }

    public void izmeniNastavnaJedinica(NastavnaJedinica kursZaIzmenu) throws Exception {
        IzmeniNastavnaJedinicaSO operacija = new IzmeniNastavnaJedinicaSO();
        operacija.izvrsi(kursZaIzmenu, null);
    }

    public List<Ucenik> ucitajUcenike() throws Exception {
        UcitajUcenikeSO operacija = new UcitajUcenikeSO();
        operacija.izvrsi(null, null);
        return operacija.getUcenici();
    }

    public void obrisiUcenik(Ucenik u) throws Exception {
        ObrisiUcenikSO operacija = new ObrisiUcenikSO();
        operacija.izvrsi(u, null);
    }

    public List<Grupa> ucitajGrupe() throws Exception {
        UcitajGrupeSO operacija = new UcitajGrupeSO();
        operacija.izvrsi(null, null);
        return operacija.getGrupe();
    }

    public void dodajUcenika(Ucenik noviUc) throws Exception {
        UbaciUcenikSO operacija = new UbaciUcenikSO();
        operacija.izvrsi(noviUc, null);
    }

    public void dodajGrupu(Grupa novaGrupa) throws Exception {
        UbaciGrupuSO operacija = new UbaciGrupuSO();
        operacija.izvrsi(novaGrupa, null);
    }

    public void izmeniUcenik(Ucenik ucenikZaIzmenu) throws Exception {
        IzmeniUcenikSO operacija = new IzmeniUcenikSO();
        operacija.izvrsi(ucenikZaIzmenu, null); 
    }

    public List<Kvalifikacija> ucitajKval() throws Exception {
        UcitajKvalifikacijaSO operacija = new UcitajKvalifikacijaSO();
        operacija.izvrsi(null, null);
        return operacija.getKvalifikacije();
    }

    public void dodajKval(Kvalifikacija novaKval) throws Exception {
        UbaciKvalifikacijaSO operacija = new UbaciKvalifikacijaSO();
        operacija.izvrsi(novaKval, null);
    }

    public List<EvidencijaKursa> ucitajEvidencijeKursa() throws Exception {
        UcitajEvidencijaKursaSO operacija = new UcitajEvidencijaKursaSO();
        operacija.izvrsi(null, null);
        return operacija.getEvidencije();
    }

    public List<StavkaEvidencijeKursa> ucitajStavkeEvidencijeKursa(EvidencijaKursa ek) throws Exception {
        UcitajStavkeEvidencijeKursaSO operacija = new UcitajStavkeEvidencijeKursaSO();
        operacija.izvrsi(ek, null);
        return operacija.getListaStavki();
    }

    public List<Profesor> ucitajProfesore() throws Exception {
        UcitajProfesorSO operacija = new UcitajProfesorSO();
        operacija.izvrsi(null, null);
        return operacija.getProfesori();
    }
    
    public void dodajEvidencijaKursa(EvidencijaKursa ek) throws Exception {
        KreirajEvidencijaKursaSO operacija = new KreirajEvidencijaKursaSO();
        operacija.izvrsi(ek, null);
    }

    public List<EvidencijaKursa> pretraziEvidencijuKursa(EvidencijaKursa EKZaPretragu) throws Exception {
        PretraziEvidencijaKursaSO operacija = new PretraziEvidencijaKursaSO();
        operacija.izvrsi(EKZaPretragu, null);
        return operacija.getEvidencije();
    }
    
    public void izmeniEvidencijuKursa(EvidencijaKursa EKZaIzmenu) throws Exception {
        IzmeniEvidencijaKursaSO operacija = new IzmeniEvidencijaKursaSO();
        operacija.izvrsi(EKZaIzmenu, null);
    }

    public void izmeniProfesora(Profesor pZaIzmenu) throws Exception {
        IzmeniProfesorSO operacija = new IzmeniProfesorSO();
        operacija.izvrsi(pZaIzmenu, null);
    }
    
}
