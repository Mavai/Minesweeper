#Rakennekuvaus  

Miinaharava -peliss� on pelialustalla vaikeusasteesta riippuen 64 - 480 ruutua.
Jokainen ruutu sis�lt�� tiedon sen viereisten miinojen m��r�st�. Lis�ksi pelialustalle on sijoitettu vaikeusasteesta riippuen
10 - 99 miinaa sattumanvaraisiin ruutuihin niin, ett� ne eiv�t voi olla p��llekk�in. Ruutu voi my�s olla merkattu, jolloin sit� ei voi avata.  

Pelin moottorina ja logiikkana toimii Miinaharava-luokka. Miinaharava-olio saa parametrikseen joko pelk�n vaikeusasteen tai valmiin pelialustan.
Vaikeusasteen perusteella luodaan uusi pelialusta, jonka yhteydess� sinne sijoitetaan miinat. Ruutujen avaaminen ja merkkaaminen tapahtuu Miinaharava -olion kautta
ja lopputulos v�littyy k�ytt�j�lle graafisen k�ytt�liittym�n kautta.

Ohjelman mukana tulee kolme tekstitiedostoa, joissa yll�pidet��n pelin huipputuloksia. Miinaharava olio luo syntyess��n uuden Tuloslista- olion joka luo listan 
tuloksista vaikeusasteen perusteella valitusta tiedostosta ja sit� p�ivitet��n pelisession aikana, jos pelaaja voittaa pelin. Tuloslista olio my�s tallentaa listaan 
tapahtuvat muutokset tiedostoon.