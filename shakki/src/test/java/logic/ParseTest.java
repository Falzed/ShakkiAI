package logic;

import components.Lauta;
import components.Nappula;
import components.*;
import org.junit.Test;
import logic.liikkuminen.Liikkuminen;
import org.junit.Assert;
import static org.junit.Assert.*;

public class ParseTest {

    @Test
    public void stuff() {
        //Standardi setup
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = new int[2];
        variants.Standard.setUp(lauta);

        System.out.print("1. d4");
        Nappula.Puoli vuoro = Nappula.Puoli.VALKOINEN;

        int[][] startEndPoints = new int[2][2];
        int[] startPoint = {3, 1};
        int[] endPoint = {3, 3};
        startEndPoints[0] = startPoint;
        startEndPoints[1] = endPoint;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertTrue(logic.parser.Parser.parseCommand("d4", vuoro, lauta).getCoordinates()[i][j] == startEndPoints[i][j]);
            }
        }
        Liikkuminen.koitaSiirtya(lauta.getNappula(startPoint), endPoint, lauta);

        System.out.println(" d6");
        vuoro = Nappula.Puoli.MUSTA;
        startPoint[0] = 3;
        startPoint[1] = 6;
        endPoint[0] = 3;
        endPoint[1] = 5;
        startEndPoints[0] = startPoint;
        startEndPoints[1] = endPoint;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertTrue(logic.parser.Parser.parseCommand("d6", vuoro, lauta).getCoordinates()[i][j] == startEndPoints[i][j]);
            }
        }
        Liikkuminen.koitaSiirtya(lauta.getNappula(startPoint), endPoint, lauta);

        System.out.print("2. c4");
        vuoro = Nappula.Puoli.VALKOINEN;
        startPoint[0] = 2;
        startPoint[1] = 1;
        endPoint[0] = 2;
        endPoint[1] = 3;
        startEndPoints[0] = startPoint;
        startEndPoints[1] = endPoint;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertTrue(logic.parser.Parser.parseCommand("c4", vuoro, lauta).getCoordinates()[i][j] == startEndPoints[i][j]);
            }
        }
        Liikkuminen.koitaSiirtya(lauta.getNappula(startPoint), endPoint, lauta);

        System.out.println(" e5");
        vuoro = Nappula.Puoli.MUSTA;
        startPoint[0] = 4;
        startPoint[1] = 6;
        endPoint[0] = 4;
        endPoint[1] = 4;
        startEndPoints[0] = startPoint;
        startEndPoints[1] = endPoint;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertTrue(logic.parser.Parser.parseCommand("e5", vuoro, lauta).getCoordinates()[i][j] == startEndPoints[i][j]);
            }
        }
        Liikkuminen.koitaSiirtya(lauta.getNappula(startPoint), endPoint, lauta);

        System.out.print("3. xe5");
        vuoro = Nappula.Puoli.VALKOINEN;
        startPoint[0] = 3;
        startPoint[1] = 3;
        endPoint[0] = 4;
        endPoint[1] = 4;
        startEndPoints[0] = startPoint;
        startEndPoints[1] = endPoint;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertTrue(logic.parser.Parser.parseCommand("xe5", vuoro, lauta).getCoordinates()[i][j] == startEndPoints[i][j]);
            }
        }
        Liikkuminen.koitaSiirtya(lauta.getNappula(startPoint), endPoint, lauta);
    }

    @Test
    public void upseeriTest() {
        //Standardi setup
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = new int[2];
        variants.Standard.setUp(lauta);

        Nappula.Puoli vuoro = Nappula.Puoli.VALKOINEN;

        int[][] startEndPoints = new int[2][2];
        int[] startPoint = {1, 0};
        int[] endPoint = {2, 2};
        startEndPoints[0] = startPoint;
        startEndPoints[1] = endPoint;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertTrue(logic.parser.Parser.parseCommand("Nc3", vuoro, lauta).getCoordinates()[i][j] == startEndPoints[i][j]);
            }
        }
        Liikkuminen.koitaSiirtya(lauta.getNappula(startPoint), endPoint, lauta);

        vuoro = Nappula.Puoli.MUSTA;
        startPoint[0] = 1;
        startPoint[1] = 7;
        endPoint[0] = 2;
        endPoint[1] = 4;
        startEndPoints[0] = startPoint;
        startEndPoints[1] = endPoint;
        assertFalse(logic.parser.Parser.parseCommand("Nc5", vuoro, lauta).getError().isEmpty());
    }

    @Test
    public void komentoEiYksikasitteinen() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = new int[2];
        koordinaatit[0] = 4;
        koordinaatit[1] = 4;
        Torni torni = new Torni("valkoinen");
        Torni torni2 = new Torni("valkoinen");
        logic.LaudanMuutokset.aseta(torni, koordinaatit, lauta);
        koordinaatit[0] = 4;
        koordinaatit[1] = 7;
        logic.LaudanMuutokset.aseta(torni2, koordinaatit, lauta);
        assertFalse(logic.parser.Parser.parseCommand("Re6", Nappula.Puoli.VALKOINEN, lauta).getError().isEmpty());
    }

    @Test
    public void a2a4Test() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = new int[2];
        koordinaatit[0] = 4;
        koordinaatit[1] = 4;
        Sotilas sotilas = new Sotilas("valkoinen");
        logic.LaudanMuutokset.aseta(sotilas, koordinaatit, lauta);
        int[] startPoints = {0, 1};
        int[] endPoints = {0, 3};
        int[][] startEndPoints = {startPoints, endPoints};
