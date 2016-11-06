package logic.parser;

import components.Nappula;
import java.util.regex.Pattern;
import components.Lauta;
import logic.liikkuminen.VoikoSiirtya;

/**
 * Luokka tulkitsee annetut komennot ja algebralliset koordinaatit.
 */
public class Parser {

    /**
     * Tulkitsee algebrallisen koordinaatin (esim d4) x,y koordinaateiksi (esim
     * 3,3).
     *
     * @param string algebralliset koordinaatit
     * @return x,y koordinaatit
     */
    public static int[] parseAlgebraic(String string) {
        Pattern pattern = Pattern.compile("[a-z][0-9]*");
        if (!pattern.matcher(string).matches()) {
            return null;
        }
        int[] koordinaatit = new int[2];
        koordinaatit[0] = string.charAt(0) - 97;
        koordinaatit[1] = string.charAt(1) - 49;
        return koordinaatit;
    }

    /**
     * Tulkitsee alku- ja loppupisteen algebralliseksi käskyksi (muotoa d2-d4).
     *
     * @param startEndPoints alku- ja loppupiste
     * @return algebrallinen käsky
     */
    public static String parseToAlgebraicCommand(int[][] startEndPoints) {
        String start = Character.toString((char) (startEndPoints[0][0] + 97));
        start += Character.toString((char) (startEndPoints[0][1] + 49));
        String end = Character.toString((char) (startEndPoints[1][0] + 97));
        end += Character.toString((char) (startEndPoints[1][1] + 49));
        return start + "-" + end;
    }

    /**
     * Tulkitsee x- ja y-koordinaatit algebrallisiksi koordinaateiksi.
     *
     * @param coordinates x- ja y-koordinaatit
     * @return algebralliset koordinaatit
     */
    public static String parseToAlgebraic(int[] coordinates) {
        String string = Character.toString((char) (coordinates[0] + 97));
        string += Character.toString((char) (coordinates[1] + 49));
        return string;
    }

    /**
     * Tulkitsee algebrallisen komennon siirron alku- ja loppukoordinaateiksi.
     * Esim d2-d4 =&gt; ((3,1),(3,3)), d4 =&gt;((3,1),(3,3)), Qxd4 =&gt;
     * ((3,0),(3,3)) (syö jotain d4:stä)
     *
     * @param string annettu komento
     * @param vuoro onko valkoisen vai mustan vuoro param lauta lauta jolla
     * siirretään nappulaa
     * @param lauta lauta jolla koitetaan siirtyä
     * @return alku- ja loppukoordinaatit
     */
    public static ParserReturn parseCommand(String string, Nappula.Puoli vuoro, Lauta lauta) {
        Pattern pattern = Pattern.compile("[a-z][1-8]-[a-z][1-8]");
        ParserReturn retVal = new ParserReturn("Parser ei saanut selvää komennosta");
        if (pattern.matcher(string).matches()) {
            int[][] startEndPoints = new int[2][2];
            startEndPoints[0] = parseAlgebraic(string.substring(0, 2));
            startEndPoints[1] = parseAlgebraic(string.substring(3));
            retVal = new ParserReturn(startEndPoints);
            if (!retVal.getError().isEmpty()) {
                return retVal;
            }
            if (retVal.getCoordinates()[0][0] < 0 || retVal.getCoordinates()[0][0] >= lauta.getLeveys()) {
                return new ParserReturn("Aloitusruutu ei laudalla");
            }
            if (retVal.getCoordinates()[0][1] < 0 || retVal.getCoordinates()[0][1] >= lauta.getPituus()) {
                return new ParserReturn("Kohderuutu ei laudalla");
            }
        }
        Pattern sotilas = Pattern.compile("[a-z][1-8]");
        if (sotilas.matcher(string).matches()) {
            retVal = SotilasParser.parseSotilas(string, vuoro, lauta);
        }
        Pattern sotilasSyonti = Pattern.compile("x[a-z][1-8]");
        if (sotilasSyonti.matcher(string).matches()) {
            retVal = SotilasParser.parseSotilasSyonti(string, vuoro, lauta);
        }
        //voi myös syödä ilman x:ää
        Pattern upseeri = Pattern.compile("[A-Z][a-z][1-8]|[\\u2654-\\u265F][a-z][1-8]");
        if (upseeri.matcher(string).matches()) {
            retVal = parseUpseeri(string, vuoro, lauta);
        }
        //Voi liikkua tyhjään vaikka antaisi käskyn syödä
        Pattern upseeriSyonti = Pattern.compile("[A-Z]x[a-z][1-8]|[\\u2654-\\u265F]x[a-z][1-8]");
        if (upseeriSyonti.matcher(string).matches()) {
            retVal = parseUpseeri(string, vuoro, lauta);
        }
        Pattern tornitus = Pattern.compile("(O-){1,2}O|(0-){1,2}0");
        if (tornitus.matcher(string).matches()) {
            retVal = parseTornitus(string, vuoro, lauta);
        }
        return retVal;
    }

