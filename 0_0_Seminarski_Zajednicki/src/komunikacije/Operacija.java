/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package komunikacije;

import java.io.Serializable;

/**
 *
 * @author dakik
 */
public enum Operacija implements Serializable{
    LOGIN, 
    UCITAJ_NASTAVNE_JEDINICE, 
    OBRISI_NASTAVNU_JEDINICU, 
    DODAJ_NASTAVNU_JEDINICU, 
    IZMENI_NASTAVNU_JEDINICU,
    UCITAJ_UCENIKE,
    OBRISI_UCENIK,
    UCITAJ_GRUPE,
    DODAJ_UCENIKA,
    DODAJ_GRUPA,
    IZMENI_UCENIK,
    UCITAJ_KVALIFIKACIJA,
    DODAJ_KVALIFIKACIJU,
    UCITAJ_EVIDENCIJA_KURSA,
    UCITAJ_STAVKE_EVIDENCIJE_KURSA,
    UCITAJ_PROFESORE,
    KREIRAJ_EVIDENCIJU_KURSA,
    PRETRAZI_EVIDENCIJE_KURSA,
    IZMENI_EVIDENCIJU_KURSA,
    IZMENI_PROFESOR,
}
