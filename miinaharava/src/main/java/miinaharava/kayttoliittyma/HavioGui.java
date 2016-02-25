package miinaharava.kayttoliittyma;

import java.awt.*;
import java.io.UnsupportedEncodingException;
import javax.swing.*;

public class HavioGui extends JDialog {

    private PelikenttaGui isanta;

    public HavioGui(PelikenttaGui isanta) {
        super();

        this.isanta = isanta;

        setPreferredSize(new Dimension(300, 100));

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        luoKomponentit(getContentPane());

        pack();
        setModal(true);
        setLocationRelativeTo(isanta);
        setVisible(true);

    }

    private void luoKomponentit(Container container){
        container.setLayout(new BorderLayout());
        container.add(luoTekstikentta(), BorderLayout.NORTH);
        container.add(luoJatkoVaihtoehdot(), BorderLayout.CENTER);
    }

    private JPanel luoTekstikentta(){
        JPanel paneeli = new JPanel();
        JLabel havioViesti;
        try {
            havioViesti = new JLabel(new String("Osuit miinaan ja hävisit pelin.".getBytes("windows-1252")));
        } catch (Exception e) {
            havioViesti = new JLabel("Osuit miinaan ja hävisit pelin.");
        }
        paneeli.add(havioViesti);
        return paneeli;
    }

    private JPanel luoJatkoVaihtoehdot() {
        JPanel paneeli = new JPanel();
        JButton uusiPeli = new JButton("Uusi peli");
        JButton lopeta = new JButton("Lopeta");
        uusiPeli.addActionListener(new LoppuikkunanKuuntelija(isanta, this));
        lopeta.addActionListener(new LoppuikkunanKuuntelija(isanta, this));
        paneeli.add(uusiPeli);
        paneeli.add(lopeta);
        return paneeli;
    }

}
