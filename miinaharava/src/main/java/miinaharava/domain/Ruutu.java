package miinaharava.domain;

import java.util.ArrayList;

/**
 * Miinaharava pelialusta koostuu useasta ruudusta. Tarjoaa metodeja ruutujen
 * käsittelyyn.
 *
 * @author markovai
 */
public class Ruutu {

    private int x;
    private int y;
    private int viereisetMiinat;
    private boolean sisaltaaMiinan;
    private boolean kiinni;
    private boolean merkattu;

    public Ruutu(int x, int y) {
        this.x = x;
        this.y = y;
        this.sisaltaaMiinan = false;
        this.viereisetMiinat = 0;
        this.kiinni = true;
        this.merkattu = false;
    }

    /**
     * Asettaa ruudun aukinaiseksi.
     */
    public void avaa() {
        this.kiinni = false;
    }
    
    public void merkitse() {
        merkattu = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean onKiinni() {
        return this.kiinni;
    }

    public boolean sisaltaaMiinan() {
        return this.sisaltaaMiinan;
    }

    /**
     * Asettaa ruutuun miinan.
     */
    public void asetaMiina() {
        this.sisaltaaMiinan = true;
    }

    @Override
    public String toString() {
        if (kiinni) {
            if (merkattu) {
                return "F";
            }
            return "@";
        } else {
            if (sisaltaaMiinan) {
                return "*";
            }
            return viereisetMiinat + "";
        }
    }

    public void setViereisetMiinat(int x) {
        this.viereisetMiinat = x;
    }

    public int getViereisetMiinat() {
        return viereisetMiinat;
    }

    /**
     * Laskeen tämä ruudun viereisten ruutujen sisältävien miinojen määrän.
     *
     * @param Ruudun sisältävä pelialusta.
     */
    public void laskeViereisetMiinat(Ruutu[][] alusta) {
        for (int i = 0; i < alusta.length; i++) {
            for (int j = 0; j < alusta.length; j++) {
                int viereiset = 0;
                for (Ruutu viereinenRuutu : viereisetRuudut(alusta, j, i)) {
                    if (viereinenRuutu.sisaltaaMiinan) {
                        viereiset++;
                    }
                }
                alusta[j][i].setViereisetMiinat(viereiset);
            }
        }
    }

    /**
     * Palauttaa listan annetun ruudun viereisistä ruuduista.
     *
     * @param alusta Ruudun sisältävä pelialusta.
     * @param x Ruudun x-koordinaatti.
     * @param y Ruudun y-koordinaatti.
     * @return
     */
    public ArrayList<Ruutu> viereisetRuudut(Ruutu[][] alusta, int x, int y) {
        ArrayList<Ruutu> viereiset = new ArrayList<>();
        if ((x == 0 && y == 0) || (x == 0 && y == alusta.length - 1) || (x == alusta.length - 1 && y == 0) || (x == alusta.length - 1 && y == alusta.length - 1)) {
            return viereisetRuudutKulmassa(viereiset, alusta, x, y);

        } else if ((x == 0) || (x == alusta.length - 1) || (y == 0) || (y == alusta.length - 1)) {
            return viereisetRuudutSivuilla(viereiset, alusta, x, y);

        } else {
            return viereisetRuudutKeskella(viereiset, alusta, x, y);
        }
    }

    /**
     * Palauttaa listan keskellä olevan ruudun viereisistä ruuduista.
     *
     * @param viereiset Lista ruuduista.
     * @param alusta Ruudun sisältävä pelialusta.
     * @param x Ruudun x-koordinaatti.
     * @param y Ruudun y-koordinaatti
     * @return
     */
    public ArrayList<Ruutu> viereisetRuudutKeskella(ArrayList<Ruutu> viereiset, Ruutu[][] alusta, int x, int y) {
        viereiset.add(alusta[x][y - 1]);
        viereiset.add(alusta[x][y + 1]);
        viereiset.add(alusta[x - 1][y - 1]);
        viereiset.add(alusta[x - 1][y + 1]);
        viereiset.add(alusta[x - 1][y]);
        viereiset.add(alusta[x + 1][y]);
        viereiset.add(alusta[x + 1][y - 1]);
        viereiset.add(alusta[x + 1][y + 1]);
        return viereiset;
    }

    /**
     * Palauttaa listan kulmassa olevan ruudun viereisistä ruuduista.
     *
     * @param viereiset Lista ruuduista.
     * @param alusta Ruudun sisältävä pelialusta.
     * @param x Ruudun x-koordinaatti.
     * @param y Ruudun y-koordinaatti
     * @return
     */
    public ArrayList<Ruutu> viereisetRuudutKulmassa(ArrayList<Ruutu> viereiset, Ruutu[][] alusta, int x, int y) {
        if (x == 0 && y == 0) {
            return vasenYlaNurkka(viereiset, alusta, x, y);
        } else if (x == 0 && y == alusta.length - 1) {
            return vasenAlaNurkka(viereiset, alusta, x, y);
        } else if (x == alusta.length - 1 && y == 0) {
            return oikeaYlaNurkka(viereiset, alusta, x, y);
        } else {
            return oikeaAlaNurkka(viereiset, alusta, x, y);
        }
    }

    /**
     * Palauttaa listan jollakin pelialusta sivuilla olevan ruudun viereisistä
     * ruuduista.
     *
     * @param viereiset Lista ruuduista.
     * @param alusta Ruudun sisältävä pelialusta.
     * @param x Ruudun x-koordinaatti.
     * @param y Ruudun y-koordinaatti
     * @return
     */
    public ArrayList<Ruutu> viereisetRuudutSivuilla(ArrayList<Ruutu> viereiset, Ruutu[][] alusta, int x, int y) {
        if (x == 0) {
            return vasenSivu(viereiset, alusta, x, y);
        } else if (x == alusta.length - 1) {
            return oikeaSivu(viereiset, alusta, x, y);
        } else if (y == 0) {
            return ylaSivu(viereiset, alusta, x, y);
        } else {
            return alaSivu(viereiset, alusta, x, y);
        }
    }

    public ArrayList<Ruutu> vasenSivu(ArrayList<Ruutu> viereiset, Ruutu[][] alusta, int x, int y) {
        viereiset.add(alusta[x][y - 1]);
        viereiset.add(alusta[x + 1][y - 1]);
        viereiset.add(alusta[x + 1][y]);
        viereiset.add(alusta[x + 1][y + 1]);
        viereiset.add(alusta[x][y + 1]);
        return viereiset;
    }

    public ArrayList<Ruutu> oikeaSivu(ArrayList<Ruutu> viereiset, Ruutu[][] alusta, int x, int y) {
        viereiset.add(alusta[x][y - 1]);
        viereiset.add(alusta[x - 1][y - 1]);
        viereiset.add(alusta[x - 1][y]);
        viereiset.add(alusta[x - 1][y + 1]);
        viereiset.add(alusta[x][y + 1]);
        return viereiset;
    }

    public ArrayList<Ruutu> ylaSivu(ArrayList<Ruutu> viereiset, Ruutu[][] alusta, int x, int y) {
        viereiset.add(alusta[x + 1][y]);
        viereiset.add(alusta[x - 1][y]);
        viereiset.add(alusta[x + 1][y + 1]);
        viereiset.add(alusta[x - 1][y + 1]);
        viereiset.add(alusta[x][y + 1]);
        return viereiset;
    }

    public ArrayList<Ruutu> alaSivu(ArrayList<Ruutu> viereiset, Ruutu[][] alusta, int x, int y) {
        viereiset.add(alusta[x + 1][y]);
        viereiset.add(alusta[x + 1][y - 1]);
        viereiset.add(alusta[x][y - 1]);
        viereiset.add(alusta[x - 1][y - 1]);
        viereiset.add(alusta[x - 1][y]);
        return viereiset;
    }

    public ArrayList<Ruutu> vasenYlaNurkka(ArrayList<Ruutu> viereiset, Ruutu[][] alusta, int x, int y) {
        viereiset.add(alusta[x + 1][y]);
        viereiset.add(alusta[x + 1][y + 1]);
        viereiset.add(alusta[x][y + 1]);
        return viereiset;
    }

    public ArrayList<Ruutu> oikeaYlaNurkka(ArrayList<Ruutu> viereiset, Ruutu[][] alusta, int x, int y) {
        viereiset.add(alusta[x - 1][y]);
        viereiset.add(alusta[x - 1][y + 1]);
        viereiset.add(alusta[x][y + 1]);
        return viereiset;
    }

    public ArrayList<Ruutu> vasenAlaNurkka(ArrayList<Ruutu> viereiset, Ruutu[][] alusta, int x, int y) {
        viereiset.add(alusta[x + 1][y]);
        viereiset.add(alusta[x + 1][y - 1]);
        viereiset.add(alusta[x][y - 1]);
        return viereiset;
    }

    public ArrayList<Ruutu> oikeaAlaNurkka(ArrayList<Ruutu> viereiset, Ruutu[][] alusta, int x, int y) {
        viereiset.add(alusta[x - 1][y]);
        viereiset.add(alusta[x - 1][y - 1]);
        viereiset.add(alusta[x][y - 1]);
        return viereiset;
    }

    @Override
    public boolean equals(Object obj) {
        Ruutu verrattava = (Ruutu) obj;
        return (verrattava.x == this.x && verrattava.y == this.y);
    }

}
