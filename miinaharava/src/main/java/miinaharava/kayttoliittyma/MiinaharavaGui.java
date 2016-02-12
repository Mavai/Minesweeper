package miinaharava.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import miinaharava.domain.Pelialusta;

public class MiinaharavaGui implements Runnable {

    private JFrame frame;
    private Pelialusta pelialusta;
    int leveys;

    public MiinaharavaGui(Pelialusta pelialusta) {
        this.pelialusta = pelialusta;
        this.leveys = pelialusta.getLeveys();
    }

    @Override
    public void run() {
        frame = new JFrame("Miinaharava");
        frame.setPreferredSize(new Dimension(400, 400));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        JPanel ruudukko = new JPanel();
        ruudukko.setLayout(new GridLayout(leveys, leveys));
        for (int i = 0; i < pelialusta.getLeveys(); i++) {
            for (int j = 0; j < pelialusta.getLeveys(); j++) {
                JButton nappi = new JButton(j + ", " + i);
                ruudukko.add(nappi);
            }
        }
        container.add(ruudukko);
    }

    public JFrame getFrame() {
        return frame;
    }
}
