package logic;

import variants.*;
import org.junit.Test;
import org.junit.Assert;
import components.Nappula;
import java.util.Arrays;
import logic.parser.Parser;
import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void komentoTest() {
        Game peli = new Game(new Standard());
        peli.suoritaKomento("d4");
        assertTrue(peli.getLauta().getNappula("d4").getKoordinaatit()[0] == 3);
        assertTrue(peli.getLauta().getNappula("d4").getKoordinaatit()[1] == 3);
        assertTrue(peli.getLauta().getNappula("d4").getNimi().equals("Sotilas"));
        assertTrue(peli.getLauta().getNappula("d4").getPuoli() == Nappula.Puoli.VALKOINEN);
        assertTrue(peli.getTurnHistory().getViimeinenVuoro().toString().equals("1. d4"));
        assertFalse(peli.tarkistaKorotus(Parser.parseAlgebraic("d4")));
    }

    @Test
    public void toisenNappulanSiirtoTest() {
        Game peli = new Game(new Standard());
        peli.suoritaKomento("d7-d5");
        assertTrue(peli.getLauta().getNappula("d7").getKoordinaatit()[0] == 3);
        assertTrue(peli.getLauta().getNappula("d7").getKoordinaatit()[1] == 6);
        assertTrue(peli.getLauta().getNappula("d7").getNimi().equals("Sotilas"));
        assertTrue(peli.getLauta().getNappula("d7").getPuoli() == Nappula.Puoli.MUSTA);
    }

    @Test
    public void historiaTest() {
        Game peli = new Game(new Standard());
        peli.applyHistory("1. e4 e5\n"
                + "2. Nf3 Nf6\n"
                + "3. d4 xd4\n"
                + "4. e5 Ne4\n"
                + "5. Qd4 d5");
        assertTrue(peli.getLauta().getNappula("d4").getKoordinaatit()[0] == 3);
        assertTrue(peli.getLauta().getNappula("d4").getKoordinaatit()[1] == 3);
        assertTrue(peli.getLauta().getNappula("d4").getNimi().equals("Kuningatar"));
        assertTrue(peli.getLauta().getNappula("d4").getPuoli() == Nappula.Puoli.VALKOINEN);
    }

    @Test
    public void enPassantTest() {
        Game peli = new Game(new Standard());
        peli.applyHistory("1. e4 e5\n"
                + "2. Nf3 Nf6\n"
                + "3. d4 xd4\n"
                + "4. e5 Ne4\n"
                + "5. Qd4 d5");
        peli.suoritaKomento("xd6");
        assertTrue(peli.getLauta().getNappula("d6").getKoordinaatit()[0] == 3);
        assertTrue(peli.getLauta().getNappula("d6").getKoordinaatit()[1] == 5);
        assertTrue(peli.getLauta().getNappula("d6").getNimi().equals("Sotilas"));
        assertTrue(peli.getLauta().getNappula("d6").getPuoli() == Nappula.Puoli.VALKOINEN);

        peli.applyHistory("1. e4 e5\n"
                + "2. Nf3 Nf6\n"
                + "3. d4 xd4\n"
                + "4. e5 Ne4\n"
                + "5. Qd4 f5");
        peli.suoritaKomento("xf6");
        assertTrue(peli.getLauta().getNappula("f6").getKoordinaatit()[0] == 5);
        assertTrue(peli.getLauta().getNappula("f6").getKoordinaatit()[1] == 5);
        assertTrue(peli.getLauta().getNappula("f6").getNimi().equals("Sotilas"));
        assertTrue(peli.getLauta().getNappula("f6").getPuoli() == Nappula.Puoli.VALKOINEN);
    }

    @Test
    public void enPassantTestMusta() {
        Game peli = new Game(new Standard());
        peli.applyHistory("1. h4 d5\n"
                + "2. a4 d4\n"
                + "3. e4 xe3");
        assertTrue(peli.getLauta().getNappula("e3").getKoordinaatit()[0] == 4);
        assertTrue(peli.getLauta().getNappula("e3").getKoordinaatit()[1] == 2);
        assertTrue(peli.getLauta().getNappula("e3").getNimi().equals("Sotilas"));
        assertTrue(peli.getLauta().getNappula("e3").getPuoli() == Nappula.Puoli.MUSTA);

        peli.applyHistory("1. h4 d5\n"
                + "2. a4 d4\n"
                + "3. c4 xc3");
        assertTrue(peli.getLauta().getNappula("c3").getKoordinaatit()[0] == 2);
        assertTrue(peli.getLauta().getNappula("c3").getKoordinaatit()[1] == 2);
        assertTrue(peli.getLauta().getNappula("c3").getNimi().equals("Sotilas"));
        assertTrue(peli.getLauta().getNappula("c3").getPuoli() == Nappula.Puoli.MUSTA);
        peli.clearHistory();
        assertTrue(peli.getTurnHistory().toString().equals(""));
    }

    @Test
    public void enKorotusTest() {
        Game peli = new Game(new Standard());
        peli.applyHistory("1. d4 d5\n"
                + "2. c4 e5\n"
                + "3. xe5 d4\n"
                + "4. e3 Bb4\n"
                + "5. Bd2 xe3\n"
                + "6. Bb4 xf2\n"
                + "7. Ke2 xg1");
        assertTrue(peli.tarkistaKorotus(Parser.parseAlgebraic("g1")));
        peli.korota(Parser.parseAlgebraic("g1"), "Kuningatar");
        assertTrue(peli.getLauta().getNappula("g1").getKoordinaatit()[0] == 6);
        assertTrue(peli.getLauta().getNappula("g1").getKoordinaatit()[1] == 0);
        assertTrue(peli.getLauta().getNappula("g1").getNimi().equals("Kuningatar"));
        assertTrue(peli.getLauta().getNappula("g1").getPuoli() == Nappula.Puoli.MUSTA);

        peli.applyHistory("1. d4 d5\n"
                + "2. c4 e5\n"
                + "3. xe5 d4\n"
                + "4. e3 Bb4\n"
                + "5. Bd2 xe3\n"
                + "6. Bb4 xf2\n"
                + "7. Ke2 h5\n"
                + "8. Qe1 xe1");
        assertTrue(peli.tarkistaKorotus(Parser.parseAlgebraic("e1")));
        peli.korota(Parser.parseAlgebraic("e1"), "Kuningatar");
        assertTrue(peli.getLauta().getNappula("e1").getKoordinaatit()[0] == 4);
        assertTrue(peli.getLauta().getNappula("e1").getKoordinaatit()[1] == 0);
        assertTrue(peli.getLauta().getNappula("e1").getNimi().equals("Kuningatar"));
        assertTrue(peli.getLauta().getNappula("e1").getPuoli() == Nappula.Puoli.MUSTA);
    }

    @Test
    public void eiLaudallaTest() {
        Game peli = new Game(new Standard());
        assertFalse(peli.suoritaKomento("z4").getError().isEmpty());
    }

    @Test
    public void tornitusTest() {
        Game peli = new Game(new Standard());
        peli.applyHistory("1. e4 e5\n"
                + "2. Nf3 Nc6\n"
                + "3. Bc4 Nf6");
        peli.suoritaKomento("0-0");
        assertTrue(peli.getLauta().getNappula("g1").getKoordinaatit()[0] == 6);
        assertTrue(peli.getLauta().getNappula("g1").getKoordinaatit()[1] == 0);
        assertTrue(peli.getLauta().getNappula("g1").getNimi().equals("Kuningas"));
        assertTrue(peli.getLauta().getNappula("g1").getPuoli() == Nappula.Puoli.VALKOINEN);
        assertTrue(peli.getLauta().getNappula("f1").getKoordinaatit()[0] == 5);
        assertTrue(peli.getLauta().getNappula("f1").getKoordinaatit()[1] == 0);
        assertTrue(peli.getLauta().getNappula("f1").getNimi().equals("Torni"));
        assertTrue(peli.getLauta().getNappula("f1").getPuoli() == Nappula.Puoli.VALKOINEN);
    }

    @Test
    public void tunnistaaMatin() {
        Game peli = new Game(new Standard());
        peli.applyHistory("1. f3 e5\n"
                + "2. g4 Qh4");
        assertTrue(peli.tarkistaMatti());
    }

    @Test
    public void tunnistaaPatin() {
        Game peli = new Game(new Standard());
        peli.applyHistory("1. c4 h5\n"
                + "2. h4 a5\n"
                + "3. Qa4 Ra6\n"
                + "4. Qa5 a6-h6\n"
                + "5. Qc7 f6\n"
                + "6. Qd7 Kf7\n"
                + "7. Qb7 Qd3\n"
                + "8. Qb8 Qh7\n"
                + "9. Qc8 Kg6\n"
                + "10. Qe6");
        assertTrue(peli.tarkistaPatti());
    }
    
    
}
