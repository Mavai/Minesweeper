#Rakennekuvaus  

Miinaharava -pelissä on pelialustalla vaikeusasteesta riippuen 64 - 480 ruutua.
Jokainen ruutu sisältää tiedon sen viereisten miinojen määrästä. Lisäksi pelialustalle on sijoitettu vaikeusasteesta riippuen
10 - 99 miinaa sattumanvaraisiin ruutuihin niin, että ne eivät voi olla päällekkäin. Ruutu voi myös olla merkattu, jolloin sitä ei voi avata.  

Pelin moottorina ja logiikkana toimii Miinaharava-luokka. Miinaharava-olio saa parametrikseen joko pelkän vaikeusasteen tai valmiin pelialustan.
Vaikeusasteen perusteella luodaan uusi pelialusta, jonka yhteydessä sinne sijoitetaan miinat. Ruutujen avaaminen ja merkkaaminen tapahtuu Miinaharava -olion kautta
ja lopputulos välittyy käyttäjälle graafisen käyttöliittymän kautta.