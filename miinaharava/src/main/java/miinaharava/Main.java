package miinaharava;

import javax.swing.SwingUtilities;
import miinaharava.kayttoliittyma.MiinaharavaGui;
import miinaharava.domain.Pelialusta;
import miinaharava.kayttoliittyma.Gui;
import miinaharava.logiikka.Miinaharava;

public class Main {
    
    public static void main(String[] args) {
        Pelialusta alusta = new Pelialusta(8, 10);
        Miinaharava peli = new Miinaharava(alusta, null);
        MiinaharavaGui gui = new MiinaharavaGui(peli);
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
