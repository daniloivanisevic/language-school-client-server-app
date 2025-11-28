/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.grupa;

import java.util.List;
import model.Grupa;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author dakik
 */
public class UbaciGrupuSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (!(objekat instanceof Grupa)) {
            throw new Exception("Parametar nije tipa Grupa!");
        }
        
        Grupa grupa = (Grupa) objekat;
        List<Grupa> allGrupe = (List<Grupa>) broker.getAll(new Grupa(), null);

        for (Grupa g : allGrupe) {
           
            if (g.getNaziv().equalsIgnoreCase(grupa.getNaziv())) {
                throw new Exception("Grupa sa tim nazivom već postoji u bazi!");
            }
        }
    }
    
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((Grupa)objekat);
    }
    
}
