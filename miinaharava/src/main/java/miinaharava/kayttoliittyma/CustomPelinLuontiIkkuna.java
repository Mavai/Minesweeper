package miinaharava.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*;
import miinaharava.kayttoliittyma.kuuntelijat.CustomPelinAloittaja;
import miinaharava.kayttoliittyma.kuuntelijat.CustomSliderinKuuntelija;

public class CustomPelinLuontiIkkuna extends JFrame {

    private JSlider korkeus;
    private JSlider leveys;
    private JSlider miinat;

    /**
     * Luo ikkunan jossa pelaaja luo itsellen customoidun pelin.
     */
    public CustomPelinLuontiIkkuna() {
        super("Miinaharava");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.korkeus = new JSlider();
        this.leveys = new JSlider();
        this.miinat = new JSlider();

        setPreferredSize(new Dimension(300, 220));

        luoKomponentit(getContentPane());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(luoKorkeudenValitsin());
        container.add(luoLeveydenValitsin());
        container.add(luoMiinojenValitsin());
        container.add(luoALoitusNappi());
    }

    private JPanel luoKorkeudenValitsin() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));
        korkeus = new JSlider(JSlider.HORIZONTAL, 5, (Toolkit.getDefaultToolkit().getScreenSize().height - 150) / 30, 8);
        JLabel teksti = new JLabel("Korkeus: " + korkeus.getValue());
        teksti.setFont(new Font("Normal", Font.BOLD, 16));
        muokkaaSlideria(korkeus, teksti, 5);
        paneeli.add(korkeus);
        paneeli.add(teksti);
        return paneeli;
    }

    private JPanel luoLeveydenValitsin() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));
        leveys = new JSlider(JSlider.HORIZONTAL, 5, (Toolkit.getDefaultToolkit().getScreenSize().width - 90) / 30, 8);
        JLabel teksti = new JLabel("Leveys: " + leveys.getValue());
        teksti.setFont(new Font("Normal", Font.BOLD, 16));
        muokkaaSlideria(leveys, teksti, 5);
        paneeli.add(leveys);
        paneeli.add(teksti);
        return paneeli;
    }

    private JPanel luoMiinojenValitsin() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));
        miinat = new JSlider(JSlider.HORIZONTAL, 0, korkeus.getValue() * leveys.getValue(), 8);
        JLabel teksti = new JLabel("Miinat: " + miinat.getValue());
        teksti.setFont(new Font("Normal", Font.BOLD, 16));
        muokkaaSlideria(miinat, teksti, miinat.getMaximum() / 20);
        paneeli.add(miinat);
        paneeli.add(teksti);
        return paneeli;
    }

    private void muokkaaSlideria(JSlider slideri, JLabel teksti, int merkkienVali) {
        slideri.setMajorTickSpacing(merkkienVali);
        slideri.setPaintTicks(true);
        slideri.addChangeListener(new CustomSliderinKuuntelija(teksti, this));
    }

    /**
     * Päivittaa miina sliderin maximia korkeuden ja leveyden perusteella.
     */
    public void paivitaMiinat() {
        miinat.setMaximum(korkeus.getValue() * leveys.getValue());
        miinat.setMajorTickSpacing(miinat.getMaximum() / 20);
    }

    private JPanel luoALoitusNappi() {
        JPanel paneeli = new JPanel();
        JButton aloita = new JButton("Aloita");
        aloita.addActionListener(new CustomPelinAloittaja(leveys, korkeus, miinat, this));
        paneeli.add(aloita);
        return paneeli;
    }

}
