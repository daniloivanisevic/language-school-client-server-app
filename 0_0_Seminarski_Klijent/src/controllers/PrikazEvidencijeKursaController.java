/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import cordinator.Cordinator;
import forme.PrikazEvidencijeKursaForma;
import forme.PrikazStavkiEvidencijeKursaForma;
import forme.modeli.ModelTabeleEvidencijaKursa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import komunikacija.Komunikacija;
import model.Ucenik;
import model.Profesor;
import model.EvidencijaKursa;

/**
 *
 * @author dakik
 */
public class PrikazEvidencijeKursaController {

    private PrikazEvidencijeKursaForma forma;
    private boolean isInitialLoad = true;

    public PrikazEvidencijeKursaController(PrikazEvidencijeKursaForma forma) {
        this.forma = forma;
        otvoriFormu();
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        forma.setLocationRelativeTo(null);
        forma.setTitle("Prikaz evidencija");
        forma.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        forma.setVisible(true);

    }

    private void pripremiFormu() {
        try {
            // Učitavam listu računa sa servera
            List<EvidencijaKursa> listaEK = Komunikacija.getInstance().ucitajListuEvidencija();

            // Kreiram i postavljam model tabele
            ModelTabeleEvidencijaKursa model = new ModelTabeleEvidencijaKursa(listaEK);
            forma.getjTableEK().setModel(model);

            TableRowSorter<ModelTabeleEvidencijaKursa> sorter = new TableRowSorter<>(model);
            forma.getjTableEK().setRowSorter(sorter);

            popuniComboBoxevePretraga();

            if (isInitialLoad) {
                JOptionPane.showMessageDialog(forma, "Sistem je učitao listu evidencija.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                isInitialLoad = false;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da učita listu evidencija. Detalji: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addActionListener() {
        dodajActionListenerNaDugmePrikaziStavke();
        dodajActionListenerNaDugmePretraziEK();
        dodajActionListenerNaDugmeResetuj();
        dodajActionListenerNaDugmeIzmeni();
        dodajActionListenerNaTabelu();
    }

    private void dodajActionListenerNaDugmePrikaziStavke() {
        forma.addBtnPrikaziSEKActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = forma.getjTableEK().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(forma, "Morate odabrati evidenciju kursa.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                } else {
                    int modelRow = forma.getjTableEK().convertRowIndexToModel(red);
                    ModelTabeleEvidencijaKursa model = (ModelTabeleEvidencijaKursa) forma.getjTableEK().getModel();
                    EvidencijaKursa odabraniEK = model.getListaEK().get(modelRow);
                    Cordinator.getInstance().otvoriPrikazStavkiEKFormu(odabraniEK);
                }
            }
        });
    }

