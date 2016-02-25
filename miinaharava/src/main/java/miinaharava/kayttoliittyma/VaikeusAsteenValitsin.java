package miinaharava.kayttoliittyma;

import java.awt.event.*;
import javax.swing.*;
import miinaharava.domain.Pelialusta;
import miinaharava.logiikka.Miinaharava;
import miinaharava.logiikka.Vaikeusaste;

public class VaikeusAsteenValitsin implements ActionListener {

    private JDialog dialog;
    private ButtonGroup vaihtoehdot;
    
    public VaikeusAsteenValitsin(JDialog dialog, ButtonGroup vaihtoehdot) {
        this.dialog = dialog;
        this.vaihtoehdot = vaihtoehdot;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonModel valinta = vaihtoehdot.getSelection();
        Miinaharava peli = null;
        if (valinta.getActionCommand().equals("Helppo")) {
            peli = new Miinaharava(Vaikeusaste.HELPPO);
        } else if (valinta.getActionCommand().equals("Vaikea")) {
            peli = new Miinaharava(Vaikeusaste.VAIKEA);
        } else if (valinta.getActionCommand().equals("Haastava")) {
            peli = new Miinaharava(Vaikeusaste.HAASTAVA);
        }
        dialog.dispose();
        JFrame uusiPeli = new PelikenttaGui(peli);
    }

}
