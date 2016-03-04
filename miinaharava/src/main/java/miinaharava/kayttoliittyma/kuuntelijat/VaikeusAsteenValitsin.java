package miinaharava.kayttoliittyma.kuuntelijat;

import java.awt.event.*;
import javax.swing.*;
import miinaharava.kayttoliittyma.CustomPelinLuontiIkkuna;
import miinaharava.kayttoliittyma.PelikenttaGui;
import miinaharava.logiikka.Miinaharava;
import miinaharava.logiikka.Vaikeusaste;

public class VaikeusAsteenValitsin implements ActionListener {

    private JDialog dialog;
    private JComboBox<String> valikko;

    /**
     * Aloittaa pelin riippuen siitä mikä vaihtoehto aloitusikkunassa valittiin.
     *
     * @param dialog Aloitusikkuna
     * @param valikko JComboBox 
     */
    public VaikeusAsteenValitsin(JDialog dialog, JComboBox valikko) {
        this.dialog = dialog;
        this.valikko = valikko;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String valinta = (String) valikko.getSelectedItem();
        Miinaharava peli = null;
        if (valinta.contains("Helppo")) {
            peli = new Miinaharava(Vaikeusaste.HELPPO);
        } else if (valinta.contains("Vaikea")) {
            peli = new Miinaharava(Vaikeusaste.VAIKEA);
        } else if (valinta.contains("Haastava")) {
            peli = new Miinaharava(Vaikeusaste.HAASTAVA);
        } else if (valinta.contains("Custom")) {
            CustomPelinLuontiIkkuna uusiIkkuna = new CustomPelinLuontiIkkuna();
            dialog.dispose();
            return;
        }
        JFrame uusiPeli = new PelikenttaGui(peli);
        dialog.dispose();
    }

}
