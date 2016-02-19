package miinaharava;

import javax.swing.SwingUtilities;
import miinaharava.kayttoliittyma.MiinaharavaGui;
import miinaharava.domain.Pelialusta;

public class Main {
    
    public static void main(String[] args) {
        Pelialusta alusta = new Pelialusta(10, 50);
        MiinaharavaGui gui = new MiinaharavaGui(alusta);
        SwingUtilities.invokeLater(gui);
        
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
