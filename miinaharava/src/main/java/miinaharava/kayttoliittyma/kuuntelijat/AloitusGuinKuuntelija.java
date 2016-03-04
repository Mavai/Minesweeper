package miinaharava.kayttoliittyma.kuuntelijat;

import java.awt.event.*;
import javax.swing.*;
import miinaharava.kayttoliittyma.CustomPelinLuontiIkkuna;
import miinaharava.kayttoliittyma.PelikenttaGui;
import miinaharava.kayttoliittyma.Tulosnakyma;
import miinaharava.logiikka.Miinaharava;
import miinaharava.logiikka.Vaikeusaste;

public class AloitusGuinKuuntelija implements ActionListener {

    private JDialog dialog;
    private JComboBox<String> valikko;
    private JTextField nimimerkki;
    private JLabel tekstikentta;

    /**
     * Aloittaa pelin riippuen siitä mikä vaihtoehto aloitusikkunassa valittiin.
     *
     * @param dialog Aloitusikkuna
     * @param valikko JComboBox
     */
    public AloitusGuinKuuntelija(JDialog dialog, JComboBox valikko, JTextField nimimerkki, JLabel tekstikentta) {
        this.dialog = dialog;
        this.valikko = valikko;
        this.nimimerkki = nimimerkki;
        this.tekstikentta = tekstikentta;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton painettu = (JButton) e.getSource();
        if (painettu.getText().equals("Aloita")) {
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
            if(!asetaPelaaja(peli)) {
                return;
            }
            PelikenttaGui uusiPeli = new PelikenttaGui(peli);
            dialog.dispose();
        } else {
            Tulosnakyma tulokset = new Tulosnakyma();
        }
    }

    public boolean asetaPelaaja(Miinaharava peli) {
        if (nimimerkki.getText().contains("-")) {
            tekstikentta.setText("Anna nimimerkki     Nimi ei saa sisältää merkkiä '-'");
            return false;
        }
        if (!nimimerkki.getText().equals("")) {
            peli.setPelaaja(nimimerkki.getText());
        }
        return true;
    }

}
