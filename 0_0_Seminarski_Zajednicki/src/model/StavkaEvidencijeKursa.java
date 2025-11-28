package model;

import domen.ApstraktniDomenskiObjekat;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class StavkaEvidencijeKursa implements ApstraktniDomenskiObjekat {

    private int rb;
    private LocalDate datumPrisustva;
    private NastavnaJedinica nastavnaJedinica;
    private boolean prisustvo;
    private EvidencijaKursa evidencija;

    public StavkaEvidencijeKursa() {
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public StavkaEvidencijeKursa(int rb, LocalDate datumPrisustva, NastavnaJedinica nj, boolean prisustvo, EvidencijaKursa evidencija) {
        this.rb = rb;
        this.datumPrisustva = datumPrisustva;
        this.nastavnaJedinica = nj;
        this.prisustvo = prisustvo;
        this.evidencija = evidencija;
    }

    public EvidencijaKursa getEvidencija() {
        return evidencija;
    }

    public void setEvidencija(EvidencijaKursa evidencija) {
        this.evidencija = evidencija;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public LocalDate getDatumPrisustva() {
        return datumPrisustva;
    }

    public void setDatumPrisustva(LocalDate datumPrisustva) {
        this.datumPrisustva = datumPrisustva;
    }

    public NastavnaJedinica getNastavnaJedinica() {
        return nastavnaJedinica;
    }

    public void setNastavnaJedinica(NastavnaJedinica nastavnaJedinica) {
        this.nastavnaJedinica = nastavnaJedinica;
    }

    public boolean isPrisustvo() {
        return prisustvo;
    }

    public void setPrisustvo(boolean prisustvo) {
        this.prisustvo = prisustvo;
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkaEvidencijeKursa";
    }

    
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int rb = rs.getInt("stavkaEvidencijeKursa.rbSEK");

            Date sqlDate = rs.getDate("stavkaEvidencijeKursa.datumPrisustva");
            LocalDate datumPrisustva = (sqlDate != null) ? sqlDate.toLocalDate() : null;

            boolean prisustvo = rs.getBoolean("stavkaEvidencijeKursa.prisustvo");

            int idNastavnaJedinica = rs.getInt("nastavnaJedinica.id");
            String naziv = rs.getString("nastavnaJedinica.naziv");
            String opis = rs.getString("nastavnaJedinica.opis");

            NastavnaJedinica nj = new NastavnaJedinica(idNastavnaJedinica, naziv, opis);

            // DODATO — obavezno poveži evidenciju kursa
            int idEvidencijaKursa = rs.getInt("stavkaEvidencijeKursa.IdEvidencijaKursa");
            EvidencijaKursa ek = new EvidencijaKursa();
            ek.setIdEvidencijaKursa(idEvidencijaKursa);

            StavkaEvidencijeKursa stavka = new StavkaEvidencijeKursa(rb, datumPrisustva, nj, prisustvo, ek);
            lista.add(stavka);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumPrisustva, prisustvo, IdEvidencijaKursa, IdNastavnaJedinica";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        String datumValue = (datumPrisustva != null)
                ? "'" + datumPrisustva.toString() + "'"
                : "NULL";

        String prisustvoValue = prisustvo ? "1" : "0";

        return datumValue + ", "
                + prisustvoValue + ", "
                + evidencija.getIdEvidencijaKursa() + ", "
                + nastavnaJedinica.getIdNastavnaJedinica();
    }

    
    @Override
    public String vratiPrimarniKljuc() {
        return "rbSEK=" + rb + " AND IdEvidencijaKursa=" + evidencija.getIdEvidencijaKursa();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if (rs.next()) {
            int rb = rs.getInt("rbSEK");
            java.sql.Date sqlDate = rs.getDate("datumPrisustva");
            LocalDate datumPrisustva = (sqlDate != null) ? sqlDate.toLocalDate() : null;

            boolean prisustvo = rs.getBoolean("prisustvo");

            int idNJ = rs.getInt("IdNastavnaJedinica");
            NastavnaJedinica nj = new NastavnaJedinica();
            nj.setIdNastavnaJedinica(idNJ);

            int idEvidencijaKursa = rs.getInt("IdEvidencijaKursa");
            EvidencijaKursa ek = new EvidencijaKursa();
            ek.setIdEvidencijaKursa(idEvidencijaKursa);

            return new StavkaEvidencijeKursa(rb, datumPrisustva, nj, prisustvo, ek);
        }

        return null;
    }

    
    @Override
    public String vratiVrednostiZaIzmenu() {
        String datumSQL = (datumPrisustva != null)
                ? "'" + datumPrisustva.format(formatter) + "'"
                : "NULL";

        return "datumPrisustva=" + datumSQL
                + ", prisustvo=" + (prisustvo ? 1 : 0)
                + ", IdNastavnaJedinica=" + nastavnaJedinica.getIdNastavnaJedinica()
                + ", IdEvidencijaKursa=" + evidencija.getIdEvidencijaKursa();
    }
}
