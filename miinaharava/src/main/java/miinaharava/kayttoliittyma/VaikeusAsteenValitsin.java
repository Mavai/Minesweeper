
package miinaharava.kayttoliittyma;

import java.awt.event.*;
import javax.swing.*;
import miinaharava.domain.Pelialusta;
import miinaharava.logiikka.Miinaharava;

public class VaikeusAsteenValitsin implements ActionListener{
    private JDialog dialog;

    public VaikeusAsteenValitsin(JButton valinta, JDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton valinta = (JButton) e.getSource();
        if (valinta.getText().equals("Helppo")) {
            Pelialusta pelialusta = new Pelialusta(8, 10);
            Miinaharava peli = new Miinaharava(pelialusta, null);
            dialog.dispose();
            JFrame uusiPeli = new PelikenttaGui(peli);
        }
    }
    
    
    
}
