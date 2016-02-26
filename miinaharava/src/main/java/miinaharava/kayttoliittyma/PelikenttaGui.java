package miinaharava.kayttoliittyma;

import java.awt.*;
import javax.swing.*;
import miinaharava.domain.Pelialusta;
import miinaharava.logiikka.Miinaharava;

public class PelikenttaGui extends JFrame {

    private Pelialusta pelialusta;
    private int leveys;
    private JButton[][] ruudut;
    private Miinaharava peli;
    private JLabel kulunutAika;
    private JLabel miinojenLkm;
    private Timer kello;

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
        this.miinojenLkm = new JLabel("Miinoja jäljellä  " + peli.getMiinojaJaljella(), JLabel.CENTER);
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
        JPanel kierrokset = luoKierrostenJaMerkkienLaskija();
        kierrokset.setPreferredSize(new Dimension(30 * leveys, 30));
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
        miinojenLkm.setFont(new Font("Normal", Font.BOLD, 12));
        kulunutAika.setFont(new Font("Normal", Font.BOLD, 12));
        miinojenLkm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        kulunutAika.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        kierrokset.add(kulunutAika);
        kierrokset.add(miinojenLkm);
        return kierrokset;
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
                nappi.setPreferredSize(new Dimension(35, 35));
                nappi.setFont(new Font("Normal", Font.BOLD, 14));
                nappi.setMargin(new Insets(5, 5, 5, 5));
                nappi.addMouseListener(new RuudunAvaaja(j, i, peli, nappi, ruudut, miinojenLkm, kello, this));
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
        JMenuItem helppo = new JMenuItem("Helppo");
        JMenuItem haastava = new JMenuItem("Haastava");
        JMenuItem vaikea = new JMenuItem("Vaikea");
        JMenuItem demo = new JMenuItem("Demo");
        helppo.addActionListener(new ValikonKuuntelija(this, peli));
        haastava.addActionListener(new ValikonKuuntelija(this, peli));
        vaikea.addActionListener(new ValikonKuuntelija(this, peli));
        demo.addActionListener(new ValikonKuuntelija(this, peli));
        vaihdaVaikeusastetta.add(helppo);
        vaihdaVaikeusastetta.add(haastava);
        vaihdaVaikeusastetta.add(vaikea);
        vaihdaVaikeusastetta.add(demo);
        return vaihdaVaikeusastetta;
    }

    public Miinaharava getPeli() {
        return peli;
    }

    public JLabel getKulunutAika() {
        return kulunutAika;
    }
    
    
}
