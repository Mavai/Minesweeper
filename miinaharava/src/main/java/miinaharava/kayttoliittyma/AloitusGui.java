package miinaharava.kayttoliittyma.komponentit;

import java.awt.*;
import javax.swing.*;
import miinaharava.kayttoliittyma.VaikeusAsteenValitsin;

public class AloitusGui implements Runnable {

    private JDialog dialog;
    private ButtonGroup vaihtoehdot;

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

    public void luoKomponentit(Container container) {
        container.setLayout(new BorderLayout());
        container.add(teksti(), BorderLayout.NORTH);
        container.add(luoVaihtoehdot(), BorderLayout.CENTER);
        container.add(luoAloitusNappi(), BorderLayout.SOUTH);
        
    }

    private JPanel luoAloitusNappi() {
        JPanel paneeli = new JPanel();
        JButton aloita = new JButton("Aloita");
        aloita.setSize(new Dimension(100, 500));
        aloita.addActionListener(new VaikeusAsteenValitsin(dialog, vaihtoehdot));
        paneeli.add(aloita);
        return paneeli;
    }
    
    public JLabel teksti() {
        JLabel teksti = new JLabel("Valitse vaikeusaste");
        teksti.setVerticalAlignment(SwingConstants.CENTER);
        teksti.setHorizontalAlignment(SwingConstants.CENTER);
        teksti.setFont(new Font("normal", 0, 20));
        return teksti;
    }
    
    public JPanel luoVaihtoehdot() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));
        JRadioButton helppo = new JRadioButton("Helppo", true);
        JRadioButton haastava = new JRadioButton("Haastava");
        JRadioButton vaikea = new JRadioButton("Vaikea");
        JRadioButton demo = new JRadioButton("Demo");
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
        
        paneeli.setPreferredSize(new Dimension(50,50));
        return paneeli;
    }
    
    

}
