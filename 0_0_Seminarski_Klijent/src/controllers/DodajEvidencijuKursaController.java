package controllers;

import cordinator.Cordinator;
import forme.DodajEvidencijuKursaForma;
import forme.FormaMod;
import static forme.FormaMod.DODAJ;
import static forme.FormaMod.IZMENI;
import forme.modeli.ModelTabeleStavkaEvidencijaKursa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.NastavnaJedinica;
import model.Ucenik;
import model.Profesor;
import model.EvidencijaKursa;
import model.StavkaEvidencijeKursa;

public class DodajEvidencijuKursaController {

    private final DodajEvidencijuKursaForma dodajEvidencijuKursa;
    private EvidencijaKursa ek;
    private ModelTabeleStavkaEvidencijaKursa modelStavke;
    Profesor profesor = Cordinator.getInstance().getUlogovani();

    public DodajEvidencijuKursaController(DodajEvidencijuKursaForma dek, FormaMod m, EvidencijaKursa ek) throws Exception {
        this.dodajEvidencijuKursa = dek;
        this.ek = ek;

        dek.setTitle("Ubacivanje i Izmena evidencije kursa");
        dek.setLocationRelativeTo(null);
        dek.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        popuniComboBoxeve();
        dodajActionListenere();
        pripremiFormu(m);
    }

