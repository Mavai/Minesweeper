package miinaharava;

import java.util.Random;
import java.util.Scanner;
import miinaharava.kayttoliittyma.Pelialusta;
import miinaharava.logiikka.Miinaharava;

public class Main {
    
    public static void main(String[] args) {
        
//        Scanner lukija = new Scanner(System.in);
//        Pelialusta alusta = new Pelialusta(10, 10, 10);
//        
//        Miinaharava peli = new Miinaharava(alusta, lukija);
//        
//        peli.aloita();
        
        Pelialusta alusta = new Pelialusta(3, 3, 1);
        
        String syote = muodostaSyote("1", "1", "999");
        
        Miinaharava peli = new Miinaharava(alusta, new Scanner(syote));
        
        peli.aloita();
        
        System.out.println(alusta.getAlusta()[1][1].onKiinni());
        
    }
    
    public static String muodostaSyote(String... lines) {
        String syote = "";
        for (String line : lines) {
            syote += line + "\n";
        }
        return syote;
    }
    
}
