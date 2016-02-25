
package miinaharava.kayttoliittyma;

import java.awt.event.*;
import javax.swing.*;
import miinaharava.logiikka.Miinaharava;
import miinaharava.logiikka.Vaikeusaste;


public class ValikonKuuntelija implements ActionListener{
    private JFrame frame;
    private Miinaharava peli;

    public ValikonKuuntelija(JFrame frame, Miinaharava peli) {
        this.frame = frame;
        this.peli = peli;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem valinta = (JMenuItem) e.getSource();
        if (valinta.getText().equals("Uusi peli")) {
            PelikenttaGui uusiPeli = new PelikenttaGui(new Miinaharava(peli.getVaikeus()));
            uusiPeli.setLocationRelativeTo(frame);
            frame.dispose();
        }
        if (valinta.getText().equals("Lopeta")) {
            System.exit(0);
        }
        if (valinta.getText().equals("Helppo")) {
            PelikenttaGui uusiPeli = new PelikenttaGui(new Miinaharava(Vaikeusaste.HELPPO));
            uusiPeli.setLocationRelativeTo(frame);
            frame.dispose();
        }
        if (valinta.getText().equals("Haastava")) {
            PelikenttaGui uusiPeli = new PelikenttaGui(new Miinaharava(Vaikeusaste.HAASTAVA));
            uusiPeli.setLocationRelativeTo(frame);
            frame.dispose();
        }
        if (valinta.getText().equals("Vaikea")) {
            PelikenttaGui uusiPeli = new PelikenttaGui(new Miinaharava(Vaikeusaste.VAIKEA));
            uusiPeli.setLocationRelativeTo(frame);
            frame.dispose();
        }
    }
    
    
    
}
