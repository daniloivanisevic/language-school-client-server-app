/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;
import model.Ucenik;
import model.Grupa;

/**
 *
 * @author dakik
 */
public class ModelTabeleUcenici extends AbstractTableModel {

   
    List<Ucenik> lista;
    String[] kolone = {"IdUcenik", "Ime", "Prezime", "Grupa"};

    public ModelTabeleUcenici(List<Ucenik> lista) {
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
        Ucenik k = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return k.getIdUcenik();
            case 1:
                return k.getIme();
            case 2:
                return k.getPrezime();
            case 3:
                return k.getGrupa().getNaziv();
            default:
                return "N/A";
        }
    }

    public List<Ucenik> getLista() {
        return lista;
    }
   
    

   public void pretrazi(String imePrezime, Grupa grupa) {
    if (lista == null || lista.isEmpty()) {
        System.out.println("Lista učenika je prazna");
        return;
    }
    // ako je null, prazan string, ako nije prebaci u mala slova
    String unos = imePrezime == null ? "" : imePrezime.toLowerCase().trim();

    List<Ucenik> filteredList = lista.stream()
        .filter(k -> unos.isEmpty()
            || k.getIme().toLowerCase().contains(unos)
            || k.getPrezime().toLowerCase().contains(unos)
            || (k.getIme() + " " + k.getPrezime()).toLowerCase().contains(unos)
            || (k.getPrezime() + " " + k.getIme()).toLowerCase().contains(unos))
        .filter(k -> grupa == null || k.getGrupa().getIdGrupa() == grupa.getIdGrupa())
        .collect(Collectors.toList());

    this.lista = filteredList;
    System.out.println("Filtrirano: " + filteredList.size() + " učenika");
    fireTableDataChanged();
}


}
