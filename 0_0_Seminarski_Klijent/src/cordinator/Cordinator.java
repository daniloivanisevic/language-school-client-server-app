/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cordinator;

import controllers.DodajEvidencijuKursaController;
import controllers.DodajGrupuController;
import controllers.DodajNastavnuJedinicuController;
import controllers.DodajKvalifikacijuController;
import controllers.DodajUcenikaController;
import controllers.GlavnaFormController;
import controllers.IzmenaProfesoraController;
import controllers.LoginController;
import controllers.PrikazEvidencijeKursaController;
import controllers.PrikazNastavneJediniceController;
import controllers.PrikazKvalifikacijaController;
import controllers.PrikazStavkiEvidencijeKursaController;
import controllers.PrikazUcenikaController;
import forme.DodajEvidencijuKursaForma;
import forme.DodajGrupuForma;
import forme.DodajKvalifikacijuForma;
import forme.DodajNastavnuJedinicuForma;
import forme.DodajUcenikaForma;
import forme.FormaMod;
import forme.GlavnaForma;
import forme.PrikazNastavneJediniceForma;
import forme.PrikazUcenikaForma;
import java.util.HashMap;
import java.util.Map;
import model.EvidencijaKursa;
import forme.IzmenaProfesoraForma; 
import forme.LogInForma;
import forme.PrikazEvidencijeKursaForma;
import forme.PrikazKvalifikacijaForma;
import forme.PrikazStavkiEvidencijeKursaForma;
import model.Profesor;

/**
 *
 * @author dakik
 */


public class Cordinator {
    private static Cordinator instance;
    private Profesor ulogovani;
    private LoginController loginController;
    private GlavnaFormController glavnaFormController;
    private PrikazNastavneJediniceController prikazNastavneJediniceController;
    private DodajNastavnuJedinicuController dodajNastavnaJedinicaController;
    private DodajUcenikaController dodajUcenikaController;
    private DodajGrupuController dodajGrupuController;
    private PrikazKvalifikacijaController prikazKvalController;
    private DodajKvalifikacijuController dodajKvalController;
    private PrikazEvidencijeKursaController prikazEKController; 
    private PrikazStavkiEvidencijeKursaController prikazStavkiEKController;
    private DodajEvidencijuKursaController dodajEKController; 
    private IzmenaProfesoraController ipController;
    private Map<String, Object> parametri; 
    private PrikazUcenikaController prikazUcenikaControllera;

    private Cordinator() {
        parametri = new HashMap<>();
    }

    public static Cordinator getInstance() {
        if(instance == null)
            instance = new Cordinator();
        return instance;
    }

    public Profesor getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Profesor ulogovani) {
        this.ulogovani = ulogovani;
    }
    
    public void dodajParam(String s, Object o){
        parametri.put(s, o);
    }
    
    public Object vratiParam(String s){
        return parametri.get(s);
    }

    public void otvoriLogInFormu() {
        loginController = new LoginController(new LogInForma());
        loginController.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        glavnaFormController = new GlavnaFormController(new GlavnaForma());
        glavnaFormController.otvoriFormu();
    }
    
    public void otvoriDodajNastavnuJedinicuFormu() {
        dodajNastavnaJedinicaController = new DodajNastavnuJedinicuController(new DodajNastavnuJedinicuForma());
        dodajNastavnaJedinicaController.otvoriFormu(FormaMod.DODAJ);
    }
    
    public void otvoriPrikazNastavnaJedinicaFormu() {
        prikazNastavneJediniceController = new PrikazNastavneJediniceController(new PrikazNastavneJediniceForma());
        prikazNastavneJediniceController.otvoriFormu();
    }

    public void otvoriIzmeniNastavnaJedinicaFormu() {
        dodajNastavnaJedinicaController = new DodajNastavnuJedinicuController(new DodajNastavnuJedinicuForma());
        dodajNastavnaJedinicaController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziFormu() {
        prikazNastavneJediniceController.osveziFormu();
    }
    
    public void otvoriPrikazUcenikaFormu() {
        prikazUcenikaControllera = new PrikazUcenikaController(new PrikazUcenikaForma());
        prikazUcenikaControllera.otvoriFormu();
    }

    public void otvoriDodajUcenikFormu() {
        dodajUcenikaController = new DodajUcenikaController(new DodajUcenikaForma());
        dodajUcenikaController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriDodajGrupaFormu() {
        dodajGrupuController = new DodajGrupuController(new DodajGrupuForma(), dodajUcenikaController);
        dodajGrupuController.otvoriFormu();
    }

    public void otvoriIzmeniUcenikFormu() {
        dodajUcenikaController = new DodajUcenikaController(new DodajUcenikaForma());
        dodajUcenikaController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziFormuUcenik() {
        prikazUcenikaControllera.osveziFormu();
    }

    public void otvoriPrikaziKvalifikacijeFormu() {
        prikazKvalController = new PrikazKvalifikacijaController(new PrikazKvalifikacijaForma());
        prikazKvalController.otvoriFormu();
    }

    public void otvoriDodajKvalifikacijuFormu() {
        dodajKvalController = new DodajKvalifikacijuController(new DodajKvalifikacijuForma());
        dodajKvalController.otvoriFormu();
    }
    
    public void otvoriPrikazEKFormu() {
        prikazEKController = new PrikazEvidencijeKursaController(new PrikazEvidencijeKursaForma());
        prikazEKController.otvoriFormu();
    }

    public void otvoriPrikazStavkiEKFormu(EvidencijaKursa odabranaEK) {
        prikazStavkiEKController = new PrikazStavkiEvidencijeKursaController(new PrikazStavkiEvidencijeKursaForma(), odabranaEK);
        prikazStavkiEKController.otvoriFormu();
    }

    public void otvoriDodajEKFormu() throws Exception {
        dodajEKController = new DodajEvidencijuKursaController(new DodajEvidencijuKursaForma(), FormaMod.DODAJ, null);
        dodajEKController.otvoriFormu();
    }
    
    public void otvoriIzmeniEKFormu(EvidencijaKursa odabranaEK) throws Exception {
        dodajEKController = new DodajEvidencijuKursaController(new DodajEvidencijuKursaForma(), FormaMod.IZMENI, odabranaEK);
        dodajEKController.otvoriFormu();
    }
    
    public void vratiSeNaGlavnuFormu() {
        if (prikazEKController != null) {
            prikazEKController.osveziTabelu();
        }
    }
    
    public void otvoriIzmeniProfesoraFormu(Profesor pZaIzmenu) {
        IzmenaProfesoraForma ipf = new IzmenaProfesoraForma();
        ipController = new IzmenaProfesoraController(ipf, pZaIzmenu);
        ipController.otvoriFormu();
    }
    
    public void osveziPrikazProfesora() {
        if (glavnaFormController != null) {
            glavnaFormController.osveziPrikazUlogovanogProfesora();
        }
    }
}  

