/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija;

import java.util.List;
import model.Profesor;

/**
 *
 * @author dakik
 */
public class LogInOperacija extends ApstraktnaGenerickaOperacija {

    Profesor profesor;

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Profesor)) {
            throw new Exception("Email i šifra nisu ispravni.");          
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Profesor uneti = (Profesor) objekat;
        List<Profesor> sviProfesori = broker.getAll(new Profesor(), null);
        System.out.println("Klasa LogInOperacija SO: " + sviProfesori);

        for (Profesor p : sviProfesori) {
            if (p.getEmail().equalsIgnoreCase(uneti.getEmail())
                    && p.getPassword().equals(uneti.getPassword())) {
                profesor = p;
                System.out.println(" Pronadjen profesor: " + p.getIme() + " " + p.getPrezime());
                return;
            }
        }

        profesor = null;
        System.out.println(" Nije pronadjen profesor sa datim kredencijalima.");
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

}
