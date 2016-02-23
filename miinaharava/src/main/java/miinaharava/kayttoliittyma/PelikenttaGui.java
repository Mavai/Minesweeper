package miinaharava.kayttoliittyma;

import com.sun.java.swing.plaf.windows.resources.windows;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import miinaharava.domain.Pelialusta;
import miinaharava.logiikka.Miinaharava;
import sun.nio.ch.WindowsAsynchronousChannelProvider;

public class PelikenttaGui extends JFrame {

    private Pelialusta pelialusta;
    private int leveys;
    private JButton[][] ruudut;
    private Miinaharava peli;
    private JLabel kierrostenLkm;
    private JLabel merkkienLkm;

    /**
     * Luo graafisen käyttöliittymän pelille.
     *
     * @param peli Peli, joka käytttää käyttöliittymää.
     */
    public PelikenttaGui(Miinaharava peli) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.peli = peli;
        this.pelialusta = peli.getPelialusta();
        this.leveys = peli.getPelialusta().getLeveys();
        ruudut = new JButton[leveys][leveys];

        setPreferredSize(new Dimension(35 * leveys, 35 * leveys + 35));

        this.kierrostenLkm = new JLabel("Kierros     " + peli.getKierroksia());
        this.merkkienLkm = new JLabel("Merkkejä jäljellä  " + 15);

        luoKomponentit(this.getContentPane());

        pack();
        setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new BorderLayout());
        container.add(valikko(), BorderLayout.NORTH);
        JPanel ruudukko = luoRuudukko();
        container.add(ruudukko, BorderLayout.CENTER);
        JPanel kierrokset = luoKierrostenJaMerkkienLaskija();
        kierrokset.setPreferredSize(new Dimension(35 * leveys, 35));
        container.add(kierrokset, BorderLayout.SOUTH);

    }

    /**
     * Luo käyttöliittymään JPanel olion joka sisältää kierroslakurin ja
     * merkkausten määrän.
     *
     * @return JPanel käyttöliittymäkomponentti
     */
    private JPanel luoKierrostenJaMerkkienLaskija() {
        JPanel kierrokset = new JPanel(new GridLayout(1, 2));
        merkkienLkm.setFont(new Font("Normal", 12, 12));
        merkkienLkm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        kierrostenLkm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        kierrokset.add(kierrostenLkm);
        kierrokset.add(merkkienLkm);
        return kierrokset;
    }

    /**
     * Luo JPanel olion Grid layoutilla, joka sisältää JButtoneita. Toimii
     * pelialustana.
     *
     * @return JPanel ruudukko
     */
    public JPanel luoRuudukko() {
        JPanel ruudukko = new JPanel(new GridLayout(leveys, leveys));
        for (int i = 0; i < pelialusta.getLeveys(); i++) {
            for (int j = 0; j < pelialusta.getLeveys(); j++) {
                JButton nappi = new JButton();
                ruudut[j][i] = nappi;
                nappi.setMargin(new Insets(5, 5, 5, 5));
                nappi.addMouseListener(new RuudunAvaaja(j, i, peli, nappi, ruudut, kierrostenLkm));
                ruudukko.add(nappi);
            }
        }
        return ruudukko;
    }

    public JMenuBar valikko() {
        JMenuBar valikko = new JMenuBar();
        JMenu menu = new JMenu("Peli");
        valikko.add(menu);
        JMenuItem uusiPeli = new JMenuItem("Uusi peli");
        menu.add(uusiPeli);
        JMenuItem vaihdaVaikeusastetta = new JMenuItem("Vaihda vaikeusastetta");
        menu.add(vaihdaVaikeusastetta);
        JMenuItem lopeta = new JMenuItem("Lopeta");
        menu.add(lopeta);
        return valikko;
    }

}
