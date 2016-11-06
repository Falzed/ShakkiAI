package logic.liikkuminen;

import components.Lauta;
import components.Nappula;

public class VoikoSiirtya {

    /**
     *
     * Metodi tarkistaa, onko annetussa ruudussa olevan nappulan annettuun
     * kohderuutuun siirtäminen laillinen siirto.
     *
     * @param mista lähtöruudun koordinaatit
     * @param minne kohderuudun koordinaatit
     * @param lauta lauta jolla nappula ja ruudut ovat
     * @param enPassant ruutu, josta voi ohesta lyöda sotilaan (jos oma sotilas
     * oikeassa paikassa)
     * @return onko siirto laillinen
     */
    public static boolean voikoSiirtya(int[] mista, int[] minne, Lauta lauta, int[] enPassant) {
//        if (enPassant != null) {
//            System.out.println(enPassant[0] + "," + enPassant[1]);
//        }
        if (lauta.getNappula(mista).isEmpty()) {
            return false;
        }
        Nappula nappula = lauta.getNappula(mista);
        boolean voiko = false;
        if (nappula.getMerkki() == '\u2655' || nappula.getMerkki() == '\u265B') {
            voiko = UpseerienLiikkuminen.kuningatarVoikoLiikkua(mista, minne, lauta, nappula.getPuoli());
        } else if (nappula.getMerkki() == '\u2654' || nappula.getMerkki() == '\u265A') {
            voiko = UpseerienLiikkuminen.kuningasVoikoLiikkua(mista, minne, lauta, nappula.getPuoli());
        } else if (nappula.getMerkki() == '\u2656' || nappula.getMerkki() == '\u265C') {
            voiko = UpseerienLiikkuminen.torniVoikoLiikkua(mista, minne, lauta, nappula.getPuoli());
        } else if (nappula.getMerkki() == '\u2658' || nappula.getMerkki() == '\u265E') {
            voiko = UpseerienLiikkuminen.ratsuVoikoLiikkua(mista, minne, lauta, nappula.getPuoli());
        } else if (nappula.getMerkki() == '\u2657' || nappula.getMerkki() == '\u265D') {
            voiko = UpseerienLiikkuminen.lahettiVoikoLiikkua(mista, minne, lauta, nappula.getPuoli());
        } else if (nappula.getMerkki() == '\u2659' || nappula.getMerkki() == '\u265F') {
            voiko = SotilasLiikkuminen.sotilasVoikoLiikkua(mista, minne, lauta, nappula.getPuoli(), enPassant);
        } else {
            voiko = FairyLiikkuminen.voikoLiikkua(mista, minne, lauta, nappula.getPuoli());
        }

        return voiko;
    }

    /**
     * Metodi tarkistaa, voiko siirron toteuttaa ilman tornitusta.
     *
     * @param mista siirrettävän nappulan sijainti
     * @param minne nappula siirretään
     * @param lauta lauta jolla liikutaan
     * @param enPassant ruutu, josta voidaan ohestalyödä sotilas
     * @return voiko siirron toteuttaa ilman tornitusta
     */
    public static boolean voikoSiirtyaTornittamatta(int[] mista, int[] minne, Lauta lauta, int[] enPassant) {
        Nappula nappula = lauta.getNappula(mista);
        if (nappula.isEmpty()) {
            return false;
        }
        if (!nappula.getNimi().equals("Kuningas")) {
            return voikoSiirtya(mista, minne, lauta, enPassant);
        }
        return UpseerienLiikkuminen.kuningasVoikoLiikkuaTornittamatta(mista, minne, lauta, nappula.getPuoli());
    }

    /**
     *
     * Metodi tarkistaa, onko annetussa ruudussa olevan nappulan annettuun
     * kohderuutuun siirtäminen laillinen siirto.
     *
     * @param nappula siirrettävä nappula
     * @param minne kohderuudun koordinaatit
     * @param lauta lauta jolla nappula ja ruudut ovat
     * @param enPassant ruutu, josta voi ohesta lyöda sotilaan (jos oma sotilas
     * oikeassa paikassa)
     * @return onko siirto laillinen
     */
    public static boolean voikoSiirtya(Nappula nappula, int[] minne, Lauta lauta, int[] enPassant) {
        return voikoSiirtya(nappula.getKoordinaatit(), minne, lauta, enPassant);
    }
}
