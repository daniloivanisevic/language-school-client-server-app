/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.ucenik;

import model.NastavnaJedinica;
import model.Ucenik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author dakik
 */
public class ObrisiUcenikSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Ucenik)){
            throw new Exception("Sistem ne može da obriše učenika.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.delete((Ucenik) objekat);
    }
    
}
