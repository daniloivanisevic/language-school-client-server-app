/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import cordinator.Cordinator;
import forme.*;
import java.util.List;
import model.Ucenik;
import komunikacija.Komunikacija;
import forme.modeli.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.NastavnaJedinica;
import model.Grupa;

/**
 *
 * @author dakik
 */
public class PrikazUcenikaController {
    private final PrikazUcenikaForma prikazUcenikaForma;

    public PrikazUcenikaController(PrikazUcenikaForma puf) {
        this.prikazUcenikaForma = puf;
        puf.setLocationRelativeTo(null);
        puf.setTitle("Prikaz ucenika");
        puf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addActionListener();
    }

    public void otvoriFormu() {
        napuniFormu();
        prikazUcenikaForma.setVisible(true);
        ucitajGrupe();
    }

    private void napuniFormu() {
        List<Ucenik> ucenici = Komunikacija.getInstance().ucitajUcenike();
        ModelTabeleUcenici mtu = new ModelTabeleUcenici(ucenici);
        prikazUcenikaForma.getjTablePrikazUcenika().setModel(mtu);
    }

    private void addActionListener() {
        prikazUcenikaForma.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = prikazUcenikaForma.getjTablePrikazUcenika().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(prikazUcenikaForma, "Nije odabran ni jedan ucenik za brisanje!", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(prikazUcenikaForma, "Sistem je nasao ucenika.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                    ModelTabeleUcenici mtu = (ModelTabeleUcenici) prikazUcenikaForma.getjTablePrikazUcenika().getModel();
                    Ucenik u = mtu.getLista().get(red);
                    try {
                        Komunikacija.getInstance().obrisiUcenik(u);
                        JOptionPane.showMessageDialog(prikazUcenikaForma, "Sistem je obrisao ucenika.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                        napuniFormu();
                        prikazUcenikaForma.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(prikazUcenikaForma, "Sistem ne moze da obrise ucenika.", "Greska!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        prikazUcenikaForma.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = prikazUcenikaForma.getjTablePrikazUcenika().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(prikazUcenikaForma, 
                            "Nije odabran ni jedan ucenik za izmenu!", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(prikazUcenikaForma, "Sistem je nasao ucenika.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                    ModelTabeleUcenici mtu = (ModelTabeleUcenici) prikazUcenikaForma.getjTablePrikazUcenika().getModel();
                    Ucenik u = mtu.getLista().get(red);
                    Cordinator.getInstance().dodajParam("ucenik", u);
                    Cordinator.getInstance().otvoriIzmeniUcenikFormu();
                }
            }
        });
        
        prikazUcenikaForma.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imePrezime = prikazUcenikaForma.getjTextFieldPretragaImePrezime().getText().trim();
                Grupa grupa = (Grupa) prikazUcenikaForma.getjComboBoxMesto().getSelectedItem();
                
                ModelTabeleUcenici mtu = (ModelTabeleUcenici) prikazUcenikaForma.getjTablePrikazUcenika().getModel();
                mtu.pretrazi(imePrezime, grupa);
                if (mtu.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(prikazUcenikaForma, "Sistem ne moze da nadje ucenike po zadatim kriterijumima.", "Greska!", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(prikazUcenikaForma, "Sistem je našao ucenike po zadatim kriterijumima.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        
        prikazUcenikaForma.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikazUcenikaForma.getjTextFieldPretragaImePrezime().setText("");
                prikazUcenikaForma.getjComboBoxMesto().setSelectedIndex(-1);
                napuniFormu();
            }
        });
    }

    public void osveziFormu() {
        napuniFormu();
    }
    
    private void ucitajGrupe() {
    try {
        List<Grupa> allGrupe = Komunikacija.getInstance().ucitajGrupe();
        prikazUcenikaForma.getjComboBoxMesto().addItem(null); 
        for (Grupa g : allGrupe) {
            prikazUcenikaForma.getjComboBoxMesto().addItem(g);
        }
        prikazUcenikaForma.getjComboBoxMesto().setSelectedIndex(-1);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(prikazUcenikaForma, "Sistem ne može da učita listu grupa.", "Greška", JOptionPane.ERROR_MESSAGE);
    }
}
    
}