    private void dodajActionListenerNaDugmePretraziEK() {
        forma.addBtnPretraziEKActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretraziEK();
            }
        });
    }

    private void dodajActionListenerNaDugmeResetuj() {
        forma.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Resetovanje polja za pretragu na default vrednosti
                forma.getjTextFieldID().setText("");
                forma.getjComboBoxPretragaProfesor().setSelectedIndex(-1);
                forma.getjComboBoxPretragaUcenik().setSelectedIndex(-1);

                // Osvežavanje tabele sa svim evidencijama
                osveziTabelu();
            }
        });
    }

    private void dodajActionListenerNaDugmeIzmeni() {
        forma.addBtnIzmeniEKActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeniOdabraniEK();
            }
        });
    }

    private void dodajActionListenerNaTabelu() {
        forma.getjTableEK().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int red = forma.getjTableEK().getSelectedRow();

                if (red == -1) {
                    JOptionPane.showMessageDialog(forma, "Sistem ne može da nađe evidenciju.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (e.getClickCount() == 1) {
                    try {
                        int modelRow = forma.getjTableEK().convertRowIndexToModel(red);
                        ModelTabeleEvidencijaKursa model = (ModelTabeleEvidencijaKursa) forma.getjTableEK().getModel();
                        EvidencijaKursa odabranaEK = model.getListaEK().get(modelRow);

                        // Prvi JOptionPane
                        JOptionPane.showMessageDialog(forma, "Sistem je našao evidenciju kursa.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

                        String poruka = "ID evidencije kursa: " + odabranaEK.getIdEvidencijaKursa() + "\n"
                                + "Ukupno prisustva: " + odabranaEK.getUkupnaPrisustva() + "\n"
                                + "Nivo: " + odabranaEK.getNivo() + "\n"
                                + "Profesor: " + odabranaEK.getProfesor().toString() + "\n"
                                + "Ucenik: " + odabranaEK.getUcenik().toString();

                        JOptionPane.showMessageDialog(forma, poruka, "Podaci o evidenciji", JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(forma, "Sistem ne može da nađe evidenciju.", "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void pretraziEK() {
        int idEK = 0;
        if (!forma.getjTextFieldID().getText().isEmpty()) {
            try {
                idEK = Integer.parseInt(forma.getjTextFieldID().getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(forma, "ID evidencije mora biti broj!", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        Profesor profesor = (Profesor) forma.getjComboBoxPretragaProfesor().getSelectedItem();
        Ucenik ucenik = (Ucenik) forma.getjComboBoxPretragaUcenik().getSelectedItem();

        // Inicijalizujem listu van try-catch bloka da bude vidljiva svuda
        List<EvidencijaKursa> EKfilter = new ArrayList<>();

        try {

            EvidencijaKursa EKZaPretragu = new EvidencijaKursa(idEK, LocalDate.MIN, LocalDate.MAX, 0, "", profesor, ucenik);
            List<EvidencijaKursa> ek = Komunikacija.getInstance().pretraziEvidencijuKursa(EKZaPretragu);
            EKfilter.addAll(ek);

            ModelTabeleEvidencijaKursa model = (ModelTabeleEvidencijaKursa) forma.getjTableEK().getModel();
            model.setListaEK(EKfilter);
            model.fireTableDataChanged();

            if (EKfilter.isEmpty()) {
                JOptionPane.showMessageDialog(forma, "Sistem ne može da nađe evidencije po zadatim kriterijumima.", "Greška", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(forma, "Sistem je našao evidencije po zadatim kriterijumima.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da nađe evidencije po zadatim kriterijumima. Detalji: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void izmeniOdabraniEK() {
        int red = forma.getjTableEK().getSelectedRow();

        if (red == -1) {
            JOptionPane.showMessageDialog(forma, "Morate izabrati evidenciju za izmenu.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int modelRow = forma.getjTableEK().convertRowIndexToModel(red);
            ModelTabeleEvidencijaKursa model = (ModelTabeleEvidencijaKursa) forma.getjTableEK().getModel();
            EvidencijaKursa odabraniEK = model.getListaEK().get(modelRow);

            Cordinator.getInstance().otvoriIzmeniEKFormu(odabraniEK);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da učita odabranu evidenciju za izmenu. Detalji: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void popuniComboBoxevePretraga() {
        try {
            List<Ucenik> ucenici = Komunikacija.getInstance().ucitajUcenike();
            List<Profesor> profesori = Komunikacija.getInstance().ucitajProfesore();

            forma.getjComboBoxPretragaUcenik().removeAllItems();
            forma.getjComboBoxPretragaUcenik().addItem(null);
            for (Ucenik ucenik : ucenici) {
                forma.getjComboBoxPretragaUcenik().addItem(ucenik);
            }
            forma.getjComboBoxPretragaUcenik().setSelectedIndex(-1);

            forma.getjComboBoxPretragaProfesor().removeAllItems();
            forma.getjComboBoxPretragaProfesor().addItem(null);
            for (Profesor p : profesori) {
                forma.getjComboBoxPretragaProfesor().addItem(p);
            }
            forma.getjComboBoxPretragaProfesor().setSelectedIndex(-1);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Greška prilikom popunjavanja polja za pretragu.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void osveziTabelu() {
        pripremiFormu();
    }
}
