/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kvalifikacija;

import model.Kvalifikacija;
import operacija.ApstraktnaGenerickaOperacija;
import java.util.*;

/**
 *
 * @author dakik
 */
public class UcitajKvalifikacijaSO extends ApstraktnaGenerickaOperacija{
    
    List<Kvalifikacija> kvalifikacije;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        kvalifikacije = broker.getAll(new Kvalifikacija(), null);
    }

    public List<Kvalifikacija> getKvalifikacije() {
        return kvalifikacije;
    }

    public void setKvalifikacije(List<Kvalifikacija> kvalifikacije) {
        this.kvalifikacije = kvalifikacije;
    }
    
}
