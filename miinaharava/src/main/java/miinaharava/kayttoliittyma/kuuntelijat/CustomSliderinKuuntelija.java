package miinaharava.kayttoliittyma.kuuntelijat;

import javax.swing.*;
import javax.swing.event.*;
import miinaharava.kayttoliittyma.CustomPelinLuontiIkkuna;

/**
 *
 * @author Marko Vainio
 */
public class CustomSliderinKuuntelija implements ChangeListener {

    private JLabel teksti;
    private JSlider slider;
    private JSlider miinat;
    private CustomPelinLuontiIkkuna frame;

    public CustomSliderinKuuntelija(JLabel teksti, CustomPelinLuontiIkkuna frame) {
        this.teksti = teksti;
        this.slider = slider;
        this.miinat = miinat;
        this.frame = frame;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider muutettu = (JSlider) e.getSource();
        if (teksti.getText().contains("Korkeus")) {
            teksti.setText("Korkeus: " + muutettu.getValue());
            frame.paivitaMiinat();
        }
        if (teksti.getText().contains("Leveys")) {
            teksti.setText("Leveys: " + muutettu.getValue());
            frame.paivitaMiinat();
        }
        if (teksti.getText().contains("Miinat")) {
            teksti.setText("Miinat: " + muutettu.getValue());
            frame.paivitaMiinat();
        }
    }

}