//        assertTrue(logic.parser.Parser.parseCommand("a2-a4", Nappula.Puoli.VALKOINEN, lauta) == startEndPoints);
        assertTrue(logic.parser.Parser.parseCommand("a2-a4", Nappula.Puoli.VALKOINEN, lauta).getCoordinates()[0][0] == 0);
        assertTrue(logic.parser.Parser.parseCommand("a2-a4", Nappula.Puoli.VALKOINEN, lauta).getCoordinates()[0][1] == 1);
        assertTrue(logic.parser.Parser.parseCommand("a2-a4", Nappula.Puoli.VALKOINEN, lauta).getCoordinates()[1][0] == 0);
        assertTrue(logic.parser.Parser.parseCommand("a2-a4", Nappula.Puoli.VALKOINEN, lauta).getCoordinates()[1][1] == 3);
    }

    @Test
    public void eiLaudallaTest() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = new int[2];
        koordinaatit[0] = 4;
        koordinaatit[1] = 4;
        Sotilas sotilas = new Sotilas("valkoinen");
        logic.LaudanMuutokset.aseta(sotilas, koordinaatit, lauta);
//        assertTrue(logic.parser.Parser.parseCommand("a2-a4", Nappula.Puoli.VALKOINEN, lauta) == startEndPoints);
        assertFalse(logic.parser.Parser.parseCommand("z2-a4", Nappula.Puoli.VALKOINEN, lauta).getError().isEmpty());
    }
    
    @Test
    public void tornitusEiKuningastaTest() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = new int[2];
        koordinaatit[0] = 4;
        koordinaatit[1] = 4;
        Sotilas sotilas = new Sotilas("valkoinen");
        logic.LaudanMuutokset.aseta(sotilas, koordinaatit, lauta);
//        assertTrue(logic.parser.Parser.parseCommand("a2-a4", Nappula.Puoli.VALKOINEN, lauta) == startEndPoints);
        assertFalse(logic.parser.Parser.parseCommand("0-0", Nappula.Puoli.VALKOINEN, lauta).getError().isEmpty());
        assertFalse(logic.parser.Parser.parseCommand("0-0-0", Nappula.Puoli.VALKOINEN, lauta).getError().isEmpty());
    }
}
