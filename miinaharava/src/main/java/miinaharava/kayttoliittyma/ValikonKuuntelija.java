package miinaharava.kayttoliittyma;

import java.awt.event.*;
import javax.swing.*;
import miinaharava.logiikka.Miinaharava;
import miinaharava.logiikka.Vaikeusaste;

public class ValikonKuuntelija implements ActionListener {

    private JFrame frame;
    private Miinaharava peli;

    public ValikonKuuntelija(JFrame frame, Miinaharava peli) {
        this.frame = frame;
        this.peli = peli;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem valinta = (JMenuItem) e.getSource();
        PelikenttaGui uusiPeli = null;
        if (valinta.getText().equals("Uusi peli")) {
            uusiPeli = new PelikenttaGui(new Miinaharava(peli.getVaikeus()));
        }
        if (valinta.getText().equals("Lopeta")) {
            System.exit(0);
        }
        if (valinta.getText().equals("Helppo")) {
            uusiPeli = new PelikenttaGui(new Miinaharava(Vaikeusaste.HELPPO));
        }
        if (valinta.getText().equals("Haastava")) {
            uusiPeli = new PelikenttaGui(new Miinaharava(Vaikeusaste.HAASTAVA));
        }
        if (valinta.getText().equals("Vaikea")) {
            uusiPeli = new PelikenttaGui(new Miinaharava(Vaikeusaste.VAIKEA));
        }
        if (valinta.getText().equals("Demo")) {
            uusiPeli = new PelikenttaGui(new Miinaharava(Vaikeusaste.DEMO));
        }
        uusiPeli.setLocationRelativeTo(frame);
        frame.dispose();
    }

}
