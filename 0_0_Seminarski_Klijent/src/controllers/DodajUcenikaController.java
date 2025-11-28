/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import cordinator.Cordinator;
import forme.DodajNastavnuJedinicuForma;
import forme.DodajUcenikaForma;
import forme.FormaMod;
import static forme.FormaMod.DODAJ;
import static forme.FormaMod.IZMENI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.NastavnaJedinica;
import model.Ucenik;
import model.Grupa;

/**
 *
 * @author dakik
 */
public class DodajUcenikaController {

    private final DodajUcenikaForma dodajUcenikaForma;

    public DodajUcenikaController(DodajUcenikaForma duf) {
        this.dodajUcenikaForma = duf;
        duf.setTitle("Dodavanje/Izmena ucenika");
        duf.setLocationRelativeTo(null);
        duf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addActionListener();
        ucitajGrupe();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dodajUcenikaForma.setVisible(true);
    }

    private void addActionListener() {
        dodajUcenikaForma.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                String ime = dodajUcenikaForma.getjTextFieldIme().getText().trim();
                String prezime = dodajUcenikaForma.getjTextFieldPrezime().getText().trim();

                Grupa izabranaGrupa = (Grupa) dodajUcenikaForma.getjComboBoxGrupa().getSelectedItem();

                if (ime.isEmpty() || prezime.isEmpty() || izabranaGrupa == null) {
                    JOptionPane.showMessageDialog(dodajUcenikaForma, "Sistem ne moze da zapamti ucenika.", "Greska!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Ucenik noviUcenik = new Ucenik(-1, ime, prezime, izabranaGrupa);

                try {
                    Komunikacija.getInstance()  .dodajUcenika(noviUcenik);
                    JOptionPane.showMessageDialog(dodajUcenikaForma, "Sistem je zapamtio ucenika.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                    dodajUcenikaForma.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dodajUcenikaForma, "Sistem ne moze da zapamti ucenika.", "Greska!", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        dodajUcenikaForma.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                int id = Integer.parseInt(dodajUcenikaForma.getjTextFieldIdEK().getText().trim());
                String ime = dodajUcenikaForma.getjTextFieldIme().getText().trim();
                String prezime = dodajUcenikaForma.getjTextFieldPrezime().getText().trim();
                Grupa izabranaGrupa = (Grupa) dodajUcenikaForma.getjComboBoxGrupa().getSelectedItem();

                Ucenik noviUcenik = new Ucenik(id, ime, prezime, izabranaGrupa);

                try {
                    Komunikacija.getInstance().izmeniUcenika(noviUcenik);
                    JOptionPane.showMessageDialog(dodajUcenikaForma, "Sistem je zapamtio ucenika.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                    dodajUcenikaForma.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dodajUcenikaForma, "Sistem ne moze da zapamti ucenika.", "Greska!", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dodajUcenikaForma.getjTextFieldIdEK().setEnabled(false);
                dodajUcenikaForma.getjButtonIzmeni().setVisible(false);
                dodajUcenikaForma.getjButtonDodaj().setVisible(true);
                dodajUcenikaForma.getjButtonDodaj().setEnabled(true);
                break;
            case IZMENI:
                dodajUcenikaForma.getjButtonIzmeni().setVisible(true);
                dodajUcenikaForma.getjButtonDodaj().setVisible(false);
                dodajUcenikaForma.getjButtonIzmeni().setEnabled(true);
                dodajUcenikaForma.getjTextFieldIdEK().setEnabled(false);
                Ucenik u = (Ucenik) Cordinator.getInstance().vratiParam("ucenik");
                dodajUcenikaForma.getjTextFieldIdEK().setText(u.getIdUcenik()+ "");
                dodajUcenikaForma.getjTextFieldIme().setText(u.getIme());
                dodajUcenikaForma.getjTextFieldPrezime().setText(u.getPrezime());
                

                for (int i = 0; i < dodajUcenikaForma.getjComboBoxGrupa().getItemCount(); i++) {
                    Grupa g = (Grupa) dodajUcenikaForma.getjComboBoxGrupa().getItemAt(i);
                    if (g.getIdGrupa()== u.getGrupa().getIdGrupa()) {
                        dodajUcenikaForma.getjComboBoxGrupa().setSelectedItem(g);
                        break; 
                    }
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    public void ucitajGrupe() {
        try {
            List<Grupa> grupe = Komunikacija.getInstance().ucitajGrupe();
            dodajUcenikaForma.getjComboBoxGrupa().removeAllItems();
            for (Grupa g : grupe) {
                dodajUcenikaForma.getjComboBoxGrupa().addItem(g);
            }
            dodajUcenikaForma.getjComboBoxGrupa().setSelectedIndex(-1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(dodajUcenikaForma, "Greška prilikom učitavanja grupe: " + e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
}
