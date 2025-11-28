/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import forme.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.*;
import cordinator.Cordinator;

/**
 *
 * @author dakik
 */
public class LoginController {
    private final LogInForma lf;

    public LoginController(LogInForma lf) {
        this.lf = lf;
        lf.setTitle("Prijava profesora");
        lf.setLocationRelativeTo(null);
        addActionListeners();
    }

    private void addActionListeners() {
        lf.loginAddActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {
                String email = lf.getjTextFieldUsername().getText().trim();
                String pass = String.valueOf(lf.getjPasswordField().getPassword()).trim();
                
                Komunikacija.getInstance().konekcija();
                Profesor ulogovan = Komunikacija.getInstance().logIn(email, pass);
                
                if(ulogovan == null){
                    JOptionPane.showMessageDialog(lf, "Email i sifra nisu ispravni.", "Greska!", JOptionPane.ERROR_MESSAGE);
                }else{
                    Cordinator.getInstance().setUlogovani(ulogovan);
                    JOptionPane.showMessageDialog(lf, "Email i sifra su ispravni.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                    try{
                        Cordinator.getInstance().otvoriGlavnuFormu();
                    }catch(Exception exc){
                        JOptionPane.showMessageDialog(lf, "Ne moze da se otvori glavna forma i meni.", "Greska!", JOptionPane.ERROR_MESSAGE);
                    }
                    lf.dispose();
                }
            }
            
        });
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }

}
