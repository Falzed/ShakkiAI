package logic.liikkuminen;

import components.Lauta;
import components.Nappula;
import logic.Tornitus;
import static logic.liikkuminen.Liikkuminen.eiOma;

/**
 * Luokka tarjoaa staattisia meodeja selvittämään, onko jokin siirto laillinen.
 *
 * @author Oskari Kulmala
 */
public class UpseerienLiikkuminen {

    /**
     * Metodi kertoo, voiko annetussa ruudussa oleva kuningas siirtyä annettuun
     * kohderuutuun.
     *
     * @param mista nappulan ruutu
     * @param minne minne pitäisi siirtyä
     * @param lauta lauta jolla liikutaan
     * @param puoli nappulan puoli
     * @return voiko annetussa ruudussa oleva kuningas siirtyä annettuun
     * kohderuutuun
     */
    public static boolean kuningasVoikoLiikkua(int[] mista, int[] minne, Lauta lauta, Nappula.Puoli puoli) {
        if (Tornitus.voikoTornittaa(mista, minne, lauta, puoli)) {
            return true;
        }
        return kuningasVoikoLiikkuaTornittamatta(mista, minne, lauta, puoli);
    }

    /**
     * Metodi kertoo, voiko annetussa ruudussa oleva kuningas siirtyä annettuun
     * kohderuutuun ilman tornitusta.
     *
     * @param mista nappulan ruutu
     * @param minne minne pitäisi siirtyä
     * @param lauta lauta jolla liikutaan
     * @param puoli nappulan puoli
     * @return voiko annetussa ruudussa oleva kuningas siirtyä annettuun
     * kohderuutuun
     */
    public static boolean kuningasVoikoLiikkuaTornittamatta(int[] mista, int[] minne, Lauta lauta, Nappula.Puoli puoli) {
        if (Math.abs(minne[0] - mista[0]) > 1) {
            return false;
        }
        if (Math.abs(minne[1] - mista[1]) > 1) {
            return false;
        }
        if (minne[1] - mista[1] == 0
                && minne[0] - mista[0] == 0) {
            return false;
        }
        return eiOma(mista, minne, lauta);
    }

    /**
     * Metodi kertoo, voiko annetussa ruudussa oleva kuningatar siirtyä
     * annettuun kohderuutuun.
     *
     * @param mista nappulan ruutu
     * @param minne minne pitäisi siirtyä
     * @param lauta lauta jolla liikutaan
     * @param puoli nappulan puoli
     * @return onko siirto laillinen
     */
    public static boolean kuningatarVoikoLiikkua(int[] mista, int[] minne, Lauta lauta, Nappula.Puoli puoli) {
        //Tornimaisesti liikkuminen
        if (!torniVoikoLiikkua(mista, minne, lauta, puoli) && !lahettiVoikoLiikkua(mista, minne, lauta, puoli)) {
            return false;
        }
        return eiOma(mista, minne, lauta);
    }

    /**
     * Metodi kertoo, voiko annetussa ruudussa oleva torni siirtyä annettuun
     * kohderuutuun.
     *
     * @param mista nappulan ruutu
     * @param minne minne pitäisi siirtyä
     * @param lauta lauta jolla liikutaan
     * @param puoli nappulan puoli
     * @return onko siirto laillinen
     */
    public static boolean torniVoikoLiikkua(int[] mista, int[] minne, Lauta lauta, Nappula.Puoli puoli) {
        if (minne[0] != mista[0] && minne[1] != mista[1]) {
            return false;
        } else if (minne[0] == mista[0] && minne[1] == mista[1]) {
            return false;
        } else if (minne[0] < mista[0]) {
            return OrtogonaalisestiLiikkuminen.moveLeft(mista, minne, lauta, puoli, false);
        } else if (minne[0] > mista[0]) {
            return OrtogonaalisestiLiikkuminen.moveRight(mista, minne, lauta, puoli, false);
        } else if (minne[1] < mista[1]) {
            return OrtogonaalisestiLiikkuminen.moveDown(mista, minne, lauta, puoli, false);
        } else if (minne[1] > mista[1]) {
            return OrtogonaalisestiLiikkuminen.moveUp(mista, minne, lauta, puoli, false);
        }
        return eiOma(mista, minne, lauta);
    }

    /**
     * Metodi kertoo, voiko annetussa ruudussa oleva lähetti siirtyä annettuun
     * kohderuutuun.
     *
     * @param mista nappulan ruutu
     * @param minne minne pitäisi siirtyä
     * @param lauta lauta jolla liikutaan
     * @param puoli nappulan puoli
     * @return onko siirto laillinen
     */
    public static boolean lahettiVoikoLiikkua(int[] mista, int[] minne, Lauta lauta, Nappula.Puoli puoli) {
        if (minne[0] - mista[0] != minne[1] - mista[1]
                && minne[0] - mista[0] != mista[1] - minne[1]) {
            return false;
        } else if (minne[0] == mista[0] && minne[0] == mista[0]) {
            return false;
        } else if (minne[0] < mista[0] && minne[1] < mista[1]) {
            return VinottainLiikkuminen.moveLeftDown(mista, minne, lauta, puoli, false);
        } else if (minne[0] > mista[0] && minne[1] < mista[1]) {
            return VinottainLiikkuminen.moveRightDown(mista, minne, lauta, puoli, false);
        } else if (minne[0] < mista[0] && minne[1] > mista[1]) {
            return VinottainLiikkuminen.moveLeftUp(mista, minne, lauta, puoli, false);
        } else if (minne[0] > mista[0] && minne[1] > mista[1]) {
            return VinottainLiikkuminen.moveRightUp(mista, minne, lauta, puoli, false);
        }
        return eiOma(mista, minne, lauta);
    }

    /**
     * Metodi kertoo, voiko annetussa ruudussa oleva ratsu siirtyä annettuun
     * kohderuutuun.
     *
     * @param mista nappulan ruutu
     * @param minne minne pitäisi siirtyä
     * @param lauta lauta jolla liikutaan
     * @param puoli nappulan puoli
     * @return onko siirto laillinen
     */
    public static boolean ratsuVoikoLiikkua(int[] mista, int[] minne, Lauta lauta, Nappula.Puoli puoli) {
        if (!RatsuLiikkuminen.oneTwo(mista, minne, lauta, puoli)) {
            return false;
        }
        return eiOma(mista, minne, lauta);
    }
}
