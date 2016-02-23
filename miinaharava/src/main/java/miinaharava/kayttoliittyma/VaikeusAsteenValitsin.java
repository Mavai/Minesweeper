package miinaharava.kayttoliittyma;

import java.awt.event.*;
import javax.swing.*;
import miinaharava.domain.Pelialusta;
import miinaharava.logiikka.Miinaharava;
import miinaharava.logiikka.Vaikeusaste;

public class VaikeusAsteenValitsin implements ActionListener {

    private JDialog dialog;

    public VaikeusAsteenValitsin(JButton valinta, JDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton valinta = (JButton) e.getSource();
        Miinaharava peli = null;
        if (valinta.getText().equals("Helppo")) {
            peli = new Miinaharava(Vaikeusaste.HELPPO);

        } else if (valinta.getText().equals("Vaikea")) {
            peli = new Miinaharava(Vaikeusaste.VAIKEA);
        }
        dialog.dispose();
        JFrame uusiPeli = new PelikenttaGui(peli);
    }

}
