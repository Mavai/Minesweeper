package miinaharava.kayttoliittyma;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import miinaharava.domain.Miina;
import miinaharava.domain.Ruutu;

public class Pelialusta {
    private int leveys;
    private int miinojenLkm;
    private Ruutu[][] alusta;
    private List<Miina> miinat;
    private List<Ruutu> tavallisetRuudut;

    public Pelialusta(int leveys, int miinojenLkm) {
        this.leveys = leveys;
        this.miinojenLkm = miinojenLkm;
        this.miinat = new ArrayList<>();
        this.tavallisetRuudut = new ArrayList<>();
        luo();
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
    
    public void luo() {
        luoRuudukko();
        luoMiinat();
        sijoitaMiinat();
        laskeViereisetMiinat();
        laskeTavallisetRuudut();
    }
    
    public void tulosta() {
        for (int i = 0; i < alusta.length; i++) {
            for (int j = 0; j < alusta.length; j++) {
                System.out.print(alusta[j][i] + " ");
            }
            System.out.println("");
        }
    }
    
    public void luoRuudukko() {
        this.alusta = new Ruutu[leveys][leveys];
        for (int i = 0; i < alusta.length; i++) {
            for (int j = 0; j < alusta.length; j++) {
                alusta[j][i] = new Ruutu(j, i);
            }
        }
    }
    
    public void luoMiinat() {
        Random r = new Random();
        for (int i = 0; i < miinojenLkm; i++) {
            Miina uusiMiina = new Miina(r.nextInt(alusta.length), r.nextInt(alusta.length));
            if (miinat.contains(uusiMiina)) {
                i--;
            } else {
                miinat.add(uusiMiina);
            }
        }
    }
    
    public void sijoitaMiinat() {
        for (Miina miina : miinat) {
            Ruutu poistettava = alusta[miina.getX()][miina.getY()];
            alusta[miina.getX()][miina.getY()].asetaMiina();
        }
    }
    
    public void laskeTavallisetRuudut() {
        for (int i = 0; i < alusta.length; i++) {
            for (int j = 0; j < alusta.length; j++) {
                if (!alusta[j][i].sisaltaaMiinan()) {
                    tavallisetRuudut.add(alusta[j][i]);
                }
            }
        }
    }
    
    public void laskeViereisetMiinat() {
        for (int i = 0; i < alusta.length; i++) {
            for (int j = 0; j < alusta.length; j++) {
                alusta[j][i].laskeViereisetMiinat(alusta);
            }
        }
    }
}
