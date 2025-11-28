
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.StavkaEvidencijeKursa;

/**
 *
 * @author dakik
 */
public class ModelTabeleStavkaEvidencijaKursa extends AbstractTableModel {

    private List<StavkaEvidencijeKursa> listaStavki;
    private String[] kolone = {"Redni Broj", "Datum prisustva", "Prisustvo", "Nastavna jedinica"};

    public ModelTabeleStavkaEvidencijaKursa(List<StavkaEvidencijeKursa> listaStavki) {
        this.listaStavki = listaStavki;
    }

    public StavkaEvidencijeKursa getStavka(int rowIndex) {
        return listaStavki.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        if (listaStavki == null) {
            return 0;
        }
        return listaStavki.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    // {"Redni Broj", "Datum prisustva", "Prisustvo"};
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaEvidencijeKursa stavka = listaStavki.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return stavka.getRb();
            case 1:
                if(stavka.getDatumPrisustva() == null)
                    return "N/A";
                else return stavka.getDatumPrisustva().toString();
            case 2:
                return stavka.isPrisustvo();
            case 3:
                return stavka.getNastavnaJedinica().getNaziv();
            default:
                return null;
        }
    }

    public void dodajStavku(StavkaEvidencijeKursa stavka) {
        int RBsad = listaStavki.size() + 1;
        stavka.setRb(RBsad);
        listaStavki.add(stavka);
        fireTableDataChanged();
    }

    public void obrisiStavku(int red) {
        listaStavki.remove(red);
        fireTableDataChanged();
    }

    public List<StavkaEvidencijeKursa> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(List<StavkaEvidencijeKursa> listaStavki) {
        this.listaStavki = listaStavki;
    }

}
