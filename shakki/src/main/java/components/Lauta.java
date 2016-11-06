package components;

import logic.parser.Parser;

/**
 * Luokka toteuttaa shakkilaudan. Lauta tietää mikä nappula on missäkin
 * ruudussa.
 *
 * @author Oskari Kulmala
 */
public class Lauta {

    private final int leveys;
    private final int pituus;
    private Nappula[][] nappulat;

    /**
     * Konstruktori ilman parametreja, olettaa laudan kooksi 8x8.
     */
    public Lauta() {
        this.leveys = 8;
        this.pituus = 8;
        nappulat = new Nappula[leveys][pituus];
    }

    /**
     * Kostruktori jolle annettu laudan leveys ja pituus.
     *
     * @param leveys laudan leveys
     * @param pituus laudan pituus
     */
    public Lauta(int leveys, int pituus) {
        this.leveys = leveys;
        this.pituus = pituus;
        nappulat = new Nappula[leveys][pituus];
    }

    /**
     * getteri.
     *
     * @param koordinaatit koordinaatit
     * @return nappula
     */
    public Nappula getNappula(int[] koordinaatit) {
        return nappulat[koordinaatit[0]][koordinaatit[1]];
    }

    /**
     * getteri.
     *
     * @param string koordinaatit
     * @return nappula
     */
    public Nappula getNappula(String string) {
        int[] koordinaatit = Parser.parseAlgebraic(string);
        return getNappula(koordinaatit);
    }

    /**
     * Kopioi laudan.
     *
     * @return palauttaa laudan kopion
     */
    public Lauta kopioi() {
        Lauta kopio = new Lauta(leveys, pituus);
        kopio.alustaLauta();
        for (int i = 0; i < leveys; i++) {
            for (int j = 0; j < pituus; j++) {
                int[] koordinaatit = {i, j};
                Nappula kopioituNappula = getNappula(koordinaatit).kopioi();
                logic.LaudanMuutokset.aseta(kopioituNappula, koordinaatit, kopio);
            }
        }
        return kopio;
    }

    /**
     * Alustaa laudan jokaiseen ruutuun Tyhja-olion.
     */
    public void alustaLauta() {
        Tyhja tyhja = new Tyhja();
        for (int i = 0; i < leveys; i++) {
            for (int j = 0; j < pituus; j++) {
                nappulat[i][j] = tyhja;
            }
        }
    }
    /**
     * setteri.
     * @param nappula nappula joka laitetaan laudalle
     * @param koordinaatit minne nappula laitetaan
     */
    public void setNappula(Nappula nappula, int[] koordinaatit) {
        nappulat[koordinaatit[0]][koordinaatit[1]] = nappula;
    }

    public int getLeveys() {
        return leveys;
    }

    public int getPituus() {
        return pituus;
    }
}
