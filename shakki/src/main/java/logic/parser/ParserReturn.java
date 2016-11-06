package logic.parser;

/**
 * Luokka parserin palautusarvoja varten, virheviesti tai (myös molemmat sinänsä
 * mahdollista) siirron alku- ja loppukoordinaatit.
 *
 */
public class ParserReturn {

    private final String errorMessage;
    private final int[][] startEndPoints;

    /**
     * Kontruktori jos ei virhettä.
     *
     * @param startEndPoints alku- ja loppukoordinaatit
     */
    public ParserReturn(int[][] startEndPoints) {
        this.errorMessage = "";
        this.startEndPoints = startEndPoints;
    }

    /**
     * Kontruktori jos ei virhe.
     *
     * @param error virheviesti
     */
    public ParserReturn(String error) {
        this.errorMessage = error;
        this.startEndPoints = null;
    }

    public int[][] getCoordinates() {
        return startEndPoints;
    }

    public String getError() {
        return errorMessage;
    }

//    @Override
//    public String toString() {
//        String string = "";
//        if (this.startEndPoints != null) {
//            string += "(" + startEndPoints[0][0] + ", " + startEndPoints[0][1]
//                    + ")\n";
//            string += "(" + startEndPoints[1][0] + ", " + startEndPoints[1][1] + ")\n";
//        } else {
//            string += "null\n";
//        }
//        string += errorMessage;
//        return string;
//    }
}
