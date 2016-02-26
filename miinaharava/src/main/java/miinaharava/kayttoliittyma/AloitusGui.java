package miinaharava.kayttoliittyma;

import java.awt.*;
import javax.swing.*;
import miinaharava.kayttoliittyma.kuuntelijat.VaikeusAsteenValitsin;

public class AloitusGui implements Runnable {

    private JDialog dialog;
    private ButtonGroup vaihtoehdot;

    /**
     * Luo aloitusikkunan Miinaharava pelin graafiseen käyttöliittymään.
     */
    public AloitusGui() {
        vaihtoehdot = new ButtonGroup();
    }

    @Override
    public void run() {
        dialog = new JDialog();
        dialog.setPreferredSize(new Dimension(300, 220));

        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        luoKomponentit(dialog.getContentPane());

        dialog.pack();
        dialog.setLocationRelativeTo(dialog);
        dialog.setVisible(true);
    }

    /**
     * Luo käyttöliittymäkomponentit aloitusnäkymään.
     *
     * @param container Container johon käyttöliittymäkomponentit sijoitetaan.
     */
    public void luoKomponentit(Container container) {
        container.setLayout(new BorderLayout());
        container.add(teksti(), BorderLayout.NORTH);
        container.add(luoVaihtoehdot(), BorderLayout.CENTER);
        container.add(luoAloitusNappi(), BorderLayout.SOUTH);

    }

    /**
     * Luo napin jolla peli aloitetaan.
     *
     * @return Palauttaa JPanel komponentin.
     */
    private JPanel luoAloitusNappi() {
        JPanel paneeli = new JPanel();
        JButton aloita = new JButton("Aloita");
        aloita.setSize(new Dimension(100, 500));
        aloita.addActionListener(new VaikeusAsteenValitsin(dialog, vaihtoehdot));
        paneeli.add(aloita);
        return paneeli;
    }

    /**
     * Luo tekstin aloitusnäkymään.
     *
     * @return Paulauttaa JLabel komponentin.
     */
    public JLabel teksti() {
        JLabel teksti = new JLabel("Valitse vaikeusaste");
        teksti.setVerticalAlignment(SwingConstants.CENTER);
        teksti.setHorizontalAlignment(SwingConstants.CENTER);
        teksti.setFont(new Font("normal", 0, 20));
        return teksti;
    }

    /**
     * Luo JRadioButtonit eri vaikeusvaihtoehdoille.
     *
     * @return Palauttaa JPanel komponentin.
     */
    public JPanel luoVaihtoehdot() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));
        JRadioButton helppo = new JRadioButton("Helppo  (8x8, 10 miinaa)", true);
        JRadioButton haastava = new JRadioButton("Haastava  (16x16, 40 miinaa)");
        JRadioButton vaikea = new JRadioButton("Vaikea  (16x32, 99 miinaa)");
        JRadioButton demo = new JRadioButton("Demo  (8x8, 3 miinaa)");
        helppo.setActionCommand("Helppo");
        haastava.setActionCommand("Haastava");
        vaikea.setActionCommand("Vaikea");
        demo.setActionCommand("Demo");
        paneeli.add(helppo);
        paneeli.add(haastava);
        paneeli.add(vaikea);
        paneeli.add(demo);
        vaihtoehdot.add(helppo);
        vaihtoehdot.add(haastava);
        vaihtoehdot.add(vaikea);
        vaihtoehdot.add(demo);

        paneeli.setPreferredSize(new Dimension(50, 50));
        return paneeli;
    }

}
