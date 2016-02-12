package miinaharava.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import miinaharava.domain.Pelialusta;

/**
 * Tarjoaa graafisen käyttöliittyman miinaharava-peliin.
 *
 * @author markovai
 */
public class MiinaharavaGui implements Runnable {

    private JFrame frame;
    private Pelialusta pelialusta;
    private int leveys;
    private JButton[][] ruudut;

    public MiinaharavaGui(Pelialusta pelialusta) {
        this.pelialusta = pelialusta;
        this.leveys = pelialusta.getLeveys();
        ruudut = new JButton[leveys][leveys];

    }

    @Override
    public void run() {
        frame = new JFrame("Miinaharava");
        frame.setPreferredSize(new Dimension(30 * leveys, 30 * leveys));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        JPanel ruudukko = luoRuudukko();
        container.add(ruudukko);

    }

    public JPanel luoRuudukko() {
        JPanel ruudukko = new JPanel(new GridLayout(leveys, leveys));
        for (int i = 0; i < pelialusta.getLeveys(); i++) {
            for (int j = 0; j < pelialusta.getLeveys(); j++) {
                JButton nappi = new JButton();
                ruudut[j][i] = nappi;
                nappi.addActionListener(new RuudunAvaaja(j, i, pelialusta, nappi, ruudut));
                ruudukko.add(nappi);
            }
        }
        return ruudukko;
    }

    public JFrame getFrame() {
        return frame;
    }
}
