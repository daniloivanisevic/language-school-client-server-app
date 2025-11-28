/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Kvalifikacija;

/**
 *
 * @author dakik
 */
public class ModelTabeleKvalifikacija extends AbstractTableModel {
    List<Kvalifikacija> lista;
    String[] kolone = {"idKvalifikacija", "Naziv","Sertifikat"};

    public ModelTabeleKvalifikacija(List<Kvalifikacija> lista) {
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
            Kvalifikacija k = lista.get(rowIndex);
    
            switch (columnIndex) {
                case 0:
                    return k.getIdKvalifikacija();
                case 1:
                    return k.getNaziv();
                case 2:
                    return k.getSertifikat();
                default:
                    return null;
            }
    }
    
}
