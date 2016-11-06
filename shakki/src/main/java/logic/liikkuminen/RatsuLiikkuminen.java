package logic.liikkuminen;

import components.Lauta;
import components.Nappula;

/**
 * Luokka tarjoaa apumetodit jotka kertovat voiko aloitusruudusta kulkea ratsun
 * lailla kohderuutuun.
 *
 * @author Oskari Kulmala
 */
public class RatsuLiikkuminen {

    /**
     * Metodi kertoo, onko siirto 2 ruutua vaaka/pystysuoraan ja 1 ruutu
     * pysty/vaakasuoraan.
     *
     * @param mista aloitusruudun koordinaatit
     * @param minne kohderuudun koordinaatit
     * @param lauta lauta jolla ollaan
     * @param puoli onko siirrettävä nappula vlakoinen vai musta
     * @return onko siirto 2 ruutua vaaka/pystysuoraan ja 1 ruutu
     * pysty/vaakasuoraan
     */
    public static boolean oneTwo(int[] mista, int[] minne, Lauta lauta, Nappula.Puoli puoli) {
        if (Math.abs(minne[0] - mista[0]) == 1) {
            if (Math.abs(minne[1] - mista[1]) == 2) {
                return true;
            } else {
                return false;
            }
        }
        if (Math.abs(minne[0] - mista[0]) == 2) {
            if (Math.abs(minne[1] - mista[1]) == 1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
