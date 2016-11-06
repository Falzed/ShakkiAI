package logic.liikkuminen;

import components.Lauta;
import components.Nappula;

/**
 * Luokka tarjoaa apumetodit jotka kertovat voiko aloitusruudusta kulkea lähetin
 * lailla kohderuutuun.
 *
 * @author Oskari Kulmala
 */
public class VinottainLiikkuminen {

    /**
     * Metodi kertoo, voisiko nappula liikkua vinoon alas ja vasemmalle (kuten
     * lähetti).
     *
     * @param mista aloitusruudun koordinaatit
     * @param minne kohderuudun koordinaatit
     * @param lauta lauta jolla ollaan
     * @param puoli onko siirrettävä nappula vlakoinen vai musta
     * @param hyppaa voidaanko hypata tiellä olevan nappulan yli
     * @return onnistuuko
     */
    public static boolean moveLeftDown(int[] mista, int[] minne, Lauta lauta, Nappula.Puoli puoli, boolean hyppaa) {
        int delta = mista[0] - minne[0];
        for (int i = 1; i < delta; i++) {
            int[] testiKoordinaatit
                    = {mista[0] - i, mista[1] - i};
            if (!lauta.getNappula(testiKoordinaatit).isEmpty() && hyppaa == false) {
                return false;
            }
        }
        return eiOma(mista, minne, lauta);
    }

    /**
     * Metodi kertoo, voisiko nappula liikkua vinoon ylös ja vasemmalle (kuten
     * lähetti).
     *
     * @param mista aloitusruudun koordinaatit
     * @param minne kohderuudun koordinaatit
     * @param lauta lauta jolla ollaan
     * @param puoli onko siirrettävä nappula vlakoinen vai musta
     * @param hyppaa voidaanko hypata tiellä olevan nappulan yli
     * @return onnistuuko
     */
    public static boolean moveLeftUp(int[] mista, int[] minne, Lauta lauta, Nappula.Puoli puoli, boolean hyppaa) {
        int delta = mista[0] - minne[0];
        for (int i = 1; i < delta; i++) {
            int[] testiKoordinaatit
                    = {mista[0] - i, mista[1] + i};
            if (!lauta.getNappula(testiKoordinaatit).isEmpty() && hyppaa == false) {
                return false;
            }
        }
        return eiOma(mista, minne, lauta);
    }

    /**
     * Metodi kertoo, voisiko nappula liikkua vinoon alas ja oikealle (kuten
     * lähetti).
     *
     * @param mista aloitusruudun koordinaatit
     * @param minne kohderuudun koordinaatit
     * @param lauta lauta jolla ollaan
     * @param puoli onko siirrettävä nappula vlakoinen vai musta
     * @param hyppaa voidaanko hypata tiellä olevan nappulan yli
     * @return onnistuuko
     */
    public static boolean moveRightDown(int[] mista, int[] minne, Lauta lauta, Nappula.Puoli puoli, boolean hyppaa) {
        int delta = minne[0] - mista[0];
        for (int i = 1; i < delta; i++) {
            int[] testiKoordinaatit
                    = {mista[0] + i, mista[1] - i};
            if (!lauta.getNappula(testiKoordinaatit).isEmpty() && hyppaa == false) {
                return false;
            }
        }
        return eiOma(mista, minne, lauta);
    }

    /**
     * Metodi kertoo, voisiko nappula liikkua vinoon ylös ja oikealle (kuten
     * lähetti).
     *
     * @param mista aloitusruudun koordinaatit
     * @param minne kohderuudun koordinaatit
     * @param lauta lauta jolla ollaan
     * @param puoli onko siirrettävä nappula vlakoinen vai musta
     * @param hyppaa voidaanko hypata tiellä olevan nappulan yli
     * @return onnistuuko
     */
    public static boolean moveRightUp(int[] mista, int[] minne, Lauta lauta, Nappula.Puoli puoli, boolean hyppaa) {
        int delta = minne[0] - mista[0];
        for (int i = 1; i < delta; i++) {
            int[] testiKoordinaatit
                    = {mista[0] + i, mista[1] + i};
            if (!lauta.getNappula(testiKoordinaatit).isEmpty() && hyppaa == false) {
                return false;
            }
        }
        return eiOma(mista, minne, lauta);
    }

    private static boolean eiOma(int[] mista, int[] minne, Lauta lauta) {
        Nappula nappula = lauta.getNappula(mista);
        if (!lauta.getNappula(minne).isEmpty()) {
            if (lauta.getNappula(minne).getPuoli() == nappula.getPuoli()) {
                return false;
            }
        }
        return true;
    }
}
