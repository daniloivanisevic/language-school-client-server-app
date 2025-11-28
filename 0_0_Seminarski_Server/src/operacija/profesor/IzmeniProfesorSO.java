/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.profesor;

/**
 *
 * @author dakik
 */

import model.Profesor;
import operacija.ApstraktnaGenerickaOperacija;

public class IzmeniProfesorSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Profesor)) {
            throw new Exception("Sistem ne može da izmeni profesora. Greška u prosleđenom parametru.");
        }
        Profesor p = (Profesor) objekat;
        
        if (p.getIme()==null || p.getPrezime()==null || p.getIme().isEmpty() || p.getPrezime().isEmpty()) {
            throw new Exception("Ime i prezime profesora ne sme biti prazno!");
        }
        if (p.getIme()==null || p.getPrezime()==null || p.getIme().isEmpty() || p.getPrezime().isEmpty()) {
            throw new Exception("Korisničko ime profesora ne sme biti prazno!");
        }
        if (p.getPassword()== null || p.getPassword().isEmpty()) {
            throw new Exception("Šifra profesora ne sme biti prazna!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.edit((Profesor) objekat);
    }
}
