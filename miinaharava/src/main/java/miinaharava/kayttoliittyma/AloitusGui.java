package miinaharava.kayttoliittyma;

import java.awt.*;
import javax.swing.*;
import miinaharava.kayttoliittyma.kuuntelijat.VaikeusAsteenValitsin;

public class AloitusGui implements Runnable {

    private JDialog dialog;
    private JComboBox<String> valikko;

    /**
     * Luo aloitusikkunan Miinaharava pelin graafiseen käyttöliittymään.
     */
    public AloitusGui() {
        valikko = new JComboBox<String>();
    }

    @Override
    public void run() {
        dialog = new JDialog();

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
        aloita.addActionListener(new VaikeusAsteenValitsin(dialog, valikko));
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
        valikko = new JComboBox<>();
        String[] vaihtoehdot = {"Helppo (8x8, 10 miinaa)", "Haastava (16x16, 40 miinaa)", "Vaikea (16x30, 99 miinaa)", "Custom"};
        for (int i = 0; i < vaihtoehdot.length; i++) {
            valikko.addItem(vaihtoehdot[i]);
        }
        paneeli.add(valikko);
        return paneeli;
    }

}
