package miinaharava.domain;

import java.util.*;
import miinaharava.logiikka.Vaikeusaste;

public class Tuloslista {
    private List<Tulos> tuloslista;
    private Vaikeusaste vaikeusaste;

    public Tuloslista(List<Tulos> tuloslista, Vaikeusaste vaikeusaste) {
        this.tuloslista = tuloslista;
        this.vaikeusaste = vaikeusaste;
    }
    
    
    
}
