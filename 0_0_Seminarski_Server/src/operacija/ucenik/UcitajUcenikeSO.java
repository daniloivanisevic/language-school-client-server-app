/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.ucenik;

import java.util.List;
import model.Ucenik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author dakik
 */
public class UcitajUcenikeSO extends ApstraktnaGenerickaOperacija{
    
    List<Ucenik> ucenici;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        String uslov = " JOIN grupa ON ucenik.grupa_id = grupa.id";
        
        try{
            ucenici = broker.getAll(new Ucenik(), uslov);
        }catch(Exception e){
            System.out.println("UcitajUcenikeSO: " +e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Ucenik> getUcenici() {
        return ucenici;
    }

    public void setUcenici(List<Ucenik> ucenici) {
        this.ucenici = ucenici;
    }
    
}
