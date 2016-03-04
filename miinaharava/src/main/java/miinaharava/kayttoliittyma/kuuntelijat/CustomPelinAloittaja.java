package miinaharava.kayttoliittyma.kuuntelijat;

import java.awt.event.*;
import javax.swing.*;
import miinaharava.domain.Pelialusta;
import miinaharava.kayttoliittyma.PelikenttaGui;
import miinaharava.logiikka.Miinaharava;

public class CustomPelinAloittaja implements ActionListener {

    private JSlider leveys;
    private JSlider korkeus;
    private JSlider miinat;
    private JFrame piilotettava;

    /**
     * Luo ActionListenerin joka aloittaa pelin pelaajan valintojen perusteella.
     *
     * @param leveys kentän leveys
     * @param korkeus kentän korkeus
     * @param miinat miinojen määrä
     * @param piilotettava piilotettava CustomPelinLuontiIkkuna
     */
    public CustomPelinAloittaja(JSlider leveys, JSlider korkeus, JSlider miinat, JFrame piilotettava) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.miinat = miinat;
        this.piilotettava = piilotettava;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        piilotettava.dispose();
        PelikenttaGui uusiPeli = new PelikenttaGui(new Miinaharava(new Pelialusta(leveys.getValue(), korkeus.getValue(), miinat.getValue())));
    }

}
