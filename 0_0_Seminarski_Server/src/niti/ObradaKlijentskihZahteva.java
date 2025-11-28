/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.net.*;
import model.*;
import komunikacije.*;
import domen.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.controller.Controller;


/**
 *
 * @author dakik
 */
public class ObradaKlijentskihZahteva extends Thread {
    Socket s;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
        posiljalac = new Posiljalac(s);
        primalac = new Primalac(s);
    }

    @Override
    public void run() {
        try{
            while(!kraj){
                Zahtev zahtev = (Zahtev)primalac.primi();
                Odgovor odgovor = new Odgovor();

                switch (zahtev.getOperacija()) {
                    case LOGIN:
                        try {
                            Profesor p = (Profesor) zahtev.getParametar();
                            p = Controller.getInstance().login(p);
                            odgovor.setOdgovor(p);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case UCITAJ_NASTAVNE_JEDINICE:
                        try {
                            List<NastavnaJedinica> nastavneJedinice = Controller.getInstance().ucitajNastavneJedinice();
                            odgovor.setOdgovor(nastavneJedinice);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case OBRISI_NASTAVNU_JEDINICU:
                        try{
                            NastavnaJedinica nj = (NastavnaJedinica) zahtev.getParametar();
                            Controller.getInstance().obrisiNastavnaJedinica(nj);
                            odgovor.setOdgovor(null);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case DODAJ_NASTAVNU_JEDINICU:
                        try {
                            NastavnaJedinica noviNJ = (NastavnaJedinica) zahtev.getParametar();
                            Controller.getInstance().dodajNastavnaJedinica(noviNJ);
                            odgovor.setOdgovor(null);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case IZMENI_NASTAVNU_JEDINICU:
                        try {
                            NastavnaJedinica njZaIzmenu = (NastavnaJedinica) zahtev.getParametar();
                            Controller.getInstance().izmeniNastavnaJedinica(njZaIzmenu);
                            odgovor.setOdgovor(null);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case UCITAJ_UCENIKE:
                        try {
                            List<Ucenik> ucenici = Controller.getInstance().ucitajUcenike();
                            odgovor.setOdgovor(ucenici);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case OBRISI_UCENIK:
                        try{
                            Ucenik u = (Ucenik) zahtev.getParametar();
                            Controller.getInstance().obrisiUcenik(u);
                            odgovor.setOdgovor(null);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case UCITAJ_GRUPE:
                        try {
                            List<Grupa> mesta = Controller.getInstance().ucitajGrupe();
                            odgovor.setOdgovor(mesta);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case DODAJ_UCENIKA:
                        try{
                            Ucenik noviUcenik = (Ucenik) zahtev.getParametar();
                            Controller.getInstance().dodajUcenika(noviUcenik);
                            System.out.println(noviUcenik);
                            odgovor.setOdgovor(null);
                            
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                            System.out.println("Greska, klasa ObradaKZ: " + ex.getMessage());
                        }
                        break;
                    case DODAJ_GRUPA:
                    try {
                        Grupa novaGrupa = (Grupa) zahtev.getParametar();
                        Controller.getInstance().dodajGrupu(novaGrupa);
                        odgovor.setOdgovor(null);
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex);
                        System.out.println("Greska, klasa ObradaKZ: " + ex.getMessage());
                    }
                    break;
                    case IZMENI_UCENIK:
                    try {
                        Ucenik ucenikZaIzmenu = (Ucenik) zahtev.getParametar();
                        Controller.getInstance().izmeniUcenik(ucenikZaIzmenu);
                        odgovor.setOdgovor(null); // USPEH! Postavljamo null kao "dobar" odgovor
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex);
                        System.out.println("Greska, klasa ObradaKZ: " + ex.getMessage());
                    }
                    break;
                    case UCITAJ_KVALIFIKACIJA:
                        try {
                            List<Kvalifikacija> kval = Controller.getInstance().ucitajKval();
                            odgovor.setOdgovor(kval);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case DODAJ_KVALIFIKACIJU:
                        try {
                            Kvalifikacija novaKvalifikacija = (Kvalifikacija) zahtev.getParametar();
                            Controller.getInstance().dodajKval(novaKvalifikacija);
                            odgovor.setOdgovor(null);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                            System.out.println("Greska, klasa ObradaKZ: " + ex.getMessage());
                        }
                        break;
                case UCITAJ_EVIDENCIJA_KURSA:
                    try {
                        List<EvidencijaKursa> ek = Controller.getInstance().ucitajEvidencijeKursa();
                        odgovor.setOdgovor(ek);
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex);
                    }
                    break;
                case UCITAJ_STAVKE_EVIDENCIJE_KURSA:
                    try {
                        List<StavkaEvidencijeKursa> stavke = Controller.getInstance().
                        ucitajStavkeEvidencijeKursa((EvidencijaKursa)zahtev.getParametar());
                        odgovor.setOdgovor(stavke);
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex);
                    }
                    break;
                case UCITAJ_PROFESORE:
                    try {
                        List<Profesor> profesori = Controller.getInstance().ucitajProfesore();
                        odgovor.setOdgovor(profesori);
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex);
                    }
                    break;
                    case KREIRAJ_EVIDENCIJU_KURSA: 
                        try {
                            EvidencijaKursa noviEK = (EvidencijaKursa) zahtev.getParametar();
                            Controller.getInstance().dodajEvidencijaKursa(noviEK);
                            odgovor.setOdgovor(null); 
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex); 
                            System.out.println("Greska, klasa ObradaKlijentskihZahteva: " + ex.getMessage());
                        }
                        break;
                case PRETRAZI_EVIDENCIJE_KURSA:
                    try {
                        EvidencijaKursa EKZaPretragu = (EvidencijaKursa) zahtev.getParametar();
                        List<EvidencijaKursa> filtriraniEK = Controller.getInstance().pretraziEvidencijuKursa(EKZaPretragu);
                        odgovor.setOdgovor(filtriraniEK);
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex);
                        System.out.println("Greska, klasa ObradaKlijentskihZahteva: " + ex.getMessage());
                    }
                    break;
                case IZMENI_EVIDENCIJU_KURSA:
                    try {
                        EvidencijaKursa EKZaIzmenu = (EvidencijaKursa) zahtev.getParametar();
                        Controller.getInstance().izmeniEvidencijuKursa(EKZaIzmenu);
                        odgovor.setOdgovor(null); 
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex); 
                        System.out.println("Greska, klasa ObradaKlijentskihZahteva: " + ex.getMessage());
                    }
                    break;
                case IZMENI_PROFESOR:
                    try {
                        Profesor pZaIzmenu = (Profesor) zahtev.getParametar();
                        Controller.getInstance().izmeniProfesora(pZaIzmenu);
                        odgovor.setOdgovor(null);
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex);
                        System.out.println("Greška prilikom izmene profesora: " + ex.getMessage());
                    }
                    break;
                    default:
                        System.out.println("Greska! Operacija ne postoji.");
                }
                posiljalac.posalji(odgovor);
            }
        }catch(Exception e){
//            e.printStackTrace();
            System.out.println("Klijent je prekinuo konekciju: "+e.getMessage());
        }
    }
    
    public void prekini(){
        kraj = true;
        try {
            s.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        interrupt();
    }
  
}
