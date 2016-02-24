package miinaharava.kayttoliittyma;

import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import javax.swing.*;


public class KellonKuuntelija implements ActionListener{
    private JLabel kentta;
    private long aloitusAika = System.currentTimeMillis();

    public KellonKuuntelija(JLabel kentta) {
        this.kentta = kentta;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long nykyinenAika = System.currentTimeMillis();
        long sekunnit = TimeUnit.MILLISECONDS.toSeconds(nykyinenAika - aloitusAika) % 60;
        long minuutit = TimeUnit.MILLISECONDS.toMinutes(nykyinenAika - aloitusAika) % 60;
        kentta.setText("" + minuutit + "  :  " + sekunnit);
    }
    
    
}
