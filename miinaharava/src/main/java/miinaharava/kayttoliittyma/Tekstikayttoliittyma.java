package miinaharava.kayttoliittyma;

import java.util.Scanner;
import miinaharava.domain.Ruutu;
import miinaharava.logiikka.Miinaharava;

public class Tekstikayttoliittyma {
    private Pelialusta pelialusta;
    private Scanner lukija;
    private Miinaharava peli;

    public Tekstikayttoliittyma(Pelialusta pelialusta, Scanner lukija, Miinaharava peli) {
        this.pelialusta = pelialusta;
        this.lukija = lukija;
        this.peli = peli;
    }
    
    public void tervehdi() {
        System.out.println("Tervetuloa 'Miinaharava' -peliin.");
        System.out.println("Pelataksesi anna koordinaatteja X ja Y arvoilla " + 0 + " - " + (pelialusta.getAlusta().length - 1) + ".");
        System.out.println("Lopettaaksesi syötä 999 kierroksen alussa.");
    }
    
    public int[] kysyKoordinaatit() {
        int[] koordinaatit = new int[2];
        while (true) {
            System.out.print("Anna X koordinaatti: ");
            koordinaatit[0] = Integer.parseInt(lukija.nextLine());
            if (koordinaatit[0] == 999) {
                break;
            }
            System.out.print("Anna Y koordinaatti: ");
            koordinaatit[1] = Integer.parseInt(lukija.nextLine());
            return koordinaatit;
        }
        return koordinaatit;
    }
    
    public Ruutu avattava() {
        int[] koordinaatit = kysyKoordinaatit();
        Ruutu avattava;
        try {
            avattava = pelialusta.getAlusta()[koordinaatit[0]][koordinaatit[1]];
        } catch (Exception e) {
            avattava = null;
        }
        return avattava;
        
    }
    
    public boolean tulostaKierros() {
        tulostaAlue();
        Ruutu avattava = avattava();
        if (avattava == null) {
            return false;
        }
        if (!peli.avaaRuutu(avattava)) {
            return havio();
        }
        if (peli.kaikkiAvattu()) {
            return voitto();
        }
        return true;
    }
    
    public void tulostaAlue() {
        pelialusta.tulosta();
        System.out.println("kierros: " + peli.getKierroksia());
    }
    
    public boolean havio() {
        pelialusta.tulosta();
        System.out.println("Osuit miinaan ja hävisit :( \nkierroksia: " + peli.getKierroksia());
        return false;
    }
    
    public boolean voitto() {
        pelialusta.tulosta();
        System.out.println("Onneksi olkoon! Löysit kaikki miinat! \nkierroksia: " + peli.getKierroksia());
        return false;
    }
}
