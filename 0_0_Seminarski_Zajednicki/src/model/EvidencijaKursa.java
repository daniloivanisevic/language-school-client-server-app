/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domen.ApstraktniDomenskiObjekat;
import java.io.Serializable;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author dakik
 */
public class EvidencijaKursa implements ApstraktniDomenskiObjekat {

    private long idEvidencijaKursa;
    private LocalDate datumUpisa;
    private LocalDate datumZavrsavanja;
    private String nivo;
    private int ukupnaPrisustva;
    private Profesor profesor;
    private Ucenik ucenik;
    private List<StavkaEvidencijeKursa> stavke;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public EvidencijaKursa() {
    }

    public EvidencijaKursa(long idEvidencijaKursa, LocalDate datumUpisa,
            LocalDate datumZavrsavanja, int ukupnaPrisustva, String nivo, Profesor profesor, Ucenik ucenik) {
        this.idEvidencijaKursa = idEvidencijaKursa;
        this.datumUpisa = datumUpisa;
        this.datumZavrsavanja = datumZavrsavanja;
        this.nivo = nivo;
        this.ukupnaPrisustva = ukupnaPrisustva;
        this.profesor = profesor;
        this.ucenik = ucenik;
        stavke = new ArrayList<>();
    }

    public LocalDate getDatumUpisa() {
        return datumUpisa;
    }

    public void setDatumUpisa(LocalDate datumUpisa) {
        this.datumUpisa = datumUpisa;
    }

    public LocalDate getDatumZavrsavanja() {
        return datumZavrsavanja;
    }

    public void setDatumZavrsavanja(LocalDate datumZavrsavanja) {
        this.datumZavrsavanja = datumZavrsavanja;
    }

    public long getIdEvidencijaKursa() {
        return idEvidencijaKursa;
    }

    public void setIdEvidencijaKursa(long idEvidencijaKursa) {
        this.idEvidencijaKursa = idEvidencijaKursa;
    }

    public int getUkupnaPrisustva() {
        return ukupnaPrisustva;
    }

    public void setUkupnaPrisustva(int ukupnaPrisustva) {
        this.ukupnaPrisustva = ukupnaPrisustva;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Ucenik getUcenik() {
        return ucenik;
    }

    public void setUcenik(Ucenik ucenik) {
        this.ucenik = ucenik;
    }

    public List<StavkaEvidencijeKursa> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaEvidencijeKursa> stavke) {
        this.stavke = stavke;
    }

    public String getNivo() {
        return nivo;
    }

    public void setNivo(String nivo) {
        this.nivo = nivo;
    }

    @Override
    public String toString() {
        return "Evidencija kursa #" + idEvidencijaKursa + " - " + ukupnaPrisustva + " PRISUSTVA";
    }

    @Override
    public String vratiNazivTabele() {
        return "evidencijaKursa";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            //evidencija kursa
            int idEK = rs.getInt("evidencijaKursa.idEvidencijaKursa");
            LocalDate tdatumUpisa = rs.getDate("evidencijaKursa.datumUpisa").toLocalDate();
            LocalDate tdatumZavrsavanja = rs.getDate("evidencijaKursa.datumZavrsavanja").toLocalDate();
            LocalDate datumUpisa;
            LocalDate datumZavrsavanja;
            int ukupnoPrisustva = rs.getInt("evidencijaKursa.ukupnaPrisustva"); // uk prisustva
            String nivo = rs.getString("evidencijaKursa.nivo"); // nivo

            if (tdatumUpisa != null) {
                datumUpisa = tdatumUpisa;
            } else {
                datumUpisa = null;
            }
            if (tdatumZavrsavanja != null) {
                datumZavrsavanja = tdatumZavrsavanja;
            } else {
                datumZavrsavanja = null;
            }

            //profesor
            int idProfesor = rs.getInt("profesor.id");
            String imeProf = rs.getString("profesor.ime");
            String prezimeProf = rs.getString("profesor.prezime");
            String emailProf = rs.getString("profesor.email");
            Profesor profesor = new Profesor(idProfesor, imeProf, prezimeProf, emailProf, null);

            //ucenik
            int idUcenik = rs.getInt("ucenik.id");
            String imeUcenika = rs.getString("ucenik.ime");
            String prezimeUcenika = rs.getString("ucenik.prezime");

            //grupa, samo da bude null
            Grupa grupaUcenika = null;
            Ucenik ucenik = new Ucenik(idUcenik, imeUcenika, prezimeUcenika, grupaUcenika);

            EvidencijaKursa ek
                    = new EvidencijaKursa(idEK, datumUpisa, datumZavrsavanja, ukupnoPrisustva, nivo, profesor, ucenik);
            lista.add(ek);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumUpisa, datumZavrsavanja, ukupnaPrisustva, nivo, IdProfesor, IdUcenik";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + datumUpisa + "', '" + datumZavrsavanja + "', "
                + ukupnaPrisustva + ", '" + nivo + "', "
                + profesor.getIdProfesor() + ", " + ucenik.getIdUcenik();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "evidencijaKursa.idEvidencijaKursa=" + idEvidencijaKursa;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datumUpisa='" + datumUpisa + "', "
                + "datumZavrsavanja='" + datumZavrsavanja + "', "
                + "nivo='" + nivo + "', "
                + "IdProfesor=" + profesor.getIdProfesor() + ", "
                + "IdUcenik=" + ucenik.getIdUcenik();
    }

}
