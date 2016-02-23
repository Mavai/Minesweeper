package miinaharava.kayttoliittyma;

import java.awt.*;
import javax.swing.*;

public class Gui implements Runnable {

    private JDialog dialog;

    public Gui() {
    }

    @Override
    public void run() {
        dialog = new JDialog();
        dialog.setPreferredSize(new Dimension(320, 100));

        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        luoKomponentit(dialog.getContentPane());

        dialog.pack();
        dialog.setVisible(true);
    }

    public void luoKomponentit(Container container) {
        container.setLayout(new BorderLayout());
        container.add(teksti(), BorderLayout.NORTH);
        container.add(luoVaihtoehdot());
        
    }
    
    public JLabel teksti() {
        JLabel teksti = new JLabel("Valitse vaikeusaste");
        teksti.setVerticalAlignment(SwingConstants.CENTER);
        teksti.setHorizontalAlignment(SwingConstants.CENTER);
        teksti.setFont(new Font("normal", 0, 20));
        return teksti;
    }
    
    public JPanel luoVaihtoehdot() {
        JPanel vaihtoehdot = new JPanel(new FlowLayout());
        JButton helppo = new JButton("Helppo");
        JButton haastava = new JButton("Haasaatava");
        JButton vaikea = new JButton("Vaikea");
        helppo.addActionListener(new VaikeusAsteenValitsin(helppo, dialog));
        haastava.addActionListener(new VaikeusAsteenValitsin(haastava, dialog));
        vaikea.addActionListener(new VaikeusAsteenValitsin(vaikea, dialog));
        vaihtoehdot.add(helppo);
        vaihtoehdot.add(haastava);
        vaihtoehdot.add(vaikea);
        return vaihtoehdot;
    }
    
    

}
