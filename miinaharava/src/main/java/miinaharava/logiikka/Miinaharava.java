package miinaharava.logiikka;

import java.util.Scanner;
import miinaharava.domain.Ruutu;
import miinaharava.kayttoliittyma.Pelialusta;
import miinaharava.kayttoliittyma.Tekstikayttoliittyma;

public class Miinaharava {

    private Pelialusta pelialusta;
    private Scanner lukija;
    private boolean kaynnissa;
    private Tekstikayttoliittyma kayttoliittyma;
    private int kierros;

    public Miinaharava(Pelialusta pelialusta, Scanner lukija) {
        this.pelialusta = pelialusta;
        this.lukija = lukija;
        this.kaynnissa = false;
        this.kayttoliittyma = new Tekstikayttoliittyma(pelialusta, lukija, this);
        this.kierros = 1;
    }

    public void aloita() {
        kayttoliittyma.tervehdi();
        while (true) {
            if (!kayttoliittyma.tulostaKierros()) {
                break;
            }
            kierros++;
        }
    }

    public int getKierroksia() {
        return kierros;
    }

    public boolean avaaRuutu(Ruutu ruutu) {
        if (!ruutu.onKiinni()) {
            return true;
        }
        ruutu.avaa();
        if (ruutu.sisaltaaMiinan()) {
            return false;
        }
        if (ruutu.getViereisetMiinat() == 0) {
            avaaNollanViereiset(ruutu);
        }
        return true;
    }

    private void avaaNollanViereiset(Ruutu ruutu) {
        for (Ruutu viereinenRuutu : ruutu.viereisetRuudut(pelialusta.getAlusta(), ruutu.getX(), ruutu.getY())) {
            if (ruutu.getViereisetMiinat() == 0) {
                avaaRuutu(viereinenRuutu);
            }
        }
    }

    public boolean kaikkiAvattu() {
        for (Ruutu ruutu : pelialusta.getTavallisetRuudut()) {
            if (ruutu.onKiinni()) {
                return false;
            }
        }
        return true;
    }
}