    private static ParserReturn parseUpseeri(String string, Nappula.Puoli vuoro, Lauta lauta) {
        char notaatio = string.charAt(0);
        int[] koordinaatit = parseAlgebraic(string.substring(1));
        int[][] startEndPoints = new int[2][2];
        startEndPoints[1] = koordinaatit;
        int[] testKoord = new int[2];
        int n = 0;
        Nappula nappula;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                testKoord[0] = i;
                testKoord[1] = j;
                nappula = lauta.getNappula(testKoord);
                if ((nappula.getNotaatioMerkki() == notaatio
                        || nappula.getMerkki() == notaatio)
                        && nappula.getPuoli() == vuoro) {
                    if (VoikoSiirtya.voikoSiirtya(nappula, koordinaatit, lauta, null)) {
                        n++;
                        startEndPoints[0][0] = testKoord[0];
                        startEndPoints[0][1] = testKoord[1];
                    }
                }
            }
        }
        if (n > 1) {
            return new ParserReturn("Komento ei yksikäsitteinen");
        }
        if (n == 0) {
            return new ParserReturn("Yksikään antamasi tyyppinen nappulasi ei "
                    + "voi liikkua kohderuutuun");
        }
        return new ParserReturn(startEndPoints);
    }

    private static ParserReturn parseTornitus(String string, Nappula.Puoli vuoro, Lauta lauta) {
        Nappula kuningas = null;
        int[][] startEnd = new int[2][2];
        for (int i = 0; i < lauta.getLeveys(); i++) {
            for (int j = 0; j < lauta.getPituus(); j++) {
                int[] koord = {i, j};
                if (lauta.getNappula(koord).getPuoli() == vuoro
                        && lauta.getNappula(koord).getNotaatioMerkki() == 'K') {
                    kuningas = lauta.getNappula(koord);
                    break;
                }
            }
        }
        if (string.equals("O-O") || string.equals("0-0")) {
            if (kuningas == null) {
                return new ParserReturn("Sinulla ei ole kuningasta");
            }
            startEnd[0] = kuningas.getKoordinaatit();
            int[] end = {kuningas.getKoordinaatit()[0] + 2,
                kuningas.getKoordinaatit()[1]};
            startEnd[1] = end;
            return new ParserReturn(startEnd);
        }
        if (string.equals("O-O-O") || string.equals("0-0-0")) {
            if (kuningas == null) {
                return new ParserReturn("Sinulla ei ole kuningasta");
            }
            startEnd[0] = kuningas.getKoordinaatit();
            int[] end = {kuningas.getKoordinaatit()[0] - 2,
                kuningas.getKoordinaatit()[1]};
            startEnd[1] = end;
            return new ParserReturn(startEnd);
        }
        return new ParserReturn("kutsuttiin logic.parser.Parser.parseTornitus "
                + "vaikka komento ei ollut O-O, O-O-O, 0-0 tai 0-0-0");
    }
}
