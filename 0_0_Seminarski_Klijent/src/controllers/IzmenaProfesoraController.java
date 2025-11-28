/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

/**
 *
 * @author dakik
 */
import cordinator.Cordinator;
import forme.FormaMod;
import forme.IzmenaProfesoraForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Profesor;

public class IzmenaProfesoraController {

    private final IzmenaProfesoraForma izmenaProfForma;
    private Profesor profesor;

    public IzmenaProfesoraController(IzmenaProfesoraForma ipf, Profesor profesor) {
        this.izmenaProfForma = ipf;
        this.profesor = profesor;

        ipf.setTitle("Izmena profesora");
        ipf.setLocationRelativeTo(null);
        ipf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        pripremiFormu();
        dodajActionListenere();
    }

    public void otvoriFormu() {
        izmenaProfForma.setVisible(true);
    }

    private void pripremiFormu() {
        // Popunjavam formu podacima iz objekta profesor
        if (profesor != null) {
            izmenaProfForma.getjTextFieldID().setText(String.valueOf(profesor.getIdProfesor()));
            izmenaProfForma.getjTextFieldID().setEditable(false); // ID se ne menja
            izmenaProfForma.getjTextFieldIme().setText(profesor.getIme());
            izmenaProfForma.getjTextFieldPrezime().setText(profesor.getPrezime());

            izmenaProfForma.getjTextFieldEmail().setText(profesor.getEmail());
            izmenaProfForma.getjTextFieldSifra().setText(profesor.getPassword());
        }
    }

    private void dodajActionListenere() {
        izmenaProfForma.addBtnSacuvajIzmeneActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvajIzmene();
            }
        });
    }

    private void sacuvajIzmene() {
        try {
            // Validacija unosa
            String ime = izmenaProfForma.getjTextFieldIme().getText().trim();
            String prezime = izmenaProfForma.getjTextFieldPrezime().getText().trim();
            String email = izmenaProfForma.getjTextFieldEmail().getText().trim();
            String sifra = izmenaProfForma.getjTextFieldSifra().getText().trim();

            if (ime.isEmpty() || prezime.isEmpty() || email.isEmpty() || sifra.isEmpty()) {
                JOptionPane.showMessageDialog(izmenaProfForma, "Sva polja moraju biti popunjena!", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //Popunjavam objekat profesor novim podacima
            this.profesor.setIme(ime);
            this.profesor.setPrezime(prezime);
            this.profesor.setEmail(email);
            this.profesor.setPassword(sifra);

            //Pozivam metodu za izmenu u Komunikacija klasi
            Komunikacija.getInstance().izmeniProfesora(this.profesor);

            // Ažuriram Cordinator objekat kako bi se promene odmah videle na glavnoj formi
            Cordinator.getInstance().setUlogovani(this.profesor);

            JOptionPane.showMessageDialog(izmenaProfForma, "Sistem je zapamtio izmene profesora.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            Cordinator.getInstance().osveziPrikazProfesora();
            izmenaProfForma.dispose(); // Zatvaranje forme

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(izmenaProfForma, "Sistem ne može da zapamti izmene profesora.", "Greška", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
