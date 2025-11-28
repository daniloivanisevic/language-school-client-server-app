/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domen.ApstraktniDomenskiObjekat;
import java.io.Serializable;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author dakik
 */
public class Profesor implements ApstraktniDomenskiObjekat {

    private long idProfesor;
    private String ime;
    private String prezime;
    private String email;
    private String password;

    public Profesor() {
    }

    public Profesor(long idProfesor, String ime, String prezime, String email, String password) {
        this.idProfesor = idProfesor;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.password = password;
    }

    public long getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(long idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (int) (this.idProfesor ^ (this.idProfesor >>> 32));
        hash = 19 * hash + Objects.hashCode(this.ime);
        hash = 19 * hash + Objects.hashCode(this.prezime);
        hash = 19 * hash + Objects.hashCode(this.email);
        hash = 19 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Profesor other = (Profesor) obj;
        if (this.idProfesor != other.idProfesor) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    @Override
    public String vratiNazivTabele() {
        return "profesor";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        System.out.println("Profesor.vratiListu pozvana!");
        java.sql.ResultSetMetaData meta = rs.getMetaData();
        int kolone = meta.getColumnCount();
        for (int i = 1; i <= kolone; i++) {
            System.out.println("Kolona " + i + ": " + meta.getColumnName(i));
        }

        while (rs.next()) {
            int idProfesor = rs.getInt("profesor.id");
            String imeProf = rs.getString("profesor.ime");
            String prezimeProf = rs.getString("profesor.prezime");
            String emailProf = rs.getString("profesor.email");
            String password = rs.getString("profesor.password");

            Profesor p = new Profesor(idProfesor, imeProf, prezimeProf, emailProf, password);
            lista.add(p);
        }
        return lista;

    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, email, password";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "','" + prezime + "','" + email + "','" + password + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "profesor.id=" + idProfesor;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='" + ime
                + "', prezime='" + prezime
                + "', email='" + email
                + "', password='" + password + "'";
    }

    @Override
    public String toString() {
        return  ime+ " "+ prezime;
    }
    
    

}
