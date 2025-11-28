/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.nastavnaJedinica;

import model.NastavnaJedinica;
import java.util.*;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author dakik
 */
public class UcitajNastavneJediniceSO extends ApstraktnaGenerickaOperacija{
    List<NastavnaJedinica> nastavneJedinice;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        nastavneJedinice = broker.getAll(new NastavnaJedinica(), "");
    }

    public List<NastavnaJedinica> getNastavneJedinice() {
        return nastavneJedinice;
    }

    public void setNastavneJedinice(List<NastavnaJedinica> nj) {
        this.nastavneJedinice = nj;
    }
    
}
