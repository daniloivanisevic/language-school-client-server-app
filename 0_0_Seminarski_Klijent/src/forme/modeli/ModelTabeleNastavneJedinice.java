/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import javax.swing.table.AbstractTableModel;
import model.NastavnaJedinica;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author dakik
 */
public class ModelTabeleNastavneJedinice extends AbstractTableModel {
    
    List<NastavnaJedinica> lista;
    String[] kolone = {"idNastavnaJedinica", "Naziv", "Opis"};

    public ModelTabeleNastavneJedinice(List<NastavnaJedinica> lista) {
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
        NastavnaJedinica k = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return k.getIdNastavnaJedinica();
            case 1: return k.getNaziv();
            case 2: return k.getOpis();
            default: return "N/A";
        }
    }

    public List<NastavnaJedinica> getLista() {
        return lista;
    }

    public void pretrazi(String naziv, String opis) {
        List<NastavnaJedinica> filteredList = lista.stream()
                .filter(k -> (naziv == null || naziv.isEmpty() || k.getNaziv().toLowerCase().contains(naziv.toLowerCase())))
                .filter(k -> (opis == null || opis.isEmpty() || k.getOpis().toLowerCase().contains(opis.toLowerCase())))
                .collect(Collectors.toList());
        this.lista = filteredList;
        fireTableDataChanged();
    }
    
}
