package miinaharava.domain;

import java.util.ArrayList;
import java.util.List;

public class Ruutu {

    private int x;
    private int y;
    private int viereisetMiinat;
    private boolean sisaltaaMiinan;
    private boolean kiinni;

    public Ruutu(int x, int y) {
        this.x = x;
        this.y = y;
        this.sisaltaaMiinan = false;
        this.viereisetMiinat = 0;
        this.kiinni = true;
    }

    public void avaa() {
        this.kiinni = false;
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

    public void asetaMiina() {
        this.sisaltaaMiinan = true;
    }

    @Override
    public String toString() {
        if (kiinni) {
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

    public ArrayList<Ruutu> viereisetRuudutKulmassa(ArrayList<Ruutu> viereiset, Ruutu[][] alusta, int x, int y) {
        if (x == 0 && y == 0) {
            viereiset.add(alusta[x + 1][y]);
            viereiset.add(alusta[x + 1][y + 1]);
            viereiset.add(alusta[x][y + 1]);
        } else if (x == 0 && y == alusta.length - 1) {
            viereiset.add(alusta[x + 1][y]);
            viereiset.add(alusta[x + 1][y - 1]);
            viereiset.add(alusta[x][y - 1]);
        } else if (x == alusta.length - 1 && y == 0) {
            viereiset.add(alusta[x - 1][y]);
            viereiset.add(alusta[x - 1][y + 1]);
            viereiset.add(alusta[x][y + 1]);
        } else {
            viereiset.add(alusta[x - 1][y]);
            viereiset.add(alusta[x - 1][y - 1]);
            viereiset.add(alusta[x][y - 1]);
        }
        return viereiset;
    }

    public ArrayList<Ruutu> viereisetRuudutSivuilla(ArrayList<Ruutu> viereiset, Ruutu[][] alusta, int x, int y) {
        if (x == 0) {
            viereiset.add(alusta[x][y - 1]);
            viereiset.add(alusta[x + 1][y - 1]);
            viereiset.add(alusta[x + 1][y]);
            viereiset.add(alusta[x + 1][y + 1]);
            viereiset.add(alusta[x][y + 1]);
        } else if (x == alusta.length - 1) {
            viereiset.add(alusta[x][y - 1]);
            viereiset.add(alusta[x - 1][y - 1]);
            viereiset.add(alusta[x - 1][y]);
            viereiset.add(alusta[x - 1][y + 1]);
            viereiset.add(alusta[x][y + 1]);
        } else if (y == 0) {
            viereiset.add(alusta[x + 1][y]);
            viereiset.add(alusta[x - 1][y]);
            viereiset.add(alusta[x + 1][y + 1]);
            viereiset.add(alusta[x - 1][y + 1]);
            viereiset.add(alusta[x][y + 1]);
        } else {
            viereiset.add(alusta[x + 1][y]);
            viereiset.add(alusta[x + 1][y - 1]);
            viereiset.add(alusta[x][y - 1]);
            viereiset.add(alusta[x - 1][y - 1]);
            viereiset.add(alusta[x - 1][y]);
        }
        return viereiset;
    }

    @Override
    public boolean equals(Object obj) {
        Ruutu verrattava = (Ruutu) obj;
        
        return (verrattava.x == this.x && verrattava.y == this.y);
    }
    
}
