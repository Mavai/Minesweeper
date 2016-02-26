package miinaharava.kayttoliittyma;

import miinaharava.domain.Pelialusta;
import java.util.Scanner;
import miinaharava.domain.Ruutu;
import miinaharava.logiikka.Miinaharava;

/**
 * Tarjoaa tekstuaalisen käyttöliittymän miinaharava-peliin.
 *
 * @author markovai
 */
public class Tekstikayttoliittyma {

    private Pelialusta pelialusta;
    private Scanner lukija;
    private Miinaharava peli;

    /**
     * Luo tekstikäyttöliittymän miinaharava-pelille.
     *
     * @param pelialusta Käytössä oleva pelialusta.
     * @param lukija Scanner jonka avulla käyttäjä antaa käskyjä.
     * @param peli Käynnissä oleva peli.
     */
    public Tekstikayttoliittyma(Pelialusta pelialusta, Scanner lukija, Miinaharava peli) {
        this.pelialusta = pelialusta;
        this.lukija = lukija;
        this.peli = peli;
    }
    
    /**
     * Aloittaa pelin.
     */
    public void aloita() {
        tervehdi();
        while (true) {
            if (!tulostaKierros()) {
                break;
            }
        }
    }

    /**
     * Tulostaa tervehdystekstin ja ohjeita.
     */
    public void tervehdi() {
        System.out.println("Tervetuloa 'Miinaharava' -peliin.");
        System.out.println("Pelataksesi anna koordinaatteja X ja Y arvoilla " + 0 + " - " + (pelialusta.getAlusta().length - 1) + ".");
        System.out.println("Lopettaaksesi syötä 999 kierroksen alussa.");
    }

    /**
     * Kysyy käyttäjältä avattavan miinan koordinaatit ja palauttaa ne listana.
     *
     * @return Palauttaa listana avattavan miinan koordinaatit.
     */
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

    /**
     * Palauttaa avattavan ruudun Ruutu-olion.
     *
     * @return Palauttaa avattavan ruudun Ruutu-olion.
     */
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

    /**
     * Tulostaa yhden kierroksen pelistä.
     *
     * @return Palauttaa false, jos osutaan miinaan tai saadaan kaikki ruudut
     * avattua ja peli loppuu.
     */
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

    /**
     * Tulostaa pelialueen ja kierrosten lukumäärän.
     */
    public void tulostaAlue() {
        pelialusta.tulosta();
        System.out.println("kierros: ");
    }

    /**
     * Tulostaa häviö-viestin ja palauttaa false.
     *
     * @return false
     */
    public boolean havio() {
        pelialusta.tulosta();
        System.out.println("Osuit miinaan ja hävisit :( \nkierroksia: ");
        return false;
    }

    /**
     * Tulostaa voitto-viestin ja palauttaa false.
     *
     * @return false
     */
    public boolean voitto() {
        pelialusta.tulosta();
        System.out.println("Onneksi olkoon! Löysit kaikki miinat! \nkierroksia: ");
        return false;
    }
}
