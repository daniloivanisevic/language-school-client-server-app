/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.evidencijaKursa;

import java.util.List;
import model.NastavnaJedinica;
import model.EvidencijaKursa;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author dakik
 */
public class UcitajEvidencijaKursaSO extends ApstraktnaGenerickaOperacija {

    private List<EvidencijaKursa> evidencije;

    @Override
    protected void preduslovi(Object param) throws Exception {
        // Nema preduslova za ovu operaciju
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        
        String uslov = " JOIN ucenik ON evidencijaKursa.IdUcenik = ucenik.id "
                + "JOIN profesor ON evidencijaKursa.IdProfesor = profesor.id";
        
        try{
            evidencije = broker.getAll(new EvidencijaKursa(), uslov);
        }catch(Exception e){
            System.out.println("UcitajUcenikeSO: " +e.getMessage());
            e.printStackTrace();
        }
    }

    public List<EvidencijaKursa> getEvidencije() {
        return evidencije;
    }

}
