package miinaharava.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import miinaharava.domain.Miina;
import miinaharava.domain.Ruutu;

/**
 * Tarjoaa metodeja miinaharava-pelin pelialustan luomiseen ja läpikäymiseen.
 *
 * @author markovai
 */
public class Pelialusta {
    private int korkeus;
    private int leveys;
    private int miinojenLkm;
    private Ruutu[][] alusta;
    private List<Miina> miinat;
    private List<Ruutu> tavallisetRuudut;

    /**
     * Luo pelalustan, jonka leveys ja miinojen lukumäärä määritellään
     * parametreinä.
     *
     * @param leveys Pelialustan leveys
     * @param miinojenLkm Miinojen lukumäärä pelialustalla
     */
    public Pelialusta(int leveys, int korkeus, int miinojenLkm) {
        this.korkeus = korkeus;
        this.leveys = leveys;
        this.miinojenLkm = miinojenLkm;
        this.miinat = new ArrayList<>();
        this.tavallisetRuudut = new ArrayList<>();
        luo();
    }

    public int getKorkeus() {
        return korkeus;
    }

    public int getLeveys() {
        return leveys;
    }

    public Ruutu[][] getAlusta() {
        return alusta;
    }

    public List<Miina> getMiinat() {
        return miinat;
    }

    public List<Ruutu> getTavallisetRuudut() {
        return tavallisetRuudut;
    }

    /**
     * Luo ruudukon ja sijoittaa miinat ruutuihin.
     */
    public void luo() {
        luoRuudukko();
        luoMiinat();
        sijoitaMiinat();
        laskeViereisetMiinat();
        laskeTavallisetRuudut();
    }

    /**
     * Tulostaa taulukon.
     */
    public void tulosta() {
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                System.out.print(alusta[j][i] + " ");
            }
            System.out.println("");
        }
    }

    /**
     * Luo int[][] taulukon.
     */
    public void luoRuudukko() {
        this.alusta = new Ruutu[leveys][korkeus];
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                alusta[j][i] = new Ruutu(j, i);
            }
        }
    }

    /**
     * Luo miinat, joilla on satunnaiset koordinaatit.
     */
    public void luoMiinat() {
        Random r = new Random();
        for (int i = 0; i < miinojenLkm; i++) {
            Miina uusiMiina = new Miina(r.nextInt(leveys), r.nextInt(korkeus));
            if (!miinat.contains(uusiMiina)) {
                miinat.add(uusiMiina);
            } else {
                i--;
            }
        }
    }

    /**
     * Sijoittaa luodut miinat pelialustalle.
     */
    public void sijoitaMiinat() {
        for (Miina miina : miinat) {
            Ruutu poistettava = alusta[miina.getX()][miina.getY()];
            alusta[miina.getX()][miina.getY()].asetaMiina();
        }
    }

    /**
     * Luo lista ruuduista, joissa ei ole miinaa.
     */
    public void laskeTavallisetRuudut() {
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                if (!alusta[j][i].sisaltaaMiinan()) {
                    tavallisetRuudut.add(alusta[j][i]);
                }
            }
        }
    }

    /**
     * Laskee ruudun viereiset miinat.
     */
    public void laskeViereisetMiinat() {
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                alusta[j][i].laskeViereisetMiinat(this);
            }
        }
    }
}
