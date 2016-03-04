package miinaharava.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import miinaharava.domain.Tuloslista;
import miinaharava.logiikka.Vaikeusaste;

public class Tulosnakyma extends JFrame {

    /**
     * Tarjoaa ikkunan jossa näytetään parhaat tulokset.
     */
    public Tulosnakyma() {
        super("Miinaharava");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        luoKomponentit(getContentPane());

        setPreferredSize(new Dimension(420, 400));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.add(luoValilehdet());
    }

    /**
     * Luo välilehdellisen JTabbedPane komponentin ja sijoittaa sinne välilehdet
     * vaikeusasteittain.
     *
     * @return Palauttaa JTabbedPane komponentin.
     */
    public JTabbedPane luoValilehdet() {
        JTabbedPane valilehdet = new JTabbedPane();
        valilehdet.addTab("Helppo", luoHelponTulokset());
        valilehdet.addTab("Haastava", luoHaastavanTulokset());
        valilehdet.addTab("Vaikea", luoVaikeanTulokset());
        return valilehdet;
    }

    /**
     * Luo paneelin helpon vaikeusasteen tuloksille.
     *
     * @return Palauttaa JPanel komponentin.
     */
    public JPanel luoHelponTulokset() {
        JPanel paneeli = new JPanel();
        JTextPane tulosikkuna = new JTextPane();
        muokkaaTuloslistaa(tulosikkuna);
        Tuloslista tuloslista = new Tuloslista(Vaikeusaste.HELPPO);
        tulosikkuna.setText(tuloslista.tulostaLista());
        paneeli.add(tulosikkuna);
        return paneeli;
    }

    /**
     * Luo paneelin haastavan vaikeusasteen tuloksille.
     *
     * @return Palauttaa JPanel komponentin
     */
    public JPanel luoHaastavanTulokset() {
        JPanel paneeli = new JPanel();
        JTextPane tulosikkuna = new JTextPane();
        muokkaaTuloslistaa(tulosikkuna);
        Tuloslista tuloslista = new Tuloslista(Vaikeusaste.HAASTAVA);
        tulosikkuna.setText(tuloslista.tulostaLista());
        paneeli.add(tulosikkuna);
        return paneeli;
    }

    /**
     * Luo paneelin vaikean vaikeusasteen tuloksille.
     *
     * @return Palauttaa JPanel komponentin
     */
    public JPanel luoVaikeanTulokset() {
        JPanel paneeli = new JPanel();
        JTextPane tulosikkuna = new JTextPane();
        muokkaaTuloslistaa(tulosikkuna);
        Tuloslista tuloslista = new Tuloslista(Vaikeusaste.VAIKEA);
        tulosikkuna.setText(tuloslista.tulostaLista());
        paneeli.add(tulosikkuna);
        return paneeli;
    }

    private void muokkaaTuloslistaa(JTextPane tulosikkuna) {
        tulosikkuna.setPreferredSize(new Dimension(400, 380));
        tulosikkuna.setEditable(false);
        tulosikkuna.setFont(new Font("Normal", Font.BOLD, 14));
    }
}
