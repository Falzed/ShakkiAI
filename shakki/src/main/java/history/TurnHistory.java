package history;

import java.util.LinkedList;
import variants.*;

/**
 * Luokka toteuttaa laudan vuorohistorian.
 *
 * @author Oskari Kulmala
 */
public class TurnHistory {

    private final LinkedList<Turn> history;

    /**
     * Luo vuorohistorian.
     */
    public TurnHistory() {
        this.history = new LinkedList<>();
    }

    /**
     * Luo vuorohistorian annetun merkkijonon perusteella.
     *
     * @param string kuvaa tähänastiset vuorot, esim 1. d4 d5\n 2. c4 ...
     */
    public TurnHistory(String string) {
        this.history = new LinkedList<Turn>();
        if (string.contains("\n")) {
            String[] strings = string.split(Character.toString('\n'));
            for (String string1 : strings) {
                Turn turn = new Turn(string1);
                history.add(turn);
            }
        } else {
            String[] strings = string.split("\\.");
            for (int i = 1; i < strings.length; i++) {
                String vuoroString = Integer.toString(i) + ". " + strings[i];
                Turn turn = new Turn(vuoroString);
                history.add(turn);
            }
        }
    }

    /**
     * Lisää historiaan uuden vuoron.
     *
     * @param turn lisättävä vuoro
     */
    public void addTurn(Turn turn) {
        this.history.add(turn);
    }

    /**
     * getteri.
     *
     * @return lista
     */
    public LinkedList<Turn> getList() {
        return this.history;
    }

    /**
     * Poistaa kaikki vuorot tietyn vuoron jälkeen. Ei tällä hetkellä käytetä
     * mihinkään.
     *
     * @param vuoro viimeisen säilytettävän vuoron numero
     */
    public void removeAfter(int vuoro) {
        while (history.size() > vuoro) {
            history.remove(history.size() - 1);
        }
    }

    /**
     * Lisää historian loppuun joko uuden vuoron tai siirron olemassa olevalle
     * vuorolle.
     *
     * @param komento annettu komento joka lisätään historiaan
     */
    public void jatkaVuoroHistoriaa(String komento) {
        if (getViimeinenVuoro().isComplete()) {
            addTurn(new Turn(getVuoroNumero(), komento, ""));
        } else {
            if (getViimeinenVuoro().getWhiteMove().isEmpty()) {
                getViimeinenVuoro().setWhiteMove(komento);
            } else if (getViimeinenVuoro().getBlackMove().isEmpty()) {
                getViimeinenVuoro().setBlackMove(komento);
            }
        }
    }

    /**
     * getteri.
     *
     * @return viimeinen vuoro
     */
    public Turn getViimeinenVuoro() {
        return history.getLast();
    }

    /**
     * getteri.
     *
     * @return vuoronumero
     */
    public int getVuoroNumero() {
        if (history.size() == 0) {
            return 1;
        }
        if (history.getLast().isComplete()) {
            return history.getLast().getTurnNumber() + 1;
        }
        return history.getLast().getTurnNumber();
    }

    @Override
    public String toString() {
        String string = "";
        for (Turn turn : history) {
            string = string.concat(turn.toString());
            if (turn.getTurnNumber() != history.getLast().getTurnNumber()) {
                string = string.concat("\n");
            }
        }
        return string;
    }
}
