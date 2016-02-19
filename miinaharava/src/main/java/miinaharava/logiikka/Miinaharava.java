package miinaharava.logiikka;

import java.util.ArrayList;
import java.util.Scanner;
import miinaharava.domain.Ruutu;
import miinaharava.domain.Pelialusta;
import miinaharava.kayttoliittyma.Tekstikayttoliittyma;

/**
 * Luokka tarjoaa logiikan miinaharava -peliin.
 *
 * @author markovai
 */
public class Miinaharava {

    private Pelialusta pelialusta;
    private Scanner lukija;
    private Tekstikayttoliittyma kayttoliittyma;
    private int kierros;
    private ArrayList<Ruutu> nollanViereiset;

    /**
     * Luo Miinaharava-olion, joka toimii pelin logiikkana.
     *
     * @param pelialusta Pelissä käytettävä pelialusta.
     * @param lukija Scanner
     */
    public Miinaharava(Pelialusta pelialusta, Scanner lukija) {
        this.pelialusta = pelialusta;
        this.lukija = lukija;
        this.kayttoliittyma = new Tekstikayttoliittyma(pelialusta, lukija, this);
        this.kierros = 1;
        this.nollanViereiset = new ArrayList<Ruutu>();
    }

    public Pelialusta getPelialusta() {
        return pelialusta;
    }

    /**
     * Kasvattaa kierrosten määrää yhdellä.
     */
    public void kasvataKierroksia() {
        kierros += 1;
    }

    /**
     * Aloittaa pelin.
     */
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

    /**
     * Avaa ruudun. Jos ruudussa on miina, palauttaa arvon false. Jos ruudun
     * vieressä ei ole miinoja avaa rekursiivisesti viereiset ruudut.
     *
     * @param ruutu Avattava ruutu.
     * @return Palauttaa true, jos ei osuta miinaan.
     */
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

    /**
     * Avaa rekursiivisesti kaikki tyhjän ruudun viereiset ruudut, joissa ei ole
     * miinaa.
     *
     * @param ruutu Avattava ruutu.
     */
    private void avaaNollanViereiset(Ruutu ruutu) {
        for (Ruutu viereinenRuutu : ruutu.viereisetRuudut(pelialusta.getAlusta(), ruutu.getX(), ruutu.getY())) {
            nollanViereiset.add(viereinenRuutu);
            avaaRuutu(viereinenRuutu);

        }
    }

    public ArrayList<Ruutu> getNollanViereiset() {
        return nollanViereiset;
    }

    /**
     * Tarkistaa onko kaikki miinattomat ruudut avattu.
     *
     * @return Palauttaa true jos kaikki miinattomat ruudut on avattu.
     */
    public boolean kaikkiAvattu() {
        for (Ruutu ruutu : pelialusta.getTavallisetRuudut()) {
            if (ruutu.onKiinni()) {
                return false;
            }
        }
        return true;
    }
}
