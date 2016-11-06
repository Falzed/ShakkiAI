package logic.liikkuminen;

import components.Lauta;
import components.Nappula;
import java.util.Arrays;

/**
 * Luokka tarjoaa apumetodit jotka kertovat voiko aloitusruudusta kulkea tornin
 * lailla kohderuutuun.
 *
 * @author Oskari Kulmala
 */
public class OrtogonaalisestiLiikkuminen {

    /**
     * Metodi kertoo, voisiko nappula liikkua vasemmalle (kuten torni).
     *
     * @param mista aloitusruudun koordinaatit
     * @param minne kohderuudun koordinaatit
     * @param lauta lauta jolla ollaan
     * @param puoli onko siirrettävä nappula valkoinen vai musta
     * @param hyppaa voidaanko hypata tiellä olevan nappulan yli
     * @return onnistuuko
     */
    public static boolean moveLeft(int[] mista, int[] minne, Lauta lauta, Nappula.Puoli puoli, boolean hyppaa) {
        int delta = 0;
        delta = mista[0] - minne[0];

        int[] testiKoordinaatit = {0, 0};
        for (int i = 1; i < delta; i++) {
            testiKoordinaatit[0] = mista[0] - i;
            testiKoordinaatit[1] = mista[1];
            if (!lauta.getNappula(testiKoordinaatit).isEmpty() && hyppaa == false) {
                return false;
            }
        }
        return eiOma(mista, minne, lauta);
    }

    /**
     * Metodi kertoo, voisiko nappula liikkua oikealle (kuten torni).
     *
     * @param mista aloitusruudun koordinaatit
     * @param minne kohderuudun koordinaatit
     * @param lauta lauta jolla ollaan
     * @param puoli onko siirrettävä nappula vlakoinen vai musta
     * @param hyppaa voidaanko hypata tiellä olevan nappulan yli
     * @return onnistuuko
     */
    public static boolean moveRight(int[] mista, int[] minne, Lauta lauta, Nappula.Puoli puoli, boolean hyppaa) {
        int delta = minne[0] - mista[0];
        int[] testiKoordinaatit = {0, 0};
        for (int i = 1; i < delta; i++) {
            testiKoordinaatit[0] = mista[0] + i;
            testiKoordinaatit[1] = mista[1];
            if (!lauta.getNappula(testiKoordinaatit).isEmpty() && hyppaa == false) {
                return false;
            }
        }
        return eiOma(mista, minne, lauta);
    }

    /**
     * Metodi kertoo, voisiko nappula liikkua ylös (kuten torni).
     *
     * @param mista aloitusruudun koordinaatit
     * @param minne kohderuudun koordinaatit
     * @param lauta lauta jolla ollaan
     * @param puoli onko siirrettävä nappula vlakoinen vai musta
     * @param hyppaa voidaanko hypata tiellä olevan nappulan yli
     * @return onnistuuko
     */
    public static boolean moveUp(int[] mista, int[] minne, Lauta lauta, Nappula.Puoli puoli, boolean hyppaa) {
        int delta = minne[1] - mista[1];
        int[] testiKoordinaatit = {0, 0};
        for (int i = 1; i < delta; i++) {
            testiKoordinaatit[1] = mista[1] + i;
            testiKoordinaatit[0] = mista[0];
            if (!lauta.getNappula(testiKoordinaatit).isEmpty() && hyppaa == false) {
                return false;
            }
        }
        return eiOma(mista, minne, lauta);
    }

    /**
     * Metodi kertoo, voisiko nappula liikkua alas (kuten torni).
     *
     * @param mista aloitusruudun koordinaatit
     * @param minne kohderuudun koordinaatit
     * @param lauta lauta jolla ollaan
     * @param puoli onko siirrettävä nappula vlakoinen vai musta
     * @param hyppaa voidaanko hypata tiellä olevan nappulan yli
     * @return onnistuuko
     */
    public static boolean moveDown(int[] mista, int[] minne, Lauta lauta, Nappula.Puoli puoli, boolean hyppaa) {
        int delta = mista[1] - minne[1];
        int[] testiKoordinaatit = {0, 0};
        for (int i = 1; i < delta; i++) {
            testiKoordinaatit[1] = mista[1] - i;
            testiKoordinaatit[0] = mista[0];
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
