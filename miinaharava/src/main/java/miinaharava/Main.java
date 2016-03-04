package miinaharava;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import miinaharava.kayttoliittyma.AloitusGui;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
        }

        AloitusGui kl = new AloitusGui();
        SwingUtilities.invokeLater(kl);

    }

}