    public void otvoriFormu() {
        dodajEvidencijuKursa.setVisible(true);
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dodajEvidencijuKursa.getjButtonIzmeniEK().setEnabled(false);
                dodajEvidencijuKursa.getjButtonKreirajEK().setVisible(true);
                dodajEvidencijuKursa.getjTextFieldID().setEnabled(false);

                dodajEvidencijuKursa.getjTextFieldDatumUpisa().setEditable(true);
                dodajEvidencijuKursa.getjTextFieldDatumZavrs().setEditable(true);
                dodajEvidencijuKursa.getjTextFieldNivo().setEditable(true);
                dodajEvidencijuKursa.getjTextFieldDatumPrisustva().setEditable(true);

                dodajEvidencijuKursa.getjTextFieldProfesor().setText(profesor.getIme() + " " + profesor.getPrezime());
                dodajEvidencijuKursa.getjTextFieldProfesor().setEditable(false);

                modelStavke = new ModelTabeleStavkaEvidencijaKursa(new ArrayList<>());
                break;

            case IZMENI:
                dodajEvidencijuKursa.getjButtonKreirajEK().setEnabled(false);
                dodajEvidencijuKursa.getjButtonIzmeniEK().setVisible(true);
                dodajEvidencijuKursa.getjTextFieldID().setEditable(false);
                dodajEvidencijuKursa.getjTextFieldDatumUpisa().setEditable(true);
                dodajEvidencijuKursa.getjTextFieldDatumZavrs().setEditable(true);
                dodajEvidencijuKursa.getjTextFieldNivo().setEditable(true);
                dodajEvidencijuKursa.getjTextFieldProfesor().setText(profesor.getIme() + " " + profesor.getPrezime());
                dodajEvidencijuKursa.getjTextFieldProfesor().setEditable(false);
                popuniFormuZaIzmenu(ek);
                break;
        }
        dodajEvidencijuKursa.getjTableStavke().setModel(modelStavke);
    }

    private void popuniFormuZaIzmenu(EvidencijaKursa ek) {
        dodajEvidencijuKursa.getjTextFieldID().setText(String.valueOf(ek.getIdEvidencijaKursa()));

        for (int i = 0; i < dodajEvidencijuKursa.getjComboBoxUcenici().getItemCount(); i++) {
            Ucenik u = (Ucenik) dodajEvidencijuKursa.getjComboBoxUcenici().getItemAt(i);
            if (u != null && u.getIdUcenik() == ek.getUcenik().getIdUcenik()) {
                dodajEvidencijuKursa.getjComboBoxUcenici().setSelectedItem(u);
                break;
            }
        }

        try {
            List<StavkaEvidencijeKursa> stavke = Komunikacija.getInstance().ucitajStavkeEvidencijeKursa(ek);
            modelStavke = new ModelTabeleStavkaEvidencijaKursa(stavke);
            JOptionPane.showMessageDialog(dodajEvidencijuKursa,
                    "Sistem je uspešno učitao stavke evidencije kursa.",
                    "Uspeh", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            modelStavke = new ModelTabeleStavkaEvidencijaKursa(new ArrayList<>());
            JOptionPane.showMessageDialog(dodajEvidencijuKursa,
                    "Sistem ne može da učita stavke evidencije kursa.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void dodajActionListenere() {
        dodajEvidencijuKursa.getjButtonDodajStavku().addActionListener(e -> dodajStavku());
        dodajEvidencijuKursa.getjButtonObrisiStavku().addActionListener(e -> obrisiStavku());
        dodajEvidencijuKursa.getjButtonKreirajEK().addActionListener(e -> kreirajEK());
        dodajEvidencijuKursa.getjButtonIzmeniEK().addActionListener(e -> izmeniEK());

        dodajEvidencijuKursa.getjComboPrisutan().addActionListener(e -> {
            String prisutanStr = (String) dodajEvidencijuKursa.getjComboPrisutan().getSelectedItem();
            if ("NE".equals(prisutanStr)) {
                dodajEvidencijuKursa.getjTextFieldDatumPrisustva().setText("");
                dodajEvidencijuKursa.getjTextFieldDatumPrisustva().setEnabled(false);
            } else {
                dodajEvidencijuKursa.getjTextFieldDatumPrisustva().setEnabled(true);
            }
        });
    }

    private void dodajStavku() {
        if (dodajEvidencijuKursa.getjComboBoxNJ().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(dodajEvidencijuKursa,
                    "Morate izabrati nastavnu jedinicu.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        NastavnaJedinica nj = (NastavnaJedinica) dodajEvidencijuKursa.getjComboBoxNJ().getSelectedItem();

        for (StavkaEvidencijeKursa stavka : modelStavke.getListaStavki()) {
            if (stavka.getNastavnaJedinica().equals(nj)) {
                JOptionPane.showMessageDialog(dodajEvidencijuKursa,
                        "Ta nastavna jedinica je već dodata.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        StavkaEvidencijeKursa novaStavka = new StavkaEvidencijeKursa();
        novaStavka.setNastavnaJedinica(nj);

        String prisutanStr = (String) dodajEvidencijuKursa.getjComboPrisutan().getSelectedItem();
        boolean prisutan = "DA".equals(prisutanStr);
        novaStavka.setPrisustvo(prisutan);

        if (prisutan) {
            String datumStr = dodajEvidencijuKursa.getjTextFieldDatumPrisustva().getText().trim();
            if (datumStr.isEmpty()) {
                JOptionPane.showMessageDialog(dodajEvidencijuKursa,
                        "Unesite datum prisustva jer je prisustvo označeno kao 'DA'.",
                        "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate datumPrisustva = LocalDate.parse(datumStr, formatter);
                novaStavka.setDatumPrisustva(datumPrisustva);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(dodajEvidencijuKursa,
                        "Neispravan format datuma prisustva (ispravno je dd/MM/yyyy).",
                        "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            novaStavka.setDatumPrisustva(null);
        }

        modelStavke.dodajStavku(novaStavka);
        modelStavke.fireTableDataChanged();
    }

    private void obrisiStavku() {
        int red = dodajEvidencijuKursa.getjTableStavke().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(dodajEvidencijuKursa, "Niste izabrali nijednu stavku.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
        modelStavke.obrisiStavku(red);
    }

    private void kreirajEK() {
        try {
            Ucenik ucenik = (Ucenik) dodajEvidencijuKursa.getjComboBoxUcenici().getSelectedItem();
            if (ucenik == null) {
                JOptionPane.showMessageDialog(dodajEvidencijuKursa, "Morate izabrati učenika.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<StavkaEvidencijeKursa> stavkeEK = modelStavke.getListaStavki();
            if (stavkeEK.isEmpty()) {
                JOptionPane.showMessageDialog(dodajEvidencijuKursa, "Evidencija kursa mora imati barem jednu stavku.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String nivo = dodajEvidencijuKursa.getjTextFieldNivo().getText().trim();
            if (nivo.isEmpty()) {
                JOptionPane.showMessageDialog(dodajEvidencijuKursa, "Morate uneti nivo kursa.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String upisStr = dodajEvidencijuKursa.getjTextFieldDatumUpisa().getText().trim();
            String zavrsStr = dodajEvidencijuKursa.getjTextFieldDatumZavrs().getText().trim();

            if (upisStr.isEmpty() || zavrsStr.isEmpty()) {
                JOptionPane.showMessageDialog(dodajEvidencijuKursa,
                        "Morate uneti oba datuma (upis i završetak kursa).",
                        "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDate datumUpisa = LocalDate.parse(upisStr, formatter);
            LocalDate datumZavrsetka = LocalDate.parse(zavrsStr, formatter);

            if (datumUpisa.isAfter(datumZavrsetka)) {
                JOptionPane.showMessageDialog(dodajEvidencijuKursa, "Datum upisa ne može biti posle datuma završetka kursa!", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            EvidencijaKursa novaEK = new EvidencijaKursa();
            novaEK.setUcenik(ucenik);
            novaEK.setProfesor(profesor);
            novaEK.setDatumUpisa(datumUpisa);
            novaEK.setDatumZavrsavanja(datumZavrsetka);
            novaEK.setNivo(nivo);
            novaEK.setStavke(stavkeEK);

            int ukupnoPrisustva = 0;
            for (StavkaEvidencijeKursa s : stavkeEK) {
                if (s.isPrisustvo()) {
                    ukupnoPrisustva++;
                }
            }
            novaEK.setUkupnaPrisustva(ukupnoPrisustva);

            Komunikacija.getInstance().kreirajEvidencijuKursa(novaEK);
            JOptionPane.showMessageDialog(dodajEvidencijuKursa, "Sistem je uspešno zapamtio evidenciju kursa.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

            prikaziPodatkeOEvidencijiKursaKreiranje(novaEK);

            dodajEvidencijuKursa.dispose();
            Cordinator.getInstance().vratiSeNaGlavnuFormu();

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(dodajEvidencijuKursa, "Datumi moraju biti u formatu dd/MM/yyyy.", "Greška", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dodajEvidencijuKursa, "Greška pri ubacivanju evidencije.", "Greška", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(dodajEvidencijuKursa, "Proverite ispravnost datuma prisustva.", "Greška", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void izmeniEK() {
        try {
            Ucenik u = (Ucenik) dodajEvidencijuKursa.getjComboBoxUcenici().getSelectedItem();

            if (u == null || profesor == null) {
                JOptionPane.showMessageDialog(dodajEvidencijuKursa, "Morate izabrati učenika i profesora.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String upisStr = dodajEvidencijuKursa.getjTextFieldDatumUpisa().getText().trim();
            String zavrsStr = dodajEvidencijuKursa.getjTextFieldDatumZavrs().getText().trim();

            if (upisStr.isEmpty() || zavrsStr.isEmpty()) {
                JOptionPane.showMessageDialog(dodajEvidencijuKursa,
                        "Morate uneti oba datuma (upis i završetak kursa).",
                        "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDate datumUpisa = LocalDate.parse(upisStr, formatter);
            LocalDate datumZavrsetka = LocalDate.parse(zavrsStr, formatter);

            if (datumUpisa.isAfter(datumZavrsetka)) {
                JOptionPane.showMessageDialog(dodajEvidencijuKursa, "Datum upisa ne može biti posle datuma završetka kursa!", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String nivo = dodajEvidencijuKursa.getjTextFieldNivo().getText().trim();
            if (nivo.isEmpty()) {
                JOptionPane.showMessageDialog(dodajEvidencijuKursa, "Morate uneti nivo kursa.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            this.ek.setUcenik(u);
            this.ek.setProfesor(profesor);
            this.ek.setDatumUpisa(datumUpisa);
            this.ek.setDatumZavrsavanja(datumZavrsetka);
            this.ek.setNivo(nivo);
            this.ek.setStavke(modelStavke.getListaStavki());

            int ukupnoPrisustva = 0;
            for (StavkaEvidencijeKursa s : modelStavke.getListaStavki()) {
                if (s.isPrisustvo()) {
                    ukupnoPrisustva++;
                }
            }
            this.ek.setUkupnaPrisustva(ukupnoPrisustva);

            this.ek.setUkupnaPrisustva(ukupnoPrisustva);

            Komunikacija.getInstance().izmeniEvidencijuKursa(this.ek);
            JOptionPane.showMessageDialog(dodajEvidencijuKursa, "Sistem je uspešno izmenio evidenciju kursa.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

            prikaziPodatkeOEvidencijiKursaIzmena(this.ek);

            dodajEvidencijuKursa.dispose();
            Cordinator.getInstance().vratiSeNaGlavnuFormu();

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(dodajEvidencijuKursa, "Datumi moraju biti u formatu dd/MM/yyyy.", "Greška", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dodajEvidencijuKursa, "Greška pri izmeni evidencije.", "Greška", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(dodajEvidencijuKursa, "Proverite ispravnost datuma prisustva.", "Greška", JOptionPane.ERROR_MESSAGE);

            ex.printStackTrace();
        }
    }

    private void popuniComboBoxeve() throws Exception {
        List<Ucenik> ucenici = Komunikacija.getInstance().ucitajUcenike();
        List<NastavnaJedinica> nastavneJedinice = Komunikacija.getInstance().ucitajNastavneJedinice();
        dodajEvidencijuKursa.getjComboBoxUcenici().removeAllItems();
        dodajEvidencijuKursa.getjComboBoxNJ().removeAllItems();

        for (NastavnaJedinica nj : nastavneJedinice) {
            dodajEvidencijuKursa.getjComboBoxNJ().addItem(nj);
        }
        dodajEvidencijuKursa.getjComboBoxNJ().setSelectedIndex(-1);

        for (Ucenik k : ucenici) {
            dodajEvidencijuKursa.getjComboBoxUcenici().addItem(k);
        }
        dodajEvidencijuKursa.getjComboBoxUcenici().setSelectedIndex(-1);
    }

    private void prikaziPodatkeOEvidencijiKursaKreiranje(EvidencijaKursa ek) {
        String poruka
                = "Evidencija kursa je uspešno ubačena!\n\n"
                + "Učenik: " + ek.getUcenik().getIme() + " " + ek.getUcenik().getPrezime() + "\n"
                + "Profesor: " + ek.getProfesor().getIme() + " " + ek.getProfesor().getPrezime() + "\n"
                + "Nivo kursa: " + ek.getNivo() + "\n"
                + "Datum upisa: " + ek.getDatumUpisa() + "\n"
                + "Datum završetka: " + ek.getDatumZavrsavanja() + "\n"
                + "Ukupno prisustava: " + ek.getUkupnaPrisustva();

        JOptionPane.showMessageDialog(dodajEvidencijuKursa, poruka, "Podaci o evidenciji kursa", JOptionPane.INFORMATION_MESSAGE);
    }

    private void prikaziPodatkeOEvidencijiKursaIzmena(EvidencijaKursa ek) {
        String poruka
                = "Evidencija kursa je uspešno izmenjena!\n\n"
                + "Učenik: " + ek.getUcenik().getIme() + " " + ek.getUcenik().getPrezime() + "\n"
                + "Profesor: " + ek.getProfesor().getIme() + " " + ek.getProfesor().getPrezime() + "\n"
                + "Nivo kursa: " + ek.getNivo() + "\n"
                + "Datum upisa: " + ek.getDatumUpisa() + "\n"
                + "Datum završetka: " + ek.getDatumZavrsavanja() + "\n"
                + "Ukupno prisustava: " + ek.getUkupnaPrisustva();

        JOptionPane.showMessageDialog(dodajEvidencijuKursa, poruka, "Podaci o evidenciji kursa", JOptionPane.INFORMATION_MESSAGE);
    }

}
