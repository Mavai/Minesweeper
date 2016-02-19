package miinaharava.kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import miinaharava.domain.Pelialusta;
import miinaharava.logiikka.Miinaharava;

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
    private Miinaharava peli;
    private JLabel kierrostenLkm;
    private JLabel merkkienLkm;

    public MiinaharavaGui(Miinaharava peli) {
        this.pelialusta = peli.getPelialusta();
        this.leveys = pelialusta.getLeveys();
        ruudut = new JButton[leveys][leveys];
        this.peli = peli;
        this.kierrostenLkm = new JLabel("Kierros     " + peli.getKierroksia());
        this.merkkienLkm = new JLabel("Merkkejä jäljellä  " + 15);
    }

    @Override
    public void run() {
        frame = new JFrame("Miinaharava");
        frame.setPreferredSize(new Dimension(35 * leveys, 35 * leveys));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new BorderLayout());
        JPanel ruudukko = luoRuudukko();
        container.add(ruudukko, BorderLayout.CENTER);
        JPanel kierrokset = luoKierrostenLaskija();
        container.add(kierrokset, BorderLayout.SOUTH);
    }

    private JPanel luoKierrostenLaskija() {
        JPanel kierrokset = new JPanel(new GridLayout(1, 2));
        merkkienLkm.setFont(new Font("Normal", 12, 12));
        merkkienLkm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        kierrostenLkm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        kierrokset.add(kierrostenLkm);
        kierrokset.add(merkkienLkm);
        return kierrokset;
    }

    public JPanel luoRuudukko() {
        JPanel ruudukko = new JPanel(new GridLayout(leveys, leveys));
        for (int i = 0; i < pelialusta.getLeveys(); i++) {
            for (int j = 0; j < pelialusta.getLeveys(); j++) {
                JButton nappi = new JButton();
                ruudut[j][i] = nappi;
                nappi.addMouseListener(new RuudunAvaaja(j, i, peli, nappi, ruudut, kierrostenLkm));
                ruudukko.add(nappi);
            }
        }
        return ruudukko;
    }

    public JFrame getFrame() {
        return frame;
    }
}
