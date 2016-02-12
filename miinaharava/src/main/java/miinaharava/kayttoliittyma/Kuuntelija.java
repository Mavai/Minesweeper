package miinaharava.kayttoliittyma;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import miinaharava.domain.Pelialusta;
import miinaharava.domain.Ruutu;
import miinaharava.logiikka.Miinaharava;

public class Kuuntelija implements ActionListener{

    private int x;
    private int y;
    private Pelialusta alusta;
    private Miinaharava peli = new Miinaharava(alusta, null);
    private JButton nappi;

    public Kuuntelija(int x, int y, Pelialusta alusta, JButton nappi) {
        this.alusta = alusta;
        this.x = x;
        this.y = y;
        this.nappi = nappi;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Ruutu avattava = alusta.getAlusta()[x][y];
        avattava.avaa();
        nappi.setEnabled(false);
        nappi.setText(avattava.toString());
        nappi.setMargin(new Insets(5, 5, 5, 5));
        nappi.setFont(new Font("Arial",Font.BOLD ,15));
        alusta.tulosta();
        System.out.println(x + ", " + y);
    }

}
