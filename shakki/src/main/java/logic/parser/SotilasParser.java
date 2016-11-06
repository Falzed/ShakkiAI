package logic.parser;

import components.Lauta;
import components.Nappula;

/**
 * Apuluokka sotilaitten siirtojen jäsentämiseksi.
 *
 * @author Oskari Kulmala
 */
public class SotilasParser {

    /**
     * Metodi jäsentää sotilaan siirron.
     *
     * @param string annettu komento
     * @param vuoro kumman vuoro on
     * @param lauta lauta, jolla pitäisi liikkua
     * @return siirron alku- ja loppukoordinaatit
     */
    public static ParserReturn parseSotilas(String string, Nappula.Puoli vuoro, Lauta lauta) {
        int[][] startEndPoints = new int[2][2];
        int[] koordinaatit = new int[2];
        koordinaatit[0] = string.charAt(0) - 97;
        koordinaatit[1] = string.charAt(1) - 49;
        if (koordinaatit[0] < 0 || koordinaatit[0] >= lauta.getLeveys()) {
            return new ParserReturn("Aloitusruutu ei laudalla");
        }
        if (koordinaatit[1] < 1 || koordinaatit[1] >= lauta.getPituus()) {
            return new ParserReturn("Kohderuutu ei laudalla");
        }
//        System.out.println(startEndPoints[0][0]+","+startEndPoints[0][1]+"-"+startEndPoints[1][0]+","+startEndPoints[1][0]);
        int[] testKoord = new int[2];
        if (vuoro == Nappula.Puoli.VALKOINEN) {
//            System.out.println((int) getNappula(testKoord).getMerkki());
            testKoord[0] = koordinaatit[0];
            testKoord[1] = koordinaatit[1] - 1;
            if (lauta.getNappula(testKoord).isEmpty()) {
                testKoord[1] = koordinaatit[1] - 2;
            }
            if (lauta.getNappula(testKoord).getMerkki() == '\u2659') {
                startEndPoints[1] = Parser.parseAlgebraic(string);
                startEndPoints[0] = testKoord;
            }
        }
        if (vuoro == Nappula.Puoli.MUSTA) {
            testKoord[0] = koordinaatit[0];
            testKoord[1] = koordinaatit[1] + 1;
            if (lauta.getNappula(testKoord).isEmpty()) {
                testKoord[1] = koordinaatit[1] + 2;
            }
            if (lauta.getNappula(testKoord).getMerkki() == '\u265F') {
                startEndPoints[1] = Parser.parseAlgebraic(string);
                startEndPoints[0] = testKoord;
            }
        }
        return new ParserReturn(startEndPoints);
    }

    /**
     * Metodi jäsentää sotilaan syöntiliikkeen.
     *
     * @param string annettu komento
     * @param vuoro kumman vuoro on
     * @param lauta lauta, jolla pitäisi liikkua
     * @return siirron alku- ja loppukoordinaatit
     */
    public static ParserReturn parseSotilasSyonti(String string, Nappula.Puoli vuoro, Lauta lauta) {
        int[] koordinaatit = Parser.parseAlgebraic(string.substring(1));
        int[][] startEndPoints = new int[2][2];
        startEndPoints[1] = koordinaatit;
        int[] testKoord = new int[2];
        int n = 0;

        if (vuoro == Nappula.Puoli.VALKOINEN) {
            testKoord[1] = koordinaatit[1] - 1;

            if (koordinaatit[0] > 0) {
                testKoord[0] = koordinaatit[0] - 1;
                if (lauta.getNappula(testKoord).getMerkki() == '\u2659') {
                    startEndPoints[0][0] = testKoord[0];
                    startEndPoints[0][1] = testKoord[1];
                    n++;
                }
            }
            if (koordinaatit[0] < 7) {
                testKoord[0] = koordinaatit[0] + 1;
                if (lauta.getNappula(testKoord).getMerkki() == '\u2659') {
                    startEndPoints[0] = testKoord;
                    n++;
                }
            }
        } else {
            testKoord[1] = koordinaatit[1] + 1;
            if (koordinaatit[0] > 0) {
                testKoord[0] = koordinaatit[0] - 1;
                if (lauta.getNappula(testKoord).getMerkki() == '\u265F') {
                    startEndPoints[0][0] = testKoord[0];
                    startEndPoints[0][1] = testKoord[1];
                    n++;
                }
            }
            if (koordinaatit[0] < 7) {
                testKoord[0] = koordinaatit[0] + 1;
                if (lauta.getNappula(testKoord).getMerkki() == '\u265F') {
                    startEndPoints[0] = testKoord;
                    n++;
                }
            }
        }
        if (n > 1) {
//            System.out.println("Komento ei yksikäsitteinen");
            return new ParserReturn("Komento ei yksikäsitteinen");
        }
        return new ParserReturn(startEndPoints);
    }
}
