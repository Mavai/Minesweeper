package miinaharava.kayttoliittyma.kuuntelijat;

import java.awt.event.*;
import javax.swing.*;
import miinaharava.domain.Pelialusta;
import miinaharava.kayttoliittyma.PelikenttaGui;
import miinaharava.logiikka.Miinaharava;

public class LoppuikkunanKuuntelija implements ActionListener {

    private PelikenttaGui edellinenPelikentta;
    private JDialog loppuikkuna;

    /**
     * Aloittaa uuden pelin tai lopettaa ohjelman perustuen käyttäjän valintaan
     * loppuikkunassa.
     *
     * @param edellinenPeli Päättyneen pelin pääikkuna.
     * @param loppuikkuna Päättyneen pelin loppuikkuna.
     */
    public LoppuikkunanKuuntelija(PelikenttaGui edellinenPeli, JDialog loppuikkuna) {
        this.edellinenPelikentta = edellinenPeli;
        this.loppuikkuna = loppuikkuna;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton painettu = (JButton) e.getSource();
        Miinaharava edellinenPeli = edellinenPelikentta.getPeli();
        Miinaharava uusiPeli = null;
        if (painettu.getText().equals("Uusi peli")) {
            loppuikkuna.dispose();
            if (edellinenPelikentta.getPeli().getVaikeus() != null) {
                uusiPeli = new Miinaharava(edellinenPeli.getVaikeus());
                uusiPeli.setPelaaja(edellinenPeli.getPelaaja());
            } else {
                uusiPeli = new Miinaharava(new Pelialusta(edellinenPeli.getPelialusta().getLeveys(), 
                        edellinenPeli.getPelialusta().getKorkeus(), edellinenPeli.getPelialusta().getMiinat().size()));
            }
            PelikenttaGui uusiPelikentta = new PelikenttaGui(uusiPeli);
            uusiPelikentta.setLocationRelativeTo(edellinenPelikentta);
            edellinenPelikentta.dispose();
        }
        if (painettu.getText().equals("Lopeta")) {
            loppuikkuna.dispose();
            edellinenPelikentta.dispose();
            System.exit(0);
        }
    }

}
