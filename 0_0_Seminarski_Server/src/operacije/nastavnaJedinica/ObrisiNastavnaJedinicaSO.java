/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.nastavnaJedinica;

import model.NastavnaJedinica;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author dakik
 */
public class ObrisiNastavnaJedinicaSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof NastavnaJedinica)){
            throw new Exception("Sistem ne može da obriše nastavnu jedinicu.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.delete((NastavnaJedinica) objekat);
    }
    
}
