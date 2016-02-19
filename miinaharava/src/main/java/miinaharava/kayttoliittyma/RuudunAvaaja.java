package miinaharava.kayttoliittyma;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import miinaharava.domain.Pelialusta;
import miinaharava.domain.Ruutu;
import miinaharava.logiikka.Miinaharava;

/**
 * RuudunAvaajaa kutsutaan kun painetaan pelialustalla olevaa JButtonia.
 * Määrittelee painalluksen jälkeiset tapahtumat.
 *
 * @author markovai
 */
public class RuudunAvaaja implements MouseListener {

    private int x;
    private int y;
    private Pelialusta alusta;
    private Miinaharava peli;
    private JButton nappi;
    private JButton[][] ruudut;
    private JLabel kierrostenLkm;

    public RuudunAvaaja(int x, int y, Miinaharava peli, JButton nappi, JButton[][] ruudut, JLabel kierrostenLkm) {
        this.x = x;
        this.y = y;
        this.nappi = nappi;
        this.peli = peli;
        this.alusta = peli.getPelialusta();
        this.ruudut = ruudut;
        this.kierrostenLkm = kierrostenLkm;
    }

    //@Override
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
        peli.kasvataKierroksia();
    }

    public void avaaNollat(Ruutu avattava) {
        for (Ruutu ruutu : peli.getNollanViereiset()) {
            JButton avattavaRuutu = ruudut[ruutu.getX()][ruutu.getY()];
            avattavaRuutu.setEnabled(false);
            avattavaRuutu.setText(ruutu.toString());
            avattavaRuutu.setMargin(new Insets(5, 5, 5, 5));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
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
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            Ruutu merkattava = alusta.getAlusta()[x][y];
            merkattava.merkitse();
            ruudut[x][y].setText(merkattava.toString());
            nappi.setMargin(new Insets(5, 5, 5, 5));

        }
        peli.kasvataKierroksia();
        kierrostenLkm.setText("Kierros     " + peli.getKierroksia());
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }
}
