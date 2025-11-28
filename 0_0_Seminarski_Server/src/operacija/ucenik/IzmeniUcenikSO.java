/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.ucenik;

import model.Ucenik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author dakik
 */
public class IzmeniUcenikSO extends ApstraktnaGenerickaOperacija{
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Ucenik)){
            throw new Exception("Sistem ne može da doda ucenika.");
        }
        Ucenik u = (Ucenik) objekat;
        if(u.getIme()==null || u.getPrezime()==null || u.getIme().isEmpty() || u.getPrezime().isEmpty()){
            throw new Exception("Greska - nema ime i prezime ucenika!");
        }else if(u.getGrupa()==null){
            throw new Exception("Greska - nema grupe ucenika!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.edit((Ucenik)objekat);
    }  
}
