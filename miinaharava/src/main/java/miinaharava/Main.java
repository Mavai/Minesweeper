package miinaharava;

import javax.swing.SwingUtilities;
import miinaharava.kayttoliittyma.AloitusGui;

public class Main {
    
    public static void main(String[] args) {
        AloitusGui kl = new AloitusGui();
        SwingUtilities.invokeLater(kl);
        
    }
    
}
