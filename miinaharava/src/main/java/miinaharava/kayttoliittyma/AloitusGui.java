package miinaharava.kayttoliittyma;

import java.awt.*;
import javax.swing.*;
import miinaharava.kayttoliittyma.kuuntelijat.AloitusGuinKuuntelija;

public class AloitusGui implements Runnable {

    private JDialog dialog;
    private JComboBox<String> valikko;
    private JTextField nimimerkki;
    private JLabel nimimerkkiKentanOtsikko;

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
        
        dialog.setResizable(false);
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
        container.add(luoNimimerkkiKentta(), BorderLayout.SOUTH);
        container.add(luoNapit(), BorderLayout.EAST);
        
    }

    /**
     * Luo napin jolla peli aloitetaan.
     *
     * @return Palauttaa JPanel komponentin.
     */
    private JPanel luoNapit() {
        JPanel paneeli = new JPanel();
        JButton aloita = new JButton("Aloita");
        JButton tuloslista = new JButton("tuloslista");
        aloita.addActionListener(new AloitusGuinKuuntelija(dialog, valikko, nimimerkki, nimimerkkiKentanOtsikko));
        tuloslista.addActionListener(new AloitusGuinKuuntelija(dialog, valikko, nimimerkki, nimimerkkiKentanOtsikko));
        paneeli.add(aloita);
        paneeli.add(tuloslista);
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
    
    public JPanel luoNimimerkkiKentta() {
        JPanel paneeli = new JPanel(new BorderLayout());
        nimimerkkiKentanOtsikko = new JLabel("Anna nimimerkki");
        nimimerkkiKentanOtsikko.setFont(new Font("Normal", Font.PLAIN, 14));
        nimimerkki = new JTextField();
        paneeli.add(nimimerkkiKentanOtsikko, BorderLayout.NORTH);
        paneeli.add(nimimerkki, BorderLayout.CENTER);
        return paneeli;
        
    }

}
