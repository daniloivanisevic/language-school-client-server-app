/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.nastavnaJedinica;

import model.*;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author dakik
 */
public class UbaciNastavnaJedinicaSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof NastavnaJedinica)){
            throw new Exception("Sistem ne može da doda nastavnu jedinicu.");
        }
        NastavnaJedinica k = (NastavnaJedinica) objekat;
        if(k.getNaziv()==null || k.getNaziv().isEmpty()){
            throw new Exception("Greska naziv nastavne jedinice!");
        }else if(k.getOpis()==null || k.getOpis().isEmpty()){
            throw new Exception("Greska opis nastavne jedinice!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((NastavnaJedinica)objekat);
    }
    
}
