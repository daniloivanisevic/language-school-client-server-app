/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domen.ApstraktniDomenskiObjekat;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author dakik
 */
public class Grupa implements ApstraktniDomenskiObjekat {

    private int idGrupa;
    private String naziv;

    public Grupa(int idGrupa, String naziv) {
        this.idGrupa = idGrupa;
        this.naziv = naziv;

    }

    public Grupa() {

    }

    public int getIdGrupa() {
        return idGrupa;
    }

    public void setIdGrupa(int idGrupa) {
        this.idGrupa = idGrupa;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String vratiNazivTabele() {
        return "grupa";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new java.util.ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String naziv = rs.getString("naziv");
            Grupa g = new Grupa(id, naziv);
            lista.add(g);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "grupa=idGrupa=" + idGrupa;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "'";
    }

    @Override
    public String toString() {
        return naziv;
    }
    
    

}
