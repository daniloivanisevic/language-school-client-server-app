/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domen.ApstraktniDomenskiObjekat;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dakik
 */
public class ProfesorKvalifikacija implements ApstraktniDomenskiObjekat{
    
    private LocalDateTime datumIzdavanja;
    private Profesor profesor;
    private Kvalifikacija kvalifikacija;

    public ProfesorKvalifikacija(LocalDateTime datumIzdavanja, Profesor profesor, Kvalifikacija kvalifikacija) {
        this.datumIzdavanja = datumIzdavanja;
        this.profesor = profesor;
        this.kvalifikacija = kvalifikacija;
    }

    public ProfesorKvalifikacija() {
    }
    

    public LocalDateTime getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(LocalDateTime datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Kvalifikacija getKvalifikacija() {
        return kvalifikacija;
    }

    public void setKvalifikacija(Kvalifikacija kvalifikacija) {
        this.kvalifikacija = kvalifikacija;
    }

    @Override
    public String vratiNazivTabele() {
        return "profesorKvalifikacija";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            LocalDateTime datum = rs.getTimestamp("profesorKvalifikacija.datumIzdavanja").toLocalDateTime();
            
            //profesor
            int idProfesor = rs.getInt("profesor.idProfesor");
            String imeProf = rs.getString("profesor.ime");
            String prezimeProf = rs.getString("profesor.prezime");
            String emailProf = rs.getString("profesor.email");
            Profesor profesor = new Profesor(idProfesor, imeProf, prezimeProf, emailProf, null);
            //kvalifikacija
            int idKval = rs.getInt("kvalifikacija.idKval");
            String naziv = rs.getString("kvalifikacija.nazivKval");
            String sertifikat = rs.getString("kvalifikacija.sertifikat");
            Kvalifikacija kval = new Kvalifikacija(idKval, naziv, sertifikat);
            
            ProfesorKvalifikacija pk = new ProfesorKvalifikacija(datum, profesor, kval);
            lista.add(pk);
        }
        return lista;        

    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "profesor, kvalifikacija, datumIzdavanja";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return profesor.getIdProfesor() + "," + kvalifikacija.getSertifikat() + ",'" + datumIzdavanja+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "profesor="+profesor.getIdProfesor() + "AND kvalifikacija="+kvalifikacija.getSertifikat();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datumProfesorKvalifikacija='" + datumIzdavanja.toString() + "'";
    }
    
       
    
}
