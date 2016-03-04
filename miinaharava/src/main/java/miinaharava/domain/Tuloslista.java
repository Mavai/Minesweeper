package miinaharava.domain;

import java.io.File;
import java.io.FileWriter;
import java.util.*;
import miinaharava.logiikka.Vaikeusaste;

public class Tuloslista {

    private List<Tulos> tuloslista;
    private Tulostenlukija lukija;
    private Vaikeusaste vaikeusaste;

    /**
     * Tarjoaa listatoteutuksen tekstitiedostosta haettaville tuloksille ja
     * niiden tallentamiseen.
     *
     * @param vaikeusaste Vaikeusaste jonka tuloksia käsitellään.
     */
    public Tuloslista(Vaikeusaste vaikeusaste) {
        this.vaikeusaste = vaikeusaste;
        if (vaikeusaste == Vaikeusaste.HELPPO) {
            lukija = new Tulostenlukija(new File("files/Helppo.txt"));
            tuloslista = lukija.lueTulokset();
        }
        if (vaikeusaste == Vaikeusaste.HAASTAVA) {
            lukija = new Tulostenlukija(new File("files/Haastava.txt"));
            tuloslista = lukija.lueTulokset();
        }
        if (vaikeusaste == Vaikeusaste.VAIKEA) {
            lukija = new Tulostenlukija(new File("files/Vaikea.txt"));
            tuloslista = lukija.lueTulokset();
        }
    }

    /**
     * Luo Tulostenlukijan ja hakee tiedostossa sijaitsevat tulokset.
     *
     * @param tiedosto Tiedosto josta tulokset haetaan.
     */
    public Tuloslista(File tiedosto) {
        lukija = new Tulostenlukija(tiedosto);
        tuloslista = lukija.lueTulokset();
    }

    public List<Tulos> getTuloslista() {
        return tuloslista;
    }

    /**
     * Lisää tuloksetn listalle ja jos listan koko ylittyy poistaa sieltä
     * huonoimman tuloksen.
     *
     * @param tulos Lisättävä tulos
     */
    public void lisaaTulos(Tulos tulos) {
        tuloslista.add(tulos);
        Collections.sort(tuloslista);
        if (tuloslista.size() > 15) {
            tuloslista.remove(15);
        }
    }

    /**
     * Tulostaa listan.
     *
     * @return Palauttaa String muuttujan.
     */
    public String tulostaLista() {
        String tulokset = "";
        if (!tuloslista.isEmpty()) {
            for (Tulos tulos : tuloslista) {
                tulokset += tulos.toString() + "\n";
            }
        }
        return tulokset;
    }

    /**
     * Palauttaa tuloksen indeksin + 1, eli tuloksen sijoitukesn.
     *
     * @param tulos Haluttu tulos
     * @return Palauttaa sijoituksen int-muodossa
     */
    public int sijoitus(Tulos tulos) {
        return tuloslista.indexOf(tulos) + 1;
    }

    /**
     * Tallentaa tuloslistaan tehdyt muutokset tiedostoon.
     *
     * @return Palauttaa arvon true jos tallennus onnistui.
     */
    public boolean tallenna() {
        FileWriter kirjoittaja = null;
        try {
            if (vaikeusaste == Vaikeusaste.HELPPO) {
                kirjoittaja = new FileWriter(new File("files/Helppo.txt"));
            } else if (vaikeusaste == Vaikeusaste.HAASTAVA) {
                kirjoittaja = new FileWriter(new File("files/Haastava.txt"));
            } else if (vaikeusaste == Vaikeusaste.VAIKEA) {
                kirjoittaja = new FileWriter(new File("files/Vaikea.txt"));
            }
            for (Tulos tulos : tuloslista) {
                kirjoittaja.write(tulos.toString());
                kirjoittaja.write(System.getProperty("line.separator"));
            }
            kirjoittaja.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Vaikeusaste getVaikeusaste() {
        return vaikeusaste;
    }

}
