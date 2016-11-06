package logic.liikkuminen;

import components.*;
import java.util.Arrays;
import logic.LaudanMuutokset;
import logic.Shakitus;
import logic.Tornitus;

/**
 * Luokka hoitaa kaiken laudalla liikkumisen, ja tarjoaa myös metodit jotka
 * kertovat voisiko nappula liikkua tiettyyn ruutuun. *
 *
 * @author Oskari Kulmala
 */
public class Liikkuminen {

    /**
     * Metodi koittaa siirtää annetussa ruudussa olevan nappulan annettuun
     * kohderuutuun.
     *
     * @param mista lähtöruudun koordinaatit
     * @param minne kohderuudun koordinaatit
     * @param lauta lauta jolla nappula ja ruudut ovat
     * @param puoli onko valkoisen vai mustan vuoro
     * @param enPassant ruutu, josta voi ohesta lyöda sotilaan (jos oma sotilas
     * oikeassa paikassa)
     *
     * @return onnistuiko siirto
     */
    public static boolean koitaSiirtyaTarkistaShakki(int[] mista, int[] minne, Lauta lauta, Nappula.Puoli puoli, int[] enPassant) {
        Lauta kopio = lauta.kopioi();
        if (!koitaSiirtya(mista, minne, kopio, enPassant)) {
            return false;
        }
        if (Shakitus.shakissa(kopio, puoli)) {
            return false;
        }
        return koitaSiirtya(mista, minne, lauta, enPassant);
    }

    /**
     * Metodi koittaa siirtää annetussa ruudussa olevan nappulan annettuun
     * kohderuutuun.
     *
     * @param nappula siirrettävä nappula
     * @param minne kohderuudun koordinaatit
     * @param lauta lauta jolla nappula ja ruudut ovat
     * @param puoli vuorossa oleva pelaaja
     * @param enPassant ruutu, josta voi ohesta lyöda sotilaan (jos oma sotilas
     * oikeassa paikassa)
     *
     * @return onnistuiko siirto
     */
    public static boolean koitaSiirtyaTarkistaShakki(Nappula nappula, int[] minne, Lauta lauta, Nappula.Puoli puoli, int[] enPassant) {
        return koitaSiirtyaTarkistaShakki(nappula.getKoordinaatit(), minne, lauta, puoli, enPassant);
    }

    /**
     * Metodi koittaa siirtää annetussa ruudussa olevan nappulan annettuun
     * kohderuutuun. Ei huomioi siirrytäänkö shakkiin.
     *
     * @param mista lähtöruudun koordinaatit
     * @param minne kohderuudun koordinaatit
     * @param lauta lauta jolla nappula ja ruudut ovat
     * @param enPassant ruutu, josta voi ohesta lyöda sotilaan (jos oma sotilas
     * oikeassa paikassa)
     *
     * @return onnistuiko siirto
     */
    public static boolean koitaSiirtya(int[] mista, int[] minne, Lauta lauta, int[] enPassant) {
        Nappula nappula = lauta.getNappula(mista);
        if (!VoikoSiirtya.voikoSiirtya(mista, minne, lauta, enPassant)) {
            return false;
        }
        if (lauta.getNappula(mista).getNotaatioMerkki() == 'K'
                && Math.abs(mista[0] - minne[0]) == 2) {
            Tornitus.tornita(mista, minne, lauta);
            return true;
        }
        return siirry(mista, minne, lauta, enPassant);
    }

//    /**
//     * Metodi koittaa siirtää annetun nappulan annettuun kohderuutuun. Ei
//     * huomioi siirrytäänkö shakkiin.
//     *
//     * @param nappula siirrettävä nappula
//     * @param minne kohderuudun koordinaatit
//     * @param lauta lauta jolla nappula ja ruudut ovat
//     * @param enPassant ruutu, josta voi ohesta lyöda sotilaan (jos oma sotilas
//     * oikeassa paikassa)
//     * @return onnistuiko siirto
//     */
//    public static boolean koitaSiirtya(Nappula nappula, int[] minne, Lauta lauta, int[] enPassant) {
//        if (nappula.isEmpty()) {
//            return false;
//        }
//        int[] mista = nappula.getKoordinaatit();
//        return koitaSiirtya(mista, minne, lauta, enPassant);
//    }

    /**
     * Metodi koittaa siirtää annetun nappulan annettuun kohderuutuun. Ei
     * huomioi siirrytäänkö shakkiin, ja olettaa ettei voi ohestalyödä.
     *
     * @param nappula siirrettävä nappula
     * @param minne kohderuudun koordinaatit
     * @param lauta lauta jolla nappula ja ruudut ovat
     * @return onnistuiko siirto
     */
    public static boolean koitaSiirtya(Nappula nappula, int[] minne, Lauta lauta) {
        if (nappula.isEmpty()) {
            return false;
        }
        int[] mista = nappula.getKoordinaatit();
        return Liikkuminen.koitaSiirtya(mista, minne, lauta, null);
    }

    private static boolean siirry(int[] mista, int[] minne, Lauta lauta, int[] enPassant) {
        if (Arrays.equals(minne, enPassant) && lauta.getNappula(mista).onSotilas()) {
            SotilasLiikkuminen.enPassant(lauta.getNappula(mista), minne, lauta);
            return true;
        }
        Nappula nappula = lauta.getNappula(mista);
        if (lauta.getNappula(minne).isEmpty()) {
            LaudanMuutokset.aseta(nappula, minne, lauta);
        } else if (lauta.getNappula(minne).getPuoli() == nappula.getPuoli()) {
            System.out.println("Et voi syödä omaa nappulaasi");
            return false;
        } else {
            LaudanMuutokset.syo(nappula, minne, lauta);
        }
        nappula.setLiikkunut();
        return true;
    }

//    /**
//     * Siirrä annetussa ruudussa oleva nappula annettuun ruutuun.
//     *
//     * @param mista siirrettävän nappulan koordinaatit
//     * @param minne minne siirrytään
//     * @param lauta lauta jolla liikutaan
//     * @return onnistuiko
//     */
//    public static boolean siirry(int[] mista, int[] minne, Lauta lauta) {
//        Nappula nappula = lauta.getNappula(mista);
//        if (lauta.getNappula(minne).isEmpty()) {
//            LaudanMuutokset.aseta(nappula, minne, lauta);
//        } else if (lauta.getNappula(minne).getPuoli() == nappula.getPuoli()) {
//            System.out.println("Et voi syödä omaa nappulaasi");
//            return false;
//        } else {
//            LaudanMuutokset.syo(nappula, minne, lauta);
//        }
//        return true;
//    }

    /**
     * Siirrä annettu nappula annettuun ruutuun.
     *
     * @param nappula siirrettävä nappula
     * @param minne minne siirrytään
     * @param lauta lauta jolla liikutaan
     * @return onnistuiko
     */
    public static boolean siirry(Nappula nappula, int[] minne, Lauta lauta) {
        if (lauta.getNappula(minne).isEmpty()) {
            LaudanMuutokset.aseta(nappula, minne, lauta);
        } else if (lauta.getNappula(minne).getPuoli() == nappula.getPuoli()) {
            System.out.println("Et voi syödä omaa nappulaasi");
            return false;
        } else {
            LaudanMuutokset.syo(nappula, minne, lauta);
        }
        return true;
    }

    /**
     * Metodi tarkistaa, ettei yritetä syödä omaa nappulaa.
     *
     * @param mista aloitusruudun koordinaatit
     * @param minne kohderuudun koordinaatit
     * @param lauta jolla liikutaan
     * @return onko kohde ruutu eri puolta kuin aloitusruudussa oleva nappula
     */
    public static boolean eiOma(int[] mista, int[] minne, Lauta lauta) {
        Nappula nappula = lauta.getNappula(mista);
        if (!lauta.getNappula(minne).isEmpty()) {
            if (lauta.getNappula(minne).getPuoli() == nappula.getPuoli()) {
                return false;
            }
        }
        return true;
    }
}
