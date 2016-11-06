package logic.liikkuminen;

import components.Lauta;
import components.Nappula;
import fairy.*;
import java.util.Arrays;

/**
 * Luokka tarjoaa staattisia metodeja shakkivariantin jonkin nappulan
 * siirtämiselle.
 *
 * @author Oskari Kulmala
 */
public class FairyLiikkuminen {

    /**
     *
     * Metodi tarkistaa, onko annetussa ruudussa olevan nappulan annettuun
     * kohderuutuun siirtäminen laillinen siirto.
     *
     * @param mista lähtöruudun koordinaatit
     * @param minne kohderuudun koordinaatit
     * @param lauta lauta jolla nappula ja ruudut ovat
     * @param puoli onko siirrettävä nappula valkoinen vai musta
     * @return onko siirto laillinen
     */
    public static boolean voikoLiikkua(int[] mista, int[] minne, Lauta lauta, Nappula.Puoli puoli) {
        FairyPiece nappula = (FairyPiece) lauta.getNappula(mista);
        if (nappula.getLiikkumistyypit().contains("Torni")) {
            if (UpseerienLiikkuminen.torniVoikoLiikkua(mista, minne, lauta, puoli)) {
                return true;
            }
        }
        if (nappula.getLiikkumistyypit().contains("TorniHyppy")) {
            if (torniHyppy(mista, minne, lauta, puoli)) {
                return true;
            }
        }
        if (nappula.getLiikkumistyypit().contains("Ratsu")) {
            if (UpseerienLiikkuminen.ratsuVoikoLiikkua(mista, minne, lauta, puoli)) {
                return true;
            }
        }
//        if(nappula.getLiikkumistyypit().contains("Ratsu")) {
//            if(UpseerienLiikkuminen.ratsuVoikoLiikkua(mista, minne, lauta, puoli)) {
//                return true;
//            }
//        }
        if (nappula.getLiikkumistyypit().contains("Lahetti")) {
            if (UpseerienLiikkuminen.lahettiVoikoLiikkua(mista, minne, lauta, puoli)) {
                return true;
            }
        }
        if (nappula.getLiikkumistyypit().contains("LahettiHyppy")) {
            if (lahettiHyppy(mista, minne, lauta, puoli)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodi kertoo, voiko nappula hypätä ortogonaalisesti alkuruudusta loppuruutuun.
     * @param mista lähtöruudun koordinaatit
     * @param minne kohderuudun koordinaatit
     * @param lauta lauta jolla nappula ja ruudut ovat
     * @param puoli onko siirrettävä nappula valkoinen vai musta
     * @return onko siirto laillinen
     */
    public static boolean torniHyppy(int[] mista, int[] minne, Lauta lauta, Nappula.Puoli puoli) {
        if (minne[0] != mista[0] && minne[1] != mista[1]) {
            return false;
        } else if (minne[0] == mista[0] && minne[1] == mista[1]) {
            return false;
        } else if (minne[0] < mista[0]) {
            return OrtogonaalisestiLiikkuminen.moveLeft(mista, minne, lauta, puoli, true);
        } else if (minne[0] > mista[0]) {
            return OrtogonaalisestiLiikkuminen.moveRight(mista, minne, lauta, puoli, true);
        } else if (minne[1] < mista[1]) {
            return OrtogonaalisestiLiikkuminen.moveDown(mista, minne, lauta, puoli, true);
        } else if (minne[1] > mista[1]) {
            return OrtogonaalisestiLiikkuminen.moveUp(mista, minne, lauta, puoli, true);
        }
        return Liikkuminen.eiOma(mista, minne, lauta);
    }

    /**
     * Metodi kertoo, voiko nappula hypätä vinoon alkuruudusta loppuruutuun.
     * @param mista lähtöruudun koordinaatit
     * @param minne kohderuudun koordinaatit
     * @param lauta lauta jolla nappula ja ruudut ovat
     * @param puoli onko siirrettävä nappula valkoinen vai musta
     * @return onko siirto laillinen
     */
    public static boolean lahettiHyppy(int[] mista, int[] minne, Lauta lauta, Nappula.Puoli puoli) {
        if (minne[0] - mista[0] != minne[1] - mista[1]
                && minne[0] - mista[0] != mista[1] - minne[1]) {
            return false;
        } else if (minne[0] == mista[0] && minne[0] == mista[0]) {
            return false;
        } else if (minne[0] < mista[0] && minne[1] < mista[1]) {
            return VinottainLiikkuminen.moveLeftDown(mista, minne, lauta, puoli, true);
        } else if (minne[0] > mista[0] && minne[1] < mista[1]) {
            return VinottainLiikkuminen.moveRightDown(mista, minne, lauta, puoli, true);
        } else if (minne[0] < mista[0] && minne[1] > mista[1]) {
            return VinottainLiikkuminen.moveLeftUp(mista, minne, lauta, puoli, true);
        } else if (minne[0] > mista[0] && minne[1] > mista[1]) {
            return VinottainLiikkuminen.moveRightUp(mista, minne, lauta, puoli, true);
        }
        return Liikkuminen.eiOma(mista, minne, lauta);
    }
}
