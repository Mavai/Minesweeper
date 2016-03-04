package miinaharava.kayttoliittyma;

import miinaharava.kayttoliittyma.kuuntelijat.*;
import java.awt.*;
import javax.swing.*;
import miinaharava.domain.Pelialusta;
import miinaharava.logiikka.Miinaharava;

public class PelikenttaGui extends JFrame {

    private final Pelialusta pelialusta;
    private final int leveys;
    private final JButton[][] ruudut;
    private final Miinaharava peli;
    private final JLabel kulunutAika;
    private final JLabel miinojenLkm;
    private final Timer kello;

    /**
     * Luo graafisen käyttöliittymän pelille.
     *
     * @param peli Peli, joka käytttää käyttöliittymää.
     */
    public PelikenttaGui(Miinaharava peli) {
        super("Miinaharava");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.peli = peli;
        this.pelialusta = peli.getPelialusta();
        this.leveys = peli.getPelialusta().getLeveys();
        ruudut = new JButton[leveys][pelialusta.getKorkeus()];

        this.kulunutAika = new JLabel("0  :  0", JLabel.CENTER);
        this.miinojenLkm = new JLabel("Miinoja: " + peli.getMiinojaJaljella(), JLabel.CENTER);
        this.kello = new Timer(1000, new KellonKuuntelija(kulunutAika));

        luoKomponentit(this.getContentPane());
        setResizable(false);
        pack();
        setLocationRelativeTo(this);
        setVisible(true);
        kello.start();
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new BorderLayout());
        container.add(valikko(), BorderLayout.NORTH);
        JPanel ruudukko = luoRuudukko();
        container.add(ruudukko, BorderLayout.CENTER);
        JPanel kierrokset = luoAjanJaMiinojenLaskija();
        kierrokset.setPreferredSize(new Dimension(30 * leveys, 30));
        container.add(kierrokset, BorderLayout.SOUTH);
    }

    /**
     * Luo käyttöliittymään JPanel olion joka sisältää aikalaskurin ja jäljellä
     * olevien miinojen määrän.
     *
     * @return JPanel käyttöliittymäkomponentti
     */
    private JPanel luoAjanJaMiinojenLaskija() {
        JPanel kierrokset = new JPanel(new GridLayout(1, 2));
        muokkaaAjanJaMiinojenLaskija();
        kierrokset.add(kulunutAika);
        kierrokset.add(miinojenLkm);
        return kierrokset;
    }

    private void muokkaaAjanJaMiinojenLaskija() {
        miinojenLkm.setFont(new Font("Normal", Font.BOLD, 12));
        kulunutAika.setFont(new Font("Normal", Font.BOLD, 12));
        miinojenLkm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        kulunutAika.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /**
     * Luo JPanel olion Grid layoutilla, joka sisältää JButtoneita. Toimii
     * pelialustana.
     *
     * @return JPanel ruudukko
     */
    public JPanel luoRuudukko() {
        JPanel ruudukko = new JPanel(new GridLayout(pelialusta.getKorkeus(), leveys));
        for (int i = 0; i < pelialusta.getKorkeus(); i++) {
            for (int j = 0; j < pelialusta.getLeveys(); j++) {
                JButton nappi = new JButton("", null);
                ruudut[j][i] = nappi;
                muokkaaNappia(nappi);
                nappi.addMouseListener(new PelikentanKuuntelija(j, i, peli, ruudut, miinojenLkm, kello, this));
                ruudukko.add(nappi);
            }
        }
        return ruudukko;
    }

    private void muokkaaNappia(JButton nappi) {
        nappi.setPreferredSize(new Dimension(30, 30));
        nappi.setFont(new Font("Normal", Font.BOLD, 14));
        nappi.setMargin(new Insets(0, 0, 0, 0));
        nappi.setBorder(null);
        nappi.setFocusPainted(false);
    }

    /**
     * Luo valikkopalkin peli-ikkunan yläreunaan.
     *
     * @return Palauttaa JMenuBar komponentin.
     */
    public JMenuBar valikko() {
        JMenuBar valikko = new JMenuBar();
        JMenu menu = new JMenu("Peli");
        valikko.add(menu);
        JMenuItem uusiPeli = new JMenuItem("Uusi peli");
        uusiPeli.addActionListener(new ValikonKuuntelija(this, peli));
        menu.add(uusiPeli);
        menu.add(luoVaikeusasteenVaihtaja());
        JMenuItem lopeta = new JMenuItem("Lopeta");
        lopeta.addActionListener(new ValikonKuuntelija(this, peli));
        menu.add(lopeta);
        return valikko;
    }

    private JMenu luoVaikeusasteenVaihtaja() {
        JMenu vaihdaVaikeusastetta = new JMenu("Vaikeusaste");
        String[] vaikeusasteet = new String[]{"Helppo", "Haastava", "Vaikea"};
        for (int i = 0; i < vaikeusasteet.length; i++) {
            JMenuItem vaihtoehto = new JMenuItem(vaikeusasteet[i]);
            vaihtoehto.addActionListener(new ValikonKuuntelija(this, peli));
            vaihdaVaikeusastetta.add(vaihtoehto);
        }
        return vaihdaVaikeusastetta;
    }

    public Miinaharava getPeli() {
        return peli;
    }

    public JLabel getKulunutAika() {
        return kulunutAika;
    }

}
