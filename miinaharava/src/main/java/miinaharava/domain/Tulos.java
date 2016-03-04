package miinaharava.domain;

import java.util.concurrent.TimeUnit;

public class Tulos implements Comparable<Tulos> {

    private long aika;
    private String pelaaja;

    /**
     * Tarjoaa toteutuksen miinaharava pelin tuloksille.
     *
     * @param pelaaja Pelaajan nimimerkki
     * @param aika Peliin kulunut aika
     */
    public Tulos(String pelaaja, long aika) {
        this.aika = aika;
        this.pelaaja = pelaaja;
    }

    public long getAika() {
        return aika;
    }

    public String getPelaaja() {
        return pelaaja;
    }

    @Override
    public String toString() {
        return pelaaja + " - " + TimeUnit.SECONDS.toMinutes(aika) % 60 + ":" + aika % 60;
    }

    @Override
    public int compareTo(Tulos tulos) {
        if (this.aika == tulos.aika) {
            return 0;
        } else if (this.aika < tulos.aika) {
            return -1;
        } else {
            return 1;
        }
    }

}
