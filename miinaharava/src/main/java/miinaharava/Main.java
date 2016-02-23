package miinaharava;

import javax.swing.SwingUtilities;
import miinaharava.kayttoliittyma.MiinaharavaGui;
import miinaharava.domain.Pelialusta;
<<<<<<< HEAD
import miinaharava.kayttoliittyma.PelinKaynnistaja;
=======
import miinaharava.kayttoliittyma.Gui;
>>>>>>> 745da9b1c46466860872c5292f89c9849ceced86
import miinaharava.logiikka.Miinaharava;

public class Main {
    
    public static void main(String[] args) {
        Pelialusta alusta = new Pelialusta(8, 10);
        Miinaharava peli = new Miinaharava(alusta, null);
        MiinaharavaGui gui = new MiinaharavaGui(peli);
<<<<<<< HEAD
        PelinKaynnistaja kaynnistaja = new PelinKaynnistaja(peli);
        SwingUtilities.invokeLater(kaynnistaja);
=======
        Gui kl = new Gui();
        SwingUtilities.invokeLater(kl);
>>>>>>> 745da9b1c46466860872c5292f89c9849ceced86
        
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
