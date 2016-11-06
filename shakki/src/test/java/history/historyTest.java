package history;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class historyTest {

    @Test
    public void toStringTest() {
        TurnHistory historia = new TurnHistory();
        Turn turn = new Turn(1, "d4", "d5");
        historia.addTurn(turn);
        turn = new Turn(2, "c4", "e5");
        historia.addTurn(turn);
        turn = new Turn(3, "xe5");
        historia.addTurn(turn);
        String vertaus = "1. d4 d5\n"
                + "2. c4 e5\n"
                + "3. xe5";
        for (int i = 0; i < vertaus.length(); i++) {
            assertEquals(vertaus.charAt(i), historia.toString().charAt(i));
        }
        assertEquals(vertaus.length(), historia.toString().length());
        assertEquals(historia.toString(), vertaus);
    }

    @Test
    public void parseStringTest() {
        String vertaus = "1. d4 d5\n"
                + "2. c4 e5\n"
                + "3. xe5";
        TurnHistory historia = new TurnHistory(vertaus);

        for (int i = 0; i < vertaus.length(); i++) {
            assertEquals(vertaus.charAt(i), historia.toString().charAt(i));
        }
        assertEquals(vertaus.length(), historia.toString().length());
        assertEquals(historia.toString(), vertaus);
    }

    @Test
    public void parseStringTestNoNewLine() {
        String vertaus = "1. d4 d5\n"
                + "2. c4 e5\n"
                + "3. xe5";
        TurnHistory historia = new TurnHistory("1. d4 d5 "
                + "2. c4 e5 "
                + "3. xe5");

        for (int i = 0; i < vertaus.length(); i++) {
            assertEquals(vertaus.charAt(i), historia.toString().charAt(i));
        }
        assertEquals(vertaus.length(), historia.toString().length());
        assertEquals(historia.toString(), vertaus);
    }

    @Test
    public void jatkaHistoriaaTest() {
        TurnHistory historia = new TurnHistory("1. d4 d5 "
                + "2. c4 e5 "
                + "3. xe5");
        String vertaus = "1. d4 d5\n"
                + "2. c4 e5\n"
                + "3. xe5 h5";
        historia.jatkaVuoroHistoriaa("h5");
        for (int i = 0; i < vertaus.length(); i++) {
            assertEquals(vertaus.charAt(i), historia.toString().charAt(i));
        }
        assertEquals(vertaus.length(), historia.toString().length());
        assertEquals(historia.toString(), vertaus);
        
        historia = new TurnHistory("1. d4 d5 "
                + "2. c4 e5 "
                + "3. xe5");
        vertaus = "1. d4 d5\n"
                + "2. c4 e5\n"
                + "3. xe5 h5\n"
                + "4. h4";
        historia.jatkaVuoroHistoriaa("h5");
        historia.jatkaVuoroHistoriaa("h4");
        for (int i = 0; i < vertaus.length(); i++) {
            assertEquals(vertaus.charAt(i), historia.toString().charAt(i));
        }
        assertEquals(vertaus.length(), historia.toString().length());
        assertEquals(historia.toString(), vertaus);
    }

    @Test
    public void removeAfterTest() {
        TurnHistory historia = new TurnHistory("1. d4 d5 "
                + "2. c4 e5 "
                + "3. xe5");
        String vertaus = "1. d4 d5\n"
                + "2. c4 e5\n"
                + "3. xe5 h5";
        historia.jatkaVuoroHistoriaa("h5");
        for (int i = 0; i < vertaus.length(); i++) {
            assertEquals(vertaus.charAt(i), historia.toString().charAt(i));
        }
        assertEquals(vertaus.length(), historia.toString().length());
        assertEquals(historia.toString(), vertaus);

        vertaus = "1. d4 d5\n"
                + "2. c4 e5";
        historia.removeAfter(2);
        assertEquals(vertaus.length(), historia.toString().length());
        for (int i = 0; i < vertaus.length(); i++) {
            assertEquals(vertaus.charAt(i), historia.toString().charAt(i));
        }
        assertEquals(vertaus.length(), historia.toString().length());
        assertEquals(historia.toString(), vertaus);
        
        assertEquals(historia.getVuoroNumero(), 3);
        historia.removeAfter(0);
        assertEquals(historia.getVuoroNumero(), 1);
    }
}
