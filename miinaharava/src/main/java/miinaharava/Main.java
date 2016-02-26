package miinaharava;

import java.util.Scanner;
import javax.swing.SwingUtilities;
import miinaharava.domain.Pelialusta;
import miinaharava.kayttoliittyma.AloitusGui;
import miinaharava.kayttoliittyma.Tekstikayttoliittyma;
import miinaharava.logiikka.Miinaharava;
import miinaharava.logiikka.Vaikeusaste;

public class Main {
    
    public static void main(String[] args) {
        AloitusGui kl = new AloitusGui();
        SwingUtilities.invokeLater(kl);
        
    }
    
}
