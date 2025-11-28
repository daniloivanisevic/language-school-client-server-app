/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domen.ApstraktniDomenskiObjekat;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dakik
 */
public class Kvalifikacija implements ApstraktniDomenskiObjekat {

    private long idKvalifikacija;
    private String naziv;
    private String sertifikat;

    public Kvalifikacija(long idKvalifikacija, String naziv, String sertifikat) {
        this.idKvalifikacija = idKvalifikacija;
        this.naziv = naziv;
        this.sertifikat = sertifikat;
    }

    public Kvalifikacija() {
    }

    public long getIdKvalifikacija() {
        return idKvalifikacija;
    }

    public void setIdKvalifikacija(long idKvalifikacija) {
        this.idKvalifikacija = idKvalifikacija;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSertifikat() {
        return sertifikat;
    }

    public void setSertifikat(String sertifikat) {
        this.sertifikat = sertifikat;
    }

    @Override
    public String vratiNazivTabele() {
        return "kvalifikacija";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idKval = rs.getInt("kvalifikacija.idKvalifikacija");
            String naziv = rs.getString("kvalifikacija.nazivKvalifikacije");
            String sertifikat = rs.getString("kvalifikacija.sertifikat");

            Kvalifikacija kval = new Kvalifikacija(idKval, naziv, sertifikat);
            lista.add(kval);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivKvalifikacije, sertifikat";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "' , '"+sertifikat + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "kvalifikacija.idKvalifikacija"+idKvalifikacija;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "nazivKvalifikacije='"+naziv + "', sertifikat='" + sertifikat + "'";
    }

}
