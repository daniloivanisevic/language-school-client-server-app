/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import forme.*;
import model.NastavnaJedinica;
import java.util.*;
import komunikacija.Komunikacija;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import cordinator.Cordinator;
import forme.modeli.ModelTabeleNastavneJedinice;
import javax.swing.JFrame;

/**
 *
 * @author dakik
 */
public class PrikazNastavneJediniceController {
    private final PrikazNastavneJediniceForma prikazNJForma;

    public PrikazNastavneJediniceController(PrikazNastavneJediniceForma pnjf) {
        this.prikazNJForma = pnjf;
        pnjf.setLocationRelativeTo(null);
        pnjf.setTitle("Prikaz nastavnih jedinica");
        pnjf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        prikazNJForma.setVisible(true);
    }

    private void pripremiFormu() {
        List<NastavnaJedinica> kursevi = Komunikacija.getInstance().ucitajNastavneJedinice();
        ModelTabeleNastavneJedinice mtk = new ModelTabeleNastavneJedinice(kursevi);
        prikazNJForma.getjTablePrikazNJ().setModel(mtk);
    }

    private void addActionListener() {
        prikazNJForma.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = prikazNJForma.getjTablePrikazNJ().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(prikazNJForma, "Nije odabran ni jedna nastavna jedinica za brisanje!", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                }else{
                    ModelTabeleNastavneJedinice mtk = (ModelTabeleNastavneJedinice) prikazNJForma.getjTablePrikazNJ().getModel();
                    NastavnaJedinica k = mtk.getLista().get(red);
                    try{
                        Komunikacija.getInstance().obrisiNastavnuJedinicu(k);
                        JOptionPane.showMessageDialog(prikazNJForma, "Sistem je obrisao nastavnu jedinicu.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(prikazNJForma, "Sistem ne moze da obrise nastavnu jedinicu.", "Greska!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        prikazNJForma.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = prikazNJForma.getjTablePrikazNJ().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(prikazNJForma, "Nije odabrana ni jedna nastavna jedinica za izmenu!", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                }else{
                    ModelTabeleNastavneJedinice mtk = (ModelTabeleNastavneJedinice) prikazNJForma.getjTablePrikazNJ().getModel();
                    NastavnaJedinica k = mtk.getLista().get(red);
                    Cordinator.getInstance().dodajParam("nastavnaJedinica", k);
                    Cordinator.getInstance().otvoriIzmeniNastavnaJedinicaFormu();
                }
            }
        });
        
        prikazNJForma.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = prikazNJForma.getjTextFieldPretragaNaziv().getText().trim();
                String opis = prikazNJForma.getjTextAreaOpis().getText().trim();
                
                ModelTabeleNastavneJedinice mtk = (ModelTabeleNastavneJedinice) prikazNJForma.getjTablePrikazNJ().getModel();
                mtk.pretrazi(naziv, opis);
            }
        });
        
        prikazNJForma.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikazNJForma.getjTextFieldPretragaNaziv().setText("");
                prikazNJForma.getjTextAreaOpis().setText("");
                pripremiFormu();
            }
        });
    }

    public void osveziFormu() {
        pripremiFormu();
    }
    
}
