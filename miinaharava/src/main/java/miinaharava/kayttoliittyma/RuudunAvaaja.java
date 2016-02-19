package miinaharava.kayttoliittyma;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import miinaharava.domain.Pelialusta;
import miinaharava.domain.Ruutu;
import miinaharava.logiikka.Miinaharava;

/**
 * RuudunAvaajaa kutsutaan kun painetaan pelialustalla olevaa JButtonia.
 * Määrittelee painalluksen jälkeiset tapahtumat.
 *
 * @author markovai
 */
public class RuudunAvaaja implements ActionListener {

    private int x;
    private int y;
    private Pelialusta alusta;
    private Miinaharava peli;
    private JButton nappi;
    private JButton[][] ruudut;

    public RuudunAvaaja(int x, int y, Pelialusta alusta, JButton nappi, JButton[][] ruudut) {
        this.alusta = alusta;
        this.x = x;
        this.y = y;
        this.nappi = nappi;
        this.peli = new Miinaharava(alusta, null);
        this.ruudut = ruudut;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Ruutu avattava = alusta.getAlusta()[x][y];
        if (!peli.avaaRuutu(avattava)) {
            for (int i = 0; i < ruudut.length; i++) {
                for (int j = 0; j < ruudut.length; j++) {
                    ruudut[j][i].setEnabled(false);
                }
            }
        }
        nappi.setEnabled(false);
        nappi.setText(avattava.toString());
        nappi.setMargin(new Insets(5, 5, 5, 5));
        if (avattava.getViereisetMiinat() == 0) {
            avaaNollat(avattava);
        }

    }

    public void avaaNollat(Ruutu avattava) {
        for (Ruutu viereinenRuutu : avattava.viereisetRuudut(alusta.getAlusta(), x, y)) {
            if (!viereinenRuutu.onKiinni()) {
                ruudut[viereinenRuutu.getX()][viereinenRuutu.getY()].doClick();
            }
        }

    }
}
