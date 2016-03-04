package miinaharava.kayttoliittyma;

import java.awt.*;
import javax.swing.*;
import miinaharava.kayttoliittyma.kuuntelijat.LoppuikkunanKuuntelija;

public class PelinPaatosGui extends JDialog {

    private final PelikenttaGui isanta;
    private final String havioVaiVoitto;
    private int sijoitus;

    /**
     * Pelin päätyttyä esiin ilmestyvä ikkuna joka kertoo pelin tuloksen.
     *
     * @param isanta Peli-ikkuna joka kutsuu päätösikkunaa.
     * @param havioVaiVoitto Tieto siitä loppuiko peli voittoon vai havioon.
     * @param sijoitus Kuinka monenneksi paras saatu tulos oli.
     */
    public PelinPaatosGui(PelikenttaGui isanta, String havioVaiVoitto, int sijoitus) {
        super();

        this.isanta = isanta;
        this.havioVaiVoitto = havioVaiVoitto;
        this.sijoitus = sijoitus;

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        luoKomponentit(getContentPane());

        pack();
        setModal(true);
        setLocationRelativeTo(isanta);
        setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new BorderLayout());
        container.add(luoTekstikentta(), BorderLayout.NORTH);
        container.add(luoJatkoVaihtoehdot(), BorderLayout.CENTER);
    }

    private JPanel luoTekstikentta() {
        JPanel paneeli = new JPanel();
        JLabel havioViesti;
        havioViesti = new JLabel("");
        if (havioVaiVoitto.equals("havio")) {
            havioViesti.setText("Osuit miinaan ja hävisit pelin.");
        } else {
            if (sijoitus == 0 || sijoitus > 15) {
                havioViesti.setText("<html>Onneksi olkoon! Voitit pelin ajassa  <b>" + isanta.getKulunutAika().getText() + "</b></html>");
            } else {
                havioViesti.setText("<html>Onneksi olkoon! Voitit pelin ajassa  <b>" + isanta.getKulunutAika().getText()
                        + "</b><br>Se oli <b>" + sijoitus + "</b>. nopein aika tällä vaikeusasteella</html>");
            }
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
