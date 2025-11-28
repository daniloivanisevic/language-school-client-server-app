/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.*;
import model.*;
import komunikacija.Komunikacija;
import cordinator.Cordinator;
import komunikacije.Odgovor;
import komunikacije.Operacija;
import komunikacije.Posiljalac;
import komunikacije.Primalac;
import komunikacije.Zahtev;

/**
 *
 * @author dakik
 */
public class Komunikacija {
    private Socket socket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    private static Komunikacija instance;

    private Komunikacija() {
    }

    public static Komunikacija getInstance() {
        if(instance == null)
            instance = new Komunikacija();
        return instance;
    }
    
    public void konekcija(){
        try {
            socket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(socket);
            primalac = new Primalac(socket);
        } catch (IOException ex) {
            System.out.println("Greska u konekciji sa serverom!");
        }
    }

    public Profesor logIn(String email, String pass) {
        Profesor p = new Profesor();
        p.setEmail(email);
        p.setPassword(pass);
        
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, p);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        
        p = (Profesor) odgovor.getOdgovor();
        return p;
    }

    public List<NastavnaJedinica> ucitajNastavneJedinice() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_NASTAVNE_JEDINICE, null);
        List<NastavnaJedinica> nj = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        nj = (List<NastavnaJedinica>) odgovor.getOdgovor();
        return nj;
    }

    public void obrisiNastavnuJedinicu(NastavnaJedinica k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_NASTAVNU_JEDINICU, k);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getOdgovor() == null){
            System.out.println("Sistem je obrisao nastavnu jedinicu.");
        }else{
            System.out.println("Greska!");
            ((Exception)odgovor.getOdgovor()).getMessage();
            throw new Exception("Greska!");
        }
    }

    public void dodajNastavnuJedinicu(NastavnaJedinica noviNJ) {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_NASTAVNU_JEDINICU, noviNJ);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getOdgovor() == null){
            System.out.println("Uspeh!");
        }else{
            System.out.println("Greska!");
        }
    }

    public void izmeniNastavnuJedinicu(NastavnaJedinica novaNJ) {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_NASTAVNU_JEDINICU, novaNJ);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getOdgovor() == null){
            System.out.println("Uspeh!");
            Cordinator.getInstance().osveziFormu();
        }else{
            System.out.println("Greska!");
        }
    }

    public List<Ucenik> ucitajUcenike() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_UCENIKE, null);
        List<Ucenik> ucenici = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        ucenici = (List<Ucenik>) odgovor.getOdgovor();
        return ucenici;
    }

    public void obrisiUcenik(Ucenik u) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_UCENIK, u);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getOdgovor() == null){
            System.out.println("Sistem je izbrisao ucenika.");
        }else{
            System.out.println("Greska!");
            ((Exception)odgovor.getOdgovor()).printStackTrace();
            throw new Exception("Greska!");
        }
    }

    public List<Grupa> ucitajGrupe() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_GRUPE, null);
        List<Grupa> grupe = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        grupe = (List<Grupa>) odgovor.getOdgovor();
        return grupe;
    }

    public void dodajUcenika(Ucenik noviUc) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_UCENIKA, noviUc);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        
        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        if(odgovor.getOdgovor() == null){
            System.out.println("Uspesno!");
        }
    }

    public void dodajGrupu(Grupa novaGr) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_GRUPA, novaGr);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        
        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        if(odgovor.getOdgovor() == null){
            System.out.println("Uspesno!");
        }
    }

    public void izmeniUcenika(Ucenik noviUcenik) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_UCENIK, noviUcenik);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        
        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        if(odgovor.getOdgovor() == null){
            System.out.println("Uspesno!");
            Cordinator.getInstance().osveziFormuUcenik();
        }
    }

    public List<Kvalifikacija> ucitajKval() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KVALIFIKACIJA, null);
        List<Kvalifikacija> kval = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        kval = (List<Kvalifikacija>) odgovor.getOdgovor();
        return kval;
    }
    
    public void dodajKval(Kvalifikacija kval) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_KVALIFIKACIJU, kval);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }
    }

    public List<EvidencijaKursa> ucitajListuEvidencija() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_EVIDENCIJA_KURSA, null);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        return (List<EvidencijaKursa>) odgovor.getOdgovor();
    }

    public List<StavkaEvidencijeKursa> ucitajStavkeEvidencijeKursa(EvidencijaKursa ek) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_STAVKE_EVIDENCIJE_KURSA, ek);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        return (List<StavkaEvidencijeKursa>) odgovor.getOdgovor();
    }

    public List<Profesor> ucitajProfesore() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_PROFESORE, null);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        return (List<Profesor>) odgovor.getOdgovor();
    }
   
    
    
    public void kreirajEvidencijuKursa(EvidencijaKursa ek) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.KREIRAJ_EVIDENCIJU_KURSA, ek);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        // Provera da li je serverska strana vratila izuzetak
        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        // Ako nije, sve je u redu
        if (odgovor.getOdgovor() == null) {
            System.out.println("Sistem je uspešno kreirao evidenciju kursa.");
        }
    }
    
    public List<EvidencijaKursa> pretraziEvidencijuKursa(EvidencijaKursa FindEvidencija) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_EVIDENCIJE_KURSA, FindEvidencija);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        
        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        return (List<EvidencijaKursa>) odgovor.getOdgovor();
    }
    
    public void izmeniEvidencijuKursa(EvidencijaKursa ek) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_EVIDENCIJU_KURSA, ek);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        
        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        if(odgovor.getOdgovor() == null){
            System.out.println("Uspeh!");
        }
    }
    
    public void izmeniProfesora(Profesor p) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_PROFESOR, p);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }
    }
    
}