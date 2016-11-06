package logic;

import logic.liikkuminen.VoikoSiirtya;
import logic.liikkuminen.Liikkuminen;
import logic.parser.Parser;
import components.Lauta;
import components.Nappula;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Luokka tarjoaa staattisia metodeja, joilla voi tarkistaa onko tietty ruutu
 * uhattu tai puoli shakissa.
 *
 * @author Oskari Kulmala
 */
public class Shakitus {

    /**
     * Metodi kertoo, uhkaako jokin vastustajan nappula tiettyä ruutua.
     *
     * @param lauta lauta jolla ruutu on
     * @param koordinaatit ruudun koordinaatit
     * @param puoli onko valkoinen vai musta uhattava osapuoli
     * @return onko ruutu uhattu
     */
    public static boolean uhattu(Lauta lauta, int[] koordinaatit, Nappula.Puoli puoli) {
        for (int i = 0; i < lauta.getLeveys(); i++) {
            for (int j = 0; j < lauta.getPituus(); j++) {
                int[] mista = {i, j};
                if (VoikoSiirtya.voikoSiirtyaTornittamatta(mista, koordinaatit, lauta, null)
                        && lauta.getNappula(mista).getPuoli()
                        != puoli) {
//                    System.out.println("Mista: "+Arrays.toString(mista));
//                    System.out.println(lauta.getNappula(mista).getPuoli());
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metodi kertoo, uhkaako jokin vastustajan nappula tiettyä ruutua.
     *
     * @param lauta lauta jolla ruutu on
     * @param koordinaatit ruudun algebralliset koordinaatit
     * @param puoli onko valkoinen vai musta uhattava osapuoli
     * @return onko ruutu uhattu
     */
    public static boolean uhattu(Lauta lauta, String koordinaatit, Nappula.Puoli puoli) {
        return Shakitus.uhattu(lauta, Parser.parseAlgebraic(koordinaatit), puoli);
    }

//    /**
//     * Metodi kertoo, onko annettu puoli shakissa.
//     * @param lauta lauta jossa on ehkä shakki
//     * @param puoli valkoinen vai musta
//     * @return onko annettu puoli shakissa
//     */
//    public static boolean shakissa(Lauta lauta, Nappula.Puoli puoli) {
//        int[] koordinaatit = new int[2];
//        for (int i = 0; i < lauta.getLeveys(); i++) {
//            for (int j = 0; j < lauta.getPituus(); j++) {
//                int[] testiKoordinaatit = {i, j};
//                if (lauta.getNappula(testiKoordinaatit).getNotaatioMerkki() == 'K'
//                        && lauta.getNappula(testiKoordinaatit).getPuoli()
//                        == puoli) {
//                    koordinaatit[0] = testiKoordinaatit[0];
//                    koordinaatit[1] = testiKoordinaatit[1];
//                }
//            }
//        }
//        if(koordinaatit == null) {
//            return false;
//        }
//        return Shakitus.uhattu(lauta, koordinaatit, puoli);
//    }
    /**
     * Metodi kertoo, onko annettu puoli matissa.
     *
     * @param lauta lauta jossa on ehkä shakki
     * @param puoli valkoinen vai musta (enum)
     * @param enPassant ruutu, johon on mahdollisuus ohestalyödä tällä vuorolla
     * @return onko annettu puoli matissa.
     */
    public static boolean matissa(Lauta lauta, Nappula.Puoli puoli, int[] enPassant) {
        if (kuninkaanKoordinaatit(lauta, puoli) != null) {
            if (!shakissa(lauta, puoli)) {
                return false;
            }
            return iteroiOmienLaillisetSiirrot(lauta, puoli, enPassant);
        } else {
            return !nappuloitaLaudalla(lauta, puoli);
        }
    }

    private static boolean nappuloitaLaudalla(Lauta lauta, Nappula.Puoli puoli) {
        for (int i = 0; i < lauta.getLeveys(); i++) {
            for (int j = 0; j < lauta.getPituus(); j++) {
                int[] koordinaatit = {i, j};
                if (lauta.getNappula(koordinaatit).getPuoli() == puoli) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metodi kertoo, onko annettu puoli matissa.
     *
     * @param lauta lauta jossa on ehkä shakki
     * @param puoli valkoinen vai musta (string)
     * @param enPassant ruutu, johon on mahdollisuus ohestalyödä tällä vuorolla
     * @return onko annettu puoli matissa.
     */
    public static boolean matissa(Lauta lauta, String puoli, int[] enPassant) {
        if (puoli.equals("valkoinen")) {
            return matissa(lauta, Nappula.Puoli.VALKOINEN, enPassant);
        }
        if (puoli.equals("musta")) {
            return matissa(lauta, Nappula.Puoli.MUSTA, enPassant);
        }
        System.out.println("puolta ei hyväksytty");
        return false;
    }

    /**
     * Metodi kertoo, onko annettu puoli patissa.
     *
     * @param lauta lauta jossa on ehkä shakki
     * @param puoli valkoinen vai musta (string)
     * @param enPassant ruutu, johon on mahdollisuus ohestalyödä tällä vuorolla
     * @return onko annettu puoli matissa.
     */
    public static boolean patissa(Lauta lauta, Nappula.Puoli puoli, int[] enPassant) {
        if (shakissa(lauta, puoli)) {
            return false;
        }
        return iteroiOmienLaillisetSiirrot(lauta, puoli, enPassant);
    }

    /**
     * Metodi kertoo, onko annettu puoli shakissa.
     *
     * @param lauta lauta jossa on ehkä shakki
     * @param puoli valkoinen vai musta
     * @return onko annettu puoli shakissa
     */
    public static boolean shakissa(Lauta lauta, Nappula.Puoli puoli) {
        int[] kuninkaanSijainti = kuninkaanKoordinaatit(lauta, puoli);
        if (kuninkaanSijainti == null) {
            return false;
        }
        return Shakitus.uhattu(lauta, kuninkaanSijainti, puoli);
    }

    private static int[] kuninkaanKoordinaatit(Lauta lauta, Nappula.Puoli puoli) {
        for (int i = 0; i < lauta.getLeveys(); i++) {
            for (int j = 0; j < lauta.getPituus(); j++) {
                int[] koordinaatit = {i, j};
                if (lauta.getNappula(koordinaatit).getPuoli() == puoli
                        && lauta.getNappula(koordinaatit).getNimi().equals("Kuningas")) {
                    return koordinaatit;
                }
            }
        }
        return null;
    }

    private static boolean iteroiOmienLaillisetSiirrot(Lauta lauta, Nappula.Puoli puoli, int[] enPassant) {
        ArrayList<Nappula> omatNappulat = new ArrayList<>();
        for (int i = 0; i < lauta.getLeveys(); i++) {
            for (int j = 0; j < lauta.getPituus(); j++) {
                int[] mista = {i, j};
                Nappula nappula = lauta.getNappula(mista);
                if (nappula.getPuoli() == puoli) {
                    omatNappulat.add(nappula);
                }
            }
        }
        for (Nappula nappula : omatNappulat) {
            for (int i = 0; i < lauta.getLeveys(); i++) {
                for (int j = 0; j < lauta.getPituus(); j++) {
                    int[] minne = {i, j};
                    if (VoikoSiirtya.voikoSiirtya(nappula.getKoordinaatit(), minne, lauta, enPassant)) {
                        Lauta testLauta = lauta.kopioi();
                        Liikkuminen.koitaSiirtya(nappula.getKoordinaatit(), minne, testLauta, enPassant);
                        if (!shakissa(testLauta, puoli)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
