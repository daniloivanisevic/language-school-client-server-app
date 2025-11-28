/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import forme.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Kvalifikacija;
import komunikacija.Komunikacija;

/**
 *
 * @author dakik
 */
public class DodajKvalifikacijuController {
    
    private DodajKvalifikacijuForma dodajKvalForma;

    public DodajKvalifikacijuController(DodajKvalifikacijuForma dkf) {
        this.dodajKvalForma = dkf;
        //pripremiFormu();

        dkf.setTitle("Dodavanje kvalifikacije");
        dkf.setLocationRelativeTo(null);
        dkf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addActionListener();
    }

    private void pripremiFormu() {
    }
    
    private void addActionListener() {
        dodajKvalForma.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nazivKval = dodajKvalForma.getjTextFieldNazivKval().getText().trim();
                    String sertifikat = dodajKvalForma.getjTextFieldSertifikat().getText().trim();
                    
                    if (nazivKval.isEmpty() || sertifikat.isEmpty()) {
                        throw new Exception("Morate uneti sve podatke o kvalifikaciji.");
                    }
                   
                    
                    Kvalifikacija novaKval = new Kvalifikacija(-1,nazivKval,sertifikat);
                    
                    Komunikacija.getInstance().dodajKval(novaKval);

                    JOptionPane.showMessageDialog(dodajKvalForma, "Sistem je zapamtio kvalifikaciju.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                    
                    dodajKvalForma.dispose();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dodajKvalForma, "Sistem ne moze da zapamti kvalifikaciju.", "Greška!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void otvoriFormu() {
        dodajKvalForma.setVisible(true);
    }
}
