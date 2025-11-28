/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import cordinator.Cordinator;
import forme.GlavnaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import model.Profesor;

/**
 *
 * @author dakik
 */
public class GlavnaFormController {
    private final GlavnaForma gf;

    public GlavnaFormController(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();
        pripremi();
    }

    private void addActionListeners() {
        gf.addBtnIzmeniProfilActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Profesor ulogovani = Cordinator.getInstance().getUlogovani();
                
                if (ulogovani != null) {
                    Cordinator.getInstance().otvoriIzmeniProfesoraFormu(ulogovani);
                } else {
                    JOptionPane.showMessageDialog(gf, "Nije pronađen ulogovani profesor.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void otvoriFormu() {
        Profesor ulogovani = Cordinator.getInstance().getUlogovani();
        String ime = ulogovani.getIme();
        String prezime = ulogovani.getPrezime();
        gf.setVisible(true);
        
    }
    
    private void pripremi() {
        gf.setLocationRelativeTo(null);
        gf.setTitle("Softverski sistem za poslovanje studija za strane jezike");
        
        Profesor ulogovani = Cordinator.getInstance().getUlogovani();
        
        if (ulogovani != null) {
            // Prikaz imena i prezimena
            gf.getjLabelIme().setText(" " + ulogovani.getIme());
            gf.getjLabelPrezime().setText(" " + ulogovani.getPrezime());


            // Prikaz korisničkog imena
            gf.getjLabelEmail().setText(" " + ulogovani.getEmail());
        }
     
    }

    public void osveziPrikazUlogovanogProfesora() {
        pripremi();
    }
}
