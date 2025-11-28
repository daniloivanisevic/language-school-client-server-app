/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import forme.*;

import forme.modeli.ModelTabeleKvalifikacija;
import java.util.List;
import javax.swing.JFrame;
import model.Kvalifikacija;
import komunikacija.Komunikacija;

/**
 *
 * @author dakik
 */

public class PrikazKvalifikacijaController {
    private final PrikazKvalifikacijaForma prikazKvalForma;

    public PrikazKvalifikacijaController(PrikazKvalifikacijaForma psf) {
        this.prikazKvalForma = psf;
        psf.setLocationRelativeTo(null);
        psf.setTitle("Prikaz kvalifikacija");
        psf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addActionListener();
    }
    
    public void otvoriFormu() {
        pripremiFormu();
        prikazKvalForma.setVisible(true);
    }
    
    private void pripremiFormu() {
        List<Kvalifikacija> kval = Komunikacija.getInstance().ucitajKval();
        ModelTabeleKvalifikacija mtk = new ModelTabeleKvalifikacija(kval);
        prikazKvalForma.getjTableKval().setModel(mtk);
    }

    private void addActionListener() {
    }

}
