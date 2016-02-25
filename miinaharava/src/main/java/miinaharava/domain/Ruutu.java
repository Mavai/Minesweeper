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

    /**
     * Luo ruudun x- ja y-koordinaateilla.
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     */
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

    /**
     * Asettaa ruudun merkityksi.
     */
    public void merkitse() {
        merkattu = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean onMerkattu() {
        return merkattu;
    }

    public void poistaMerkinta() {
        merkattu = false;
    }

    /**
     * Tarkistaa onko ruutu kiinni vai avattu.
     *
     * @return Palauttaa true jos ruutu on kiinni.
     */
    public boolean onKiinni() {
        return this.kiinni;
    }

    /**
     * Tarkistaa sisältääkö ruutu miinan.
     *
     * @return Palauttaa true jos ruutu sisältää miinan.
     */
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
            if (viereisetMiinat == 0) {
                return " ";
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
     * @param alusta Ruudun sisältävä pelialusta.
     */
    public void laskeViereisetMiinat(Pelialusta alusta) {
        for (int i = 0; i < alusta.getKorkeus(); i++) {
            for (int j = 0; j < alusta.getLeveys(); j++) {
                int viereiset = 0;
                for (Ruutu viereinenRuutu : viereisetRuudut(alusta, j, i)) {
                    if (viereinenRuutu.sisaltaaMiinan) {
                        viereiset++;
                    }
                }
                alusta.getAlusta()[j][i].setViereisetMiinat(viereiset);
            }
        }
    }

    /**
     * Palauttaa listan annetun ruudun viereisistä ruuduista.
     *
     * @param alusta Ruudun sisältävä pelialusta.
     * @param x Ruudun x-koordinaatti.
     * @param y Ruudun y-koordinaatti.
     * @return Palauttaa listan ruudun viereisistä ruuduista.
     */
    public ArrayList<Ruutu> viereisetRuudut(Pelialusta alusta, int x, int y) {
        ArrayList<Ruutu> viereiset = new ArrayList<Ruutu>();
        int xMin = x - 1;
        int xMax = x + 1;
        int yMin = y - 1;
        int yMax = y + 1;
        if (x == 0) {
            xMin = 0;
        }
        if (y == 0) {
            yMin = 0;
        }
        if (x == alusta.getLeveys() - 1) {
            xMax = alusta.getLeveys() - 1;
        }
        if (y == alusta.getKorkeus() - 1) {
            yMax = alusta.getKorkeus() - 1;
        }
        lisaaListalle(xMin, xMax, yMin, yMax, viereiset, alusta.getAlusta(), x, y);
        return viereiset;
    }

    private void lisaaListalle(int xMin, int xMax, int yMin, int yMax, ArrayList<Ruutu> viereiset, Ruutu[][] alusta, int x, int y) {
        for (int i = xMin; i <= xMax; i++) {
            for (int j = yMin; j <= yMax; j++) {
                viereiset.add(alusta[i][j]);
            }
        }
        viereiset.remove(alusta[x][y]);
    }

    @Override
    public boolean equals(Object obj) {
        Ruutu verrattava = (Ruutu) obj;
        return (verrattava.x == this.x && verrattava.y == this.y);
    }

}
