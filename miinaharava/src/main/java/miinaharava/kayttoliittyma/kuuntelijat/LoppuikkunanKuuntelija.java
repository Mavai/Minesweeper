package miinaharava.kayttoliittyma.kuuntelijat;

import java.awt.event.*;
import javax.swing.*;
import miinaharava.kayttoliittyma.PelikenttaGui;
import miinaharava.logiikka.Miinaharava;

public class LoppuikkunanKuuntelija implements ActionListener {

    private PelikenttaGui edellinenPeli;
    private JDialog loppuikkuna;

    /**
     * Aloittaa uuden pelin tai lopettaa ohjelman perustuen käyttäjän valintaan
     * loppuikkunassa.
     *
     * @param edellinenPeli Päättyneen pelin pääikkuna.
     * @param loppuikkuna Päättyneen pelin loppuikkuna.
     */
    public LoppuikkunanKuuntelija(PelikenttaGui edellinenPeli, JDialog loppuikkuna) {
        this.edellinenPeli = edellinenPeli;
        this.loppuikkuna = loppuikkuna;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton painettu = (JButton) e.getSource();
        if (painettu.getText().equals("Uusi peli")) {
            loppuikkuna.dispose();
            PelikenttaGui uusiPeli = new PelikenttaGui(new Miinaharava(edellinenPeli.getPeli().getVaikeus()));
            uusiPeli.setLocationRelativeTo(edellinenPeli);
            edellinenPeli.dispose();
        }
        if (painettu.getText().equals("Lopeta")) {
            loppuikkuna.dispose();
            edellinenPeli.dispose();
            System.exit(0);
        }
    }

}
