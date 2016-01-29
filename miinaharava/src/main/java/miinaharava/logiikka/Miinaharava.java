package miinaharava.logiikka;

import java.util.Scanner;
import miinaharava.domain.Ruutu;
import miinaharava.kayttoliittyma.Pelialusta;

public class Miinaharava {

    private Pelialusta pelialusta;
    private Scanner lukija;
    private boolean kaynnissa;

    public Miinaharava(Pelialusta pelialusta, Scanner lukija) {
        this.pelialusta = pelialusta;
        this.lukija = lukija;
        this.kaynnissa = false;
    }

    public void aloita() {
        System.out.println("Tervetuloa 'Miinaharava' -peliin.");
        System.out.println("Pelataksesi anna koordinaatteja X ja Y arvoilla " + 0 + " - " + (pelialusta.getAlusta().length - 1) + ".");
        int[] koordinaatit = new int[2];
        this.kaynnissa = true;
        while (kaynnissa) {
            pelialusta.tulosta();
            koordinaatit = kysyKoordinaatit();
            if (koordinaatit[0] == 999) {
                break;
            }
            Ruutu avattava = pelialusta.getAlusta()[koordinaatit[0]][koordinaatit[1]];
            if (!avaaRuutu(avattava)) {
                break;
            }
        }
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

    public boolean avaaRuutu(Ruutu ruutu) {
        if (!ruutu.onKiinni()) {
            return true;
        }
        ruutu.avaa();
        if (ruutu.getViereisetMiinat() == 0) {
            avaaViereisetNollat(ruutu);
        } else if (ruutu.sisaltaaMiinan()) {
            pelialusta.tulosta();
            System.out.println("HÄVISIT!");
            return false;
        }
        return true;
    }

    private void avaaViereisetNollat(Ruutu ruutu) {
        for (Ruutu viereinenRuutu : ruutu.viereisetRuudut(pelialusta.getAlusta(), ruutu.getX(), ruutu.getY())) {
            if (ruutu.getViereisetMiinat() == 0) {
                avaaRuutu(viereinenRuutu);
            }
        }
    }
}
