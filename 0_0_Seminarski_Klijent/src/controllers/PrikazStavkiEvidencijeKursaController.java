/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import cordinator.Cordinator;
import forme.PrikazStavkiEvidencijeKursaForma;
import forme.modeli.ModelTabeleStavkaEvidencijaKursa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.EvidencijaKursa;
import model.StavkaEvidencijeKursa;
import komunikacija.Komunikacija;
import model.Ucenik;
import model.Grupa;

/**
 *
 * @author dakik
 */
public class PrikazStavkiEvidencijeKursaController {
    private PrikazStavkiEvidencijeKursaForma psekf;
    private EvidencijaKursa ek;
    private boolean isInitialLoad = true;

    public PrikazStavkiEvidencijeKursaController(PrikazStavkiEvidencijeKursaForma psekf, EvidencijaKursa ek) {
        this.psekf = psekf;
        this.ek = ek;
        otvoriFormu();
        addActionListener();
    }
    
    public void otvoriFormu(){
        pripremiFormu();
        psekf.setLocationRelativeTo(null);
        psekf.setTitle("Prikaz stavki izabrane evidencije kursa");
        psekf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        psekf.setVisible(true);
        psekf.getjLabelBroj().setText("Stavke evidencije broj "+ek.getIdEvidencijaKursa());
    }

    private void pripremiFormu() {
        try {
            // Učitavam listu stavki evidencija sa servera
            List<StavkaEvidencijeKursa> listaSEK = Komunikacija.getInstance().ucitajStavkeEvidencijeKursa(ek);
            
            // Kreiram i postavljamo model tabele
            ModelTabeleStavkaEvidencijaKursa mtsek = new ModelTabeleStavkaEvidencijaKursa(listaSEK);
            psekf.getjTableStavkeEK().setModel(mtsek);

            // isInitialLoad
            if (isInitialLoad) {
                JOptionPane.showMessageDialog(psekf, "Sistem je učitao stavke evidencije kursa.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                isInitialLoad = false;
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(psekf, "Sistem ne može da učita stavke evidencije kursa.", "Greška", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.getMessage());
        }
    }
    

    private void addActionListener() {

    }

    public void osveziFormu() {
        pripremiFormu();
    }
    
}
