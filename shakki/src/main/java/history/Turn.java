package history;

import logic.parser.Parser;

/**
 * Luokka toteuttaa yhden shakkivuoron (molempien pelaajien siirrot).
 *
 * @author Oskari Kulmala
 */
public class Turn {

    private int turnNumber;
    private String whiteMove = "";
    private String blackMove = "";

    /**
     * Konstruktori vuorolle, asettaa kuinka mones vuoro on.
     *
     * @param turnNumber kuinka mones vuoro on
     */
    public Turn(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    /**
     * Konstruktori, asettaa kuinka mones vuoro on ja valkoisen ja mustan
     * siirrot.
     *
     * @param turnNumber kuinka mones vuoro on
     * @param whiteMove valkoisen siirto
     * @param blackMove mustan siirto
     */
    public Turn(int turnNumber, String whiteMove, String blackMove) {
        this.turnNumber = turnNumber;
        this.whiteMove = whiteMove;
        this.blackMove = blackMove;
    }

    /**
     * Konstruktori, asettaa kuinka mones vuoro on ja valkoisen ja mustan
     * siirrot.
     *
     * @param turnNumber kuinka mones vuoro on
     * @param string valkoisen ja mustan siirrot, esim. "d4 d5"
     */
    public Turn(int turnNumber, String string) {
        this(Integer.toString(turnNumber).concat(". ").concat(string));

//        this.turnNumber = turnNumber;
//        String temp = "";
//        for(int i=0; i<string.length(); i++) {
//            if(string.charAt(i)==' ') {
//                if(whiteMove.isEmpty()) {
//                    whiteMove = temp;
//                    temp = "";
//                } else if(blackMove.isEmpty()) {
//                    blackMove = temp;
//                    temp = "";
//                } else {
//                    break;
//                }
//            }
//            temp = temp.concat(Character.toString(string.charAt(i)));
//        }
//        if(!temp.isEmpty()) {
//            if(whiteMove.isEmpty()) {
//                    whiteMove = temp;
//                } else if(blackMove.isEmpty()) {
//                    blackMove = temp;
//                }
//        }
    }

    /**
     * Luo vuoron, sisältö annettuna stringinä.
     *
     * @param notation sisältö
     */
    public Turn(String notation) {
        int pisteIndex = notation.indexOf(".");
        this.turnNumber = Integer.parseInt(notation.substring(0, pisteIndex));
        String temp = "";
        for (int i = pisteIndex + 1; i < notation.length(); i++) {
            if (notation.charAt(i) != ' ') {
                temp = temp.concat(Character.toString(notation.charAt(i)));
            } else if (!temp.isEmpty() && this.whiteMove.isEmpty()) {
                this.whiteMove = temp;
                temp = "";
            } else if (!temp.isEmpty() && !this.whiteMove.isEmpty()) {
                this.blackMove = temp;
            }
        }
        if (!temp.isEmpty()) {
            if (whiteMove.isEmpty()) {
                whiteMove = temp;
            } else if (blackMove.isEmpty()) {
                blackMove = temp;
            }
        }
    }

    /**
     * getteri.
     *
     * @return vuoronumero
     */
    public int getTurnNumber() {
        return this.turnNumber;
    }

    /**
     * getteri.
     *
     * @return siirto
     */
    public String getWhiteMove() {
        return this.whiteMove;
    }

    /**
     * getteri.
     *
     * @return siirto
     */
    public String getBlackMove() {
        return this.blackMove;
    }

    /**
     * setteri.
     *
     * @param string valkoisen siirto
     */
    public void setWhiteMove(String string) {
        whiteMove = string;
    }

    /**
     * setteri.
     *
     * @param string siirto
     */
    public void setBlackMove(String string) {
        blackMove = string;
    }

    /**
     * Metodi tarkistaa, onko vuorolla jo sekä valkoisen että mustan siirto.
     *
     * @return onko molemmat siirrot
     */
    public boolean isComplete() {
        if (whiteMove.equals("")) {
            return false;
        }
        if (blackMove.equals("")) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String string = "";
        string = string.concat(Integer.toString(turnNumber));
        if (!whiteMove.isEmpty()) {
            string = string.concat(". " + whiteMove);
            if (!blackMove.isEmpty()) {
                string = string.concat(" " + blackMove);
            }
        }
        return string;
    }

}
