package miinaharava.kayttoliittyma;

import java.awt.event.*;
import javax.swing.*;
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
    private JLabel miinojenLkm;
    private Timer kello;
    private PelikenttaGui frame;

    /**
     * Luo hiirenkuuntelijan joka avaa ruudun tai merkkaa sen riippuen hiiren
     * painikkeesta.
     *
     * @param x JButtonin x-koordinaatti
     * @param y JButtonin y-koordinaatti
     * @param peli Käynnissä oleva peli
     * @param nappi JButton jota painettiin
     * @param ruudut Lista pelialustalla olevista JButtoneista
     * @param kierrostenLkm Päivitettävä JLabel olio
     */
    public RuudunAvaaja(int x, int y, Miinaharava peli, JButton nappi, JButton[][] ruudut, JLabel miinojenLkm, Timer kello,PelikenttaGui frame) {
        this.x = x;
        this.y = y;
        this.nappi = nappi;
        this.peli = peli;
        this.alusta = peli.getPelialusta();
        this.ruudut = ruudut;
        this.miinojenLkm = miinojenLkm;
        this.kello = kello;
        this.frame = frame;
    }

    /**
     * Avaa kaikki viereikkäiset nollat ja asettaa JButtonin epäaktiiviseksi.
     */
    public void avaaNollat() {
        for (Ruutu ruutu : peli.getNollanViereiset()) {
            JButton avattavaRuutu = ruudut[ruutu.getX()][ruutu.getY()];
            avattavaRuutu.setEnabled(false);
            avattavaRuutu.setText(ruutu.toString());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            avaaRuutu();
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            merkkaaRuutu();
        }
    }

    private void merkkaaRuutu() {
        ImageIcon icon = new ImageIcon("Lippu.png");
        Ruutu merkattava = alusta.getAlusta()[x][y];
        if (merkattava.onMerkattu()) {
            peli.postaRuudunMerkinta(merkattava);
            ruudut[x][y].setIcon(null);
        } else {
            peli.merkkaaRuutu(merkattava);
            ruudut[x][y].setIcon(icon);
        }
        miinojenLkm.setText("Miinoja jäljellä  " + peli.getMiinojaJaljella());
    }

    private void avaaRuutu() {
        Ruutu avattava = alusta.getAlusta()[x][y];
        if (!peli.avaaRuutu(avattava)) {
            havio();
        }
        nappi.setEnabled(false);
        nappi.setText(avattava.toString());
        if (avattava.getViereisetMiinat() == 0) {
            avaaNollat();
        }
        if (peli.kaikkiAvattu()) {
            voitto();
        }
    }

    private void voitto() {
        PelinPaatosGui voittoIlmoitus = new PelinPaatosGui(frame, "voitto");
        kello.stop();
    }
    
    

    private void havio() {
        ImageIcon icon = new ImageIcon("Miina.png");
        for (int i = 0; i < alusta.getKorkeus(); i++) {
            for (int j = 0; j < alusta.getLeveys(); j++) {
                ruudut[j][i].setEnabled(false);
                if (alusta.getAlusta()[j][i].sisaltaaMiinan()) {
                    ruudut[j][i].setIcon(icon);
                    ruudut[j][i].setDisabledIcon(icon);
                }
            }
        }
        kello.stop();
        PelinPaatosGui havioIlmoitus = new PelinPaatosGui(frame, "havio");
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
