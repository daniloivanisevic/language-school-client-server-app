/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.EvidencijaKursa;

/**
 *
 * @author dakik
 */
public class ModelTabeleEvidencijaKursa extends AbstractTableModel {
    private List<EvidencijaKursa> listaEK;
    private String[] kolone = {"ID Evidencija Kursa", "Datum upisa", "Datum zavrsavanja", "Ukupno prisustva","Nivo", "Profesor","Ucenik"};

    public ModelTabeleEvidencijaKursa(List<EvidencijaKursa> listaEK) {
        this.listaEK = listaEK;
    }

    @Override
    public int getRowCount() {
        if (listaEK == null) {
            return 0;
        }
        return listaEK.size();
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
        EvidencijaKursa ek = listaEK.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return ek.getIdEvidencijaKursa();
            case 1:
                return ek.getDatumUpisa().toString();
            case 2:
                return ek.getDatumZavrsavanja().toString();
            case 3:
                return ek.getUkupnaPrisustva();
            case 4:
                return ek.getNivo();
            case 5:
                return ek.getProfesor();
            case 6:
                return ek.getUcenik();
            default:
                return null;
        }
    }

    public List<EvidencijaKursa> getListaEK() {
        return listaEK;
    }

    public void setListaEK(List<EvidencijaKursa> listaEK) {
        this.listaEK = listaEK;
    }
    

    @Override
    public Class<?> getColumnClass(int columnIndex) {
    /** Objasnjenje:
     * Ova metoda je ključna za ispravno sortiranje tabele.
     * TableRowSorter poziva ovu metodu da utvrdi tip podataka svake kolone.
     * Na osnovu povratne vrednosti (npr. Long.class, Double.class),
     * sorter zna da li treba da sortira kolonu kao brojeve, tekst, ili nešto drugo.
     * Bez ove metode, sve kolone bi se sortirale kao tekst (abecedno),
     * što dovodi do pogrešnog poretka za brojeve (npr. 14, 2, 1).
     *
     * Što se tiče upitnika '?', on je takozvani "wildcard" u Javi.
     * Znači da ova metoda vraća objekat tipa Class koji može da predstavlja bilo koji tip,
     * što je neophodno jer ova metoda za svaku kolonu vraća različit tip (Long, Double, Boolean, String).
     */
        switch (columnIndex) {
            case 0: // ID Evidencija kursa
                return Long.class;
            case 1: // datum upisa
            case 2: //datum zavrsavanja
                return String.class;
            case 3: // ukupno prisustva
                return Long.class;
            case 4: // nivo
                return String.class;
            case 5: // Profesor
            case 6: //Ucenik
                return String.class; // Sortiraće se po toString() metodi
            default:
                return Object.class;
        }
    }
    
}