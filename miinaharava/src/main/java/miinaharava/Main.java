package miinaharava;

import javax.swing.SwingUtilities;
import miinaharava.kayttoliittyma.Gui;

public class Main {
    
    public static void main(String[] args) {
        Gui kl = new Gui();
        SwingUtilities.invokeLater(kl);
        
//        Scanner lukija = new Scanner(System.in);
//        
//        Miinaharava peli = new Miinaharava(alusta, lukija);
//        
//        peli.aloita();
        
    }
    
    public static String muodostaSyote(String... rivit) {
        String syote = "";
        for (String rivi : rivit) {
            syote += rivi + "\n";
        }
        return syote;
    }
    
}
