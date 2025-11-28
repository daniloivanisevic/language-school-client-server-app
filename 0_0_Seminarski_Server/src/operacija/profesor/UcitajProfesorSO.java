
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.profesor;

import java.util.List;
import model.Profesor;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author dakik
 */
public class UcitajProfesorSO extends ApstraktnaGenerickaOperacija{
    
    List<Profesor> profesori;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        //
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        profesori = broker.getAll(new Profesor(), "");
    }

    public List<Profesor> getProfesori() {
        return profesori;
    }

    public void setProfesori(List<Profesor> profesori) {
        this.profesori = profesori;
    }
    
}
