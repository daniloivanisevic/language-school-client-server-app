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
public class UbaciUcenikSO extends ApstraktnaGenerickaOperacija{
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Ucenik)){
            throw new Exception("Sistem ne može da kreira ucenika.");
        }
        Ucenik u = (Ucenik) objekat;
        if(u.getIme()==null || u.getPrezime()==null || u.getIme().isEmpty() || u.getPrezime().isEmpty()){
            throw new Exception("Greska - nema ime i/ili prezime ucenika!");
        }else if(u.getGrupa()==null){
            throw new Exception("Greska - nema grupa ucenika!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((Ucenik)objekat);
    }
}
