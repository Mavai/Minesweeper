package miinaharava.logiikka;

import java.util.ArrayList;
import miinaharava.domain.Ruutu;
import miinaharava.domain.Pelialusta;

/**
 * Luokka tarjoaa logiikan miinaharava -peliin.
 *
 * @author markovai
 */
public class Miinaharava {

    private Pelialusta pelialusta;
    private ArrayList<Ruutu> nollanViereiset;
    private Vaikeusaste vaikeus;
    private int miinojaJaljella;

    /**
     * Luo Miinaharava-olion, joka toimii pelin logiikkana.
     *
     * @param alusta Peliin liittyvä pelialusta.
     */
    public Miinaharava(Pelialusta alusta) {
        this.nollanViereiset = new ArrayList<Ruutu>();
        this.pelialusta = alusta;
        this.miinojaJaljella = pelialusta.getMiinat().size();
    }

    /**
     * Luo Miinaharava-olion, joka toimii pelin logiikkana.
     *
     * @param vaikeus Vaikeusaste, jonka perusteella luodaan oikea pelialusta.
     */
    public Miinaharava(Vaikeusaste vaikeus) {
        if (vaikeus == Vaikeusaste.HELPPO) {
            this.pelialusta = new Pelialusta(8, 8, 10);
        }
        if (vaikeus == Vaikeusaste.HAASTAVA) {
            this.pelialusta = new Pelialusta(16, 16, 40);
        }
        if (vaikeus == Vaikeusaste.VAIKEA) {
            this.pelialusta = new Pelialusta(30, 16, 99);
        }
        if (vaikeus == Vaikeusaste.DEMO) {
            this.pelialusta = new Pelialusta(8, 8, 3);
        }
        this.miinojaJaljella = pelialusta.getMiinat().size();
        this.nollanViereiset = new ArrayList<Ruutu>();
        this.vaikeus = vaikeus;
    }

    public int getMiinojaJaljella() {
        return miinojaJaljella;
    }

    public Vaikeusaste getVaikeus() {
        return vaikeus;
    }

    public Pelialusta getPelialusta() {
        return pelialusta;
    }

    /**
     * Asettaa ruudun merkatuksi.
     *
     * @param ruutu Merkattava ruutu.
     */
    public void merkkaaRuutu(Ruutu ruutu) {
        ruutu.merkitse();
        miinojaJaljella--;
    }

    /**
     * Asettaa ruudun merkitsemättömäksi.
     *
     * @param ruutu Ruutu josta merkkaus poistetaan.
     */
    public void postaRuudunMerkinta(Ruutu ruutu) {
        ruutu.poistaMerkinta();
        miinojaJaljella++;
    }

    /**
     * Avaa ruudun. Jos ruudussa on miina, palauttaa arvon false. Jos ruudun
     * vieressä ei ole miinoja avaa rekursiivisesti viereiset ruudut.
     *
     * @param ruutu Avattava ruutu.
     * @return Palauttaa true, jos ei osuta miinaan.
     */
    public boolean avaaRuutu(Ruutu ruutu) {
        if (ruutu.onMerkattu()) {
            return true;
        }
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
        for (Ruutu viereinenRuutu : ruutu.viereisetRuudut(pelialusta, ruutu.getX(), ruutu.getY())) {
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
