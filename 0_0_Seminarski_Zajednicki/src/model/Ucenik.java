/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domen.ApstraktniDomenskiObjekat;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dakik
 */
public class Ucenik implements ApstraktniDomenskiObjekat {

    private long idUcenik;
    private String ime;
    private String prezime;
    private Grupa grupa;

    public Ucenik(long idUcenik, String ime, String prezime, Grupa grupa) {
        this.idUcenik = idUcenik;
        this.ime = ime;
        this.prezime = prezime;
        this.grupa = grupa;

    }

    public Ucenik() {
    }

    public Grupa getGrupa() {
        return grupa;
    }

    public void setGrupa(Grupa grupa) {
        this.grupa = grupa;
    }

    public long getIdUcenik() {
        return idUcenik;
    }

    public void setIdUcenik(long idUcenik) {
        this.idUcenik = idUcenik;
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

    @Override
    public String vratiNazivTabele() {
        return "ucenik";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("ucenik.id");
            String ime = rs.getString("ucenik.ime");
            String prezime = rs.getString("ucenik.prezime");

            int idGrupe = rs.getInt("grupa.id");
            String nazivGrupe = rs.getString("grupa.naziv");

            Grupa grupa = new Grupa(idGrupe, nazivGrupe);
            Ucenik u = new Ucenik(id, ime, prezime, grupa);
            lista.add(u);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, grupa_id";

    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "'," + "'" + prezime + "'," + grupa.getIdGrupa();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "ucenik.id=" + idUcenik;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='" + ime + "', prezime='" + prezime + "', grupa_id=" + grupa.getIdGrupa();
    }

       @Override
    public String toString() {
        return  ime+ " "+ prezime;
    }
    
}
