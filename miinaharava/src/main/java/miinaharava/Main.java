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
        Pelialusta alusta = new Pelialusta(10, 10, 1);
        Miinaharava peli = new Miinaharava(alusta);
        //Tekstikayttoliittyma tkl = new Tekstikayttoliittyma(alusta, new Scanner(System.in), new Miinaharava(Vaikeusaste.HELPPO));
        //tkl.aloita();
        AloitusGui kl = new AloitusGui();
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
