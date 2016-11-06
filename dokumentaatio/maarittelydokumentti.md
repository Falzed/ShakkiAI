# Shakkitekoäly

Toteutetaan tekoäly aiemmassa harjoitustössä tehdylle shakkiohjelmalle javalla. 

## Tietorakenteet
* Solmu - solmulla on
  * Lauta - laudalla on kaksiulotteinen taulukko, joissa on Nappula-olioita (mukaan lukien Tyhja-oliot). Lauta on valittu koska olen jo toteuttanut sen aiemmin, ja sen tilavaativuus ei ole mitenkään hirveä.
  * Tieto siitä, voiko ohestalyödä, ja mistä ruudusta jos voi (kokonaislukutaulukko)
  * Tieto siitä, kumman pelaajan vuoro on (enum)
* jonkinlainen lista haun solmun (laudan) lapsisolmuille

Loppupelitietokantaa ei ole, koska viidenkin nappulan loppupeleihin menisi noin seitsemän gigatavua.

## Algoritmit
* Heuristiikka joka arvioi kuinka hyvä mahdollinen tilanne laudalla on - annetaan lauta ja koordinaatit josta voi ohestalyödä (null jos ei voi)
  * luultavasti perustuen enimmäkseen materiaaliin, nappuloitten kehitykseen, kaksoissotilaitten (tai kolmois jne) välttämiseen, onko sotilas avoimella linjalla (ei vastustajan sotilasta), kuinka turvassa kuningas on
* lepotilahaku (? quiescence search)
  * käyttäen alpha-beta -karsintaa
  * tilan hiljaisuuden määritys perustuen siihen kuinka pitkälle liikuttiin, syötiinkö jotain, shakitettiinko jne
Lepotilahaku mahdollistaa tekoälyn tutkivan tarkemmin mielenkiintoisilta vaikuttavia siirtoja.
Alpha-beta karsinta tietenkin on nopeampi normaaliin minimaxiin verrattuna, eikä ole kovin monimutkainen toteuttaa. Alpha-beta valittu NegaScoutin sijaan koska se ei vaadi solmujen järjestämistä oikein, jossa saattaisi helposti tulla bugeja. NegaScout on tyypillisesti noin 10% nopeampi.

## Syötteet
Syötteenä tekoäly saa nykyisen tilanteen laudalla, joko jonkinlainen alkutilanne tai pelaajan siirrosta johtuva. Se sitten arvioi mikä on tässä tilanteessa paras siirto. Tämä toteutetaan lepotilahaulla, jossa ei tutkita syvällisesti huonoilta vaikuttvia siirtoja. Mielenkiintoiset siirrot tutkitaan alpha-beta -karsinnalla.

## Aikavaativuudet
* Heuristiikan pitäisi olla lineaarinen nappuloitten määrän laudalla suhteen.
* Pelkän alpha-beta karsinnan aikavaativuuden pitäisi olla O(b<sup>d</sup>) [2] - O(b<sup>d/2</sup>) [2] jos käydään solmut läpi hyvässä järjestyksessä - missä b on haarautumiskerroin eli solmun lapsisolmujen määrä ja d hakusyvyys. b on laillisten siirtojen määrä, joka on tyypillisesti pelin keskivaiheilla 35. [1] Ihan pelin alussa mahdollisia siirtoja on 18.
* Lepotilahaussa pahimmassa tapauksessa mikään solmu ei ole "hiljainen" vaan tutkitaan syvällisesti, jolloin aikavaativuus on myös O(b<sup>d</sup>).

## Tilavaativuudet
* Laudan vaatima tila on vakio (joka ruudussa aina jotain koska tyhjässäkin ruudussa Tyhja-nappula)
* Tilanneheuristiikan pitäisi vaatia vain tila yhdelle kokonaisluvulle
* Minimaxin ja täten myös alpha-beta -karsinnan pahimman tapauksen tilavaativuus on O(b*d) [3]
* lepotilahaun pahimman tapauksen tilavaativuus on täten O(b*d)

## Lähteet
1. [gamedev Chess Programming Part IV](http://www.gamedev.net/page/resources/_/technical/artificial-intelligence/chess-programming-part-iv-basic-search-r1171), haettu 6.11.2016
[gamedev Chess Programming Part IV]:http://www.gamedev.net/page/resources/_/technical/artificial-intelligence/chess-programming-part-iv-basic-search-r1171
2. [CMSC 474, Introduction to Game Theory](http://www.cs.umd.edu/~hajiagha/474GT15/Lecture12122013.pdf), haettu 6.11.2016
3. [http://knight.cis.temple.edu/~vasilis/Courses/CIS603/Lectures/l7.html] (http://knight.cis.temple.edu/~vasilis/Courses/CIS603/Lectures/l7.html)