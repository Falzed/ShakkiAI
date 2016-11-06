package logic;

import logic.liikkuminen.Liikkuminen;
import components.Lauta;
import components.Nappula;

/**
 * Luokka tarjoaa staattiset metodit tornitukseen.
 * @author Oskari Kulmala
 */
public class Tornitus {

    /**
     * Onko mahdollista tornittaa anntusta ruudusta toiseen annettuun ruutuun.
     * @param mista kuninkaan ruutu
     * @param minne minne yritetään tornittaa
     * @param lauta lauta jolla liikutaan
     * @param puoli kuninkaan puoli
     * @return onko siirto laillinen
     */
    public static boolean voikoTornittaa(int[] mista, int[] minne, Lauta lauta, Nappula.Puoli puoli) {
        Nappula kuningas = lauta.getNappula(mista);
        if (Math.abs(minne[0] - mista[0]) != 2) {
            return false;
        }
        if (Shakitus.shakissa(lauta, puoli)) {
            return false;
        }
        Nappula torni = new Nappula();
        if (minne[0] > mista[0]) {
            int[] torninKoordinaatit = {lauta.getLeveys() - 1, minne[1]};
            torni = lauta.getNappula(torninKoordinaatit);
            for (int i = 0; i < 3; i++) {
                int[] koordinaatit = {mista[0] + i, minne[1]};
                if (Shakitus.uhattu(lauta, koordinaatit, puoli)
                        || (!lauta.getNappula(koordinaatit).isEmpty() && i != 0)) {
//                    System.out.println(Arrays.toString(koordinaatit));
//                    System.out.println("Shakissa: "+Shakitus.uhattu(lauta, koordinaatit, kuningas.getPuoli()));
//                    System.out.println("Ei tyhjä: "+!lauta.getNappula(koordinaatit).isEmpty());
//                    System.out.println(lauta.getNappula(koordinaatit).getPuoli());
//                    System.out.println(lauta.getNappula(koordinaatit).getNotaatioMerkki());
                    return false;
                }
            }
        } else {
            int[] torninKoordinaatit = {0, minne[1]};
            torni = lauta.getNappula(torninKoordinaatit);
            for (int i = 0; i < 3; i++) {
                int[] koordinaatit = {mista[0] - i, minne[1]};
                if (Shakitus.uhattu(lauta, koordinaatit, puoli)
                        || (!lauta.getNappula(koordinaatit).isEmpty() && i != 0)) {
                    return false;
                }
            }
        }
        if (kuningas.getPuoli() != torni.getPuoli()) {
            return false;
        }
        if (kuningas.getNotaatioMerkki() != 'K' || torni.getNotaatioMerkki() != 'R') {
            return false;
        }
        if (kuningas.onkoLiikkunut() || torni.onkoLiikkunut()) {
            return false;
        }
        return true;
    }

    /**
     * Metodi suorittaa annetun tornituksen.
     * @param mista kuninkaan ruutu
     * @param minne minne yritetään tornittaa
     * @param lauta lauta jolla liikutaan
     */
    public static void tornita(int[] mista, int[] minne, Lauta lauta) {
        Nappula kuningas = lauta.getNappula(mista);
        Nappula torni = new Nappula();
        if (minne[0] > mista[0]) {
            int[] torninKoordinaatit = {lauta.getLeveys() - 1, minne[1]};
            torni = lauta.getNappula(torninKoordinaatit);
            Liikkuminen.siirry(kuningas, minne, lauta);
            int[] torniMinne = {minne[0] - 1, minne[1]};
            Liikkuminen.siirry(torni, torniMinne, lauta);
        } else {
            int[] torninKoordinaatit = {0, minne[1]};
            torni = lauta.getNappula(torninKoordinaatit);
            int[] torniMinne = {minne[0] + 1, minne[1]};
            Liikkuminen.siirry(kuningas, minne, lauta);
            Liikkuminen.siirry(torni, torniMinne, lauta);
        }
    }
}
