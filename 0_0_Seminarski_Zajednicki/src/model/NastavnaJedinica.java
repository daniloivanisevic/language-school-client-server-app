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
public class NastavnaJedinica implements ApstraktniDomenskiObjekat {

    private long idNastavnaJedinica;
    private String naziv;
    private String opis;

    public NastavnaJedinica(int idNJ, String naziv, String opis) {
        this.idNastavnaJedinica = idNJ;
        this.naziv = naziv;
        this.opis = opis;
    }

    public NastavnaJedinica() {
    }

    public long getIdNastavnaJedinica() {
        return idNastavnaJedinica;
    }

    public void setIdNastavnaJedinica(long idNastavnaJedinica) {
        this.idNastavnaJedinica = idNastavnaJedinica;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }


    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String vratiNazivTabele() {
        return "nastavnaJedinica";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idNJ = rs.getInt("nastavnaJedinica.id");
            String naziv = rs.getString("nastavnaJedinica.naziv");
            String opis = rs.getString("nastavnaJedinica.opis");

            NastavnaJedinica k = new NastavnaJedinica(idNJ, naziv, opis);
            lista.add(k);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv, opis";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "', '" + opis + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "nastavnaJedinica.id=" + idNastavnaJedinica;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if (rs.next()) {
            int idNastavnaJedinica = rs.getInt("nastavnaJedinica.id");
            String naziv = rs.getString("nastavnaJedinica.naziv");
            String opis = rs.getString("nastavnaJedinica.opis");
            
            return new NastavnaJedinica(idNastavnaJedinica, naziv, opis);
        }
        return null;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "', opis='" + opis + "'";
    }
    
        @Override
    public String toString() {
        return naziv;
    }

}
