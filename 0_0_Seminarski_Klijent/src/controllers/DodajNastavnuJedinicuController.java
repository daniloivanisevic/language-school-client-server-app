/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import forme.*;
import static forme.FormaMod.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.NastavnaJedinica;
import cordinator.Cordinator;
import javax.swing.JFrame;

/**
 *
 * @author dakik
 */
public class DodajNastavnuJedinicuController {
    private final DodajNastavnuJedinicuForma dodajNastavnuJednicnuForma;

    public DodajNastavnuJedinicuController(DodajNastavnuJedinicuForma dnjf) {
        this.dodajNastavnuJednicnuForma = dnjf;
        dnjf.setTitle("Dodavanje/Izmena nastavne jedinice");
        dnjf.setLocationRelativeTo(null);
        dnjf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        dnjf.getjTextAreaOpis();
        addActionListener();
    }
    
    public void otvoriFormu(FormaMod m){
        pripremiFormu(m);
        dodajNastavnuJednicnuForma.setVisible(true);
    }

    private void addActionListener() {
            dodajNastavnuJednicnuForma.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }
            private void dodaj(ActionEvent e){
                String noviNaziv = dodajNastavnuJednicnuForma.getjTextFieldNoviNaziv().getText().trim();
                String noviOpis = dodajNastavnuJednicnuForma.getjTextAreaOpis().getText();
                
                
                NastavnaJedinica novaNJ = new NastavnaJedinica(-1, noviNaziv, noviOpis);
                
                try{
                    Komunikacija.getInstance().dodajNastavnuJedinicu(novaNJ);
                    JOptionPane.showMessageDialog(dodajNastavnuJednicnuForma, "Sistem je zapamtio nastavnu jedinicu.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                    dodajNastavnuJednicnuForma.dispose();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(dodajNastavnuJednicnuForma, "Sistem ne moze da zapamti nastavnu jedinicu.", "Greška!", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
            
            dodajNastavnuJednicnuForma.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }
            private void izmeni(ActionEvent e){
                int sifra = Integer.parseInt(dodajNastavnuJednicnuForma.getjTextFieldSifraNJ().getText());
                String noviNaziv = dodajNastavnuJednicnuForma.getjTextFieldNoviNaziv().getText().trim();
                String noviOpis = dodajNastavnuJednicnuForma.getjTextAreaOpis().getText();
                
                NastavnaJedinica novaNJ = new NastavnaJedinica(sifra, noviNaziv, noviOpis);
                
                try{
                    Komunikacija.getInstance().izmeniNastavnuJedinicu(novaNJ);
                    JOptionPane.showMessageDialog(dodajNastavnuJednicnuForma, "Sistem je zapamtio nastavnu jedinicu.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                    dodajNastavnuJednicnuForma.dispose();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(dodajNastavnuJednicnuForma, "Sistem ne moze da zapamti nastavnu jedinicu.", "Greška!", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
    }

    private void pripremiFormu(FormaMod m) {
        switch (m) {
            case DODAJ:
                dodajNastavnuJednicnuForma.getjTextFieldSifraNJ().setEnabled(false);
                dodajNastavnuJednicnuForma.getjButtonIzmeni().setVisible(false);
                dodajNastavnuJednicnuForma.getjButtonDodaj().setVisible(true);
                dodajNastavnuJednicnuForma.getjButtonDodaj().setEnabled(true);
                break;
            case IZMENI:
                dodajNastavnuJednicnuForma.getjButtonIzmeni().setVisible(true);
                dodajNastavnuJednicnuForma.getjButtonDodaj().setVisible(false);
                dodajNastavnuJednicnuForma.getjButtonIzmeni().setEnabled(true);
                
                NastavnaJedinica k = (NastavnaJedinica) Cordinator.getInstance().vratiParam("nastavnaJedinica");
                dodajNastavnuJednicnuForma.getjTextFieldSifraNJ().setText(k.getIdNastavnaJedinica()+"");
                dodajNastavnuJednicnuForma.getjTextFieldNoviNaziv().setText(k.getNaziv());
                dodajNastavnuJednicnuForma.getjTextAreaOpis().setText(k.getOpis());

                break;
            default:
                throw new AssertionError();
        }
    }
    
    
}
