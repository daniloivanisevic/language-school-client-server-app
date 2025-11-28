/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import forme.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Grupa;

/**
 *
 * @author dakik
 */
public class DodajGrupuController {
    
    private final DodajGrupuForma forma;
    private final DodajUcenikaController parentKontroler; // Referenca na formu za dodavanje kupca

    public DodajGrupuController(DodajGrupuForma forma, DodajUcenikaController pk) {
        this.forma = forma;
        this.parentKontroler = pk;
        forma.setTitle("Dodavanje nove grupe");
        forma.setLocationRelativeTo(null);
        forma.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addActionListener();
    }
    
    private void addActionListener() {
        forma.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvajGrupu();
            }

            private void sacuvajGrupu() {
                try {
                    String nazivGrupe = forma.getjTextFieldNazivGrupe().getText().trim();
                    
                    if (nazivGrupe.isEmpty()) {
                        JOptionPane.showMessageDialog(forma, "Naziv grupe ne sme biti nepopunjen!", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    
                    Grupa novaGrupa = new Grupa(-1, nazivGrupe);
                    

                    Komunikacija.getInstance().dodajGrupu(novaGrupa);
                    JOptionPane.showMessageDialog(forma, "Sistem je zapamtio grupu.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Osvežavanje JComboBox-a u roditeljskoj formi
                    parentKontroler.ucitajGrupe();
                    
                    forma.dispose();
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(forma, "Greška prilikom dodavanja grupe: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void otvoriFormu() {
        forma.setVisible(true);
    }
}
