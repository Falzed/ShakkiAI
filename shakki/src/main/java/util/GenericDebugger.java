package util;

import components.Kuningas;
import components.*;
import components.Torni;
import history.Turn;
import history.TurnHistory;
import logic.liikkuminen.Liikkuminen;
import logic.parser.*;
import logic.Shakitus;
import java.util.ArrayList;
import logic.Game;
import ui.UI;
import variants.Standard;
import fairy.*;
import java.io.*;
import java.util.Arrays;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.util.HashMap;

/**
 * Luokka on vain main-metodi minne tulee välillä työnnettyä bugista koodia ajettavaksi.
 * @author Oskari Kulmala
 */
public class GenericDebugger {
    public static void main(String[] args)throws FileNotFoundException, IOException, SAXException, ParserConfigurationException {
//        Lauta lauta = new Lauta();
//        lauta.alustaLauta();
//        int[] koordinaatit = new int[2];
//        koordinaatit[0] = 4;
//        koordinaatit[1] = 4;
//        Sotilas sotilas = new Sotilas("valkoinen");
//        logic.LaudanMuutokset.aseta(sotilas, koordinaatit, lauta);
//        ParserReturn parserRet = logic.parser.Parser.parseCommand("0-0-0", Nappula.Puoli.VALKOINEN, lauta);
//        System.out.println(parserRet);
//        int[][] startEndPoints = new int[2][2];
//        int[] start = {0,0};
//        int[] end = {1,1};
//        startEndPoints[0] = start;
//        startEndPoints[1] = end;
//        System.out.println(Parser.parseToAlgebraicCommand(startEndPoints));
//        ArrayList<String> lista = new ArrayList<>();
//        lista.add("TorniHyppy");
//        System.out.println(lista.contains("Torni"));


//        File file = new File("src/main/resources/chargeOfTheLightBrigade.xml");
//        File file = new File("src/main/resources/testing.xml");
//        Game peli = new Game(new FairyVariant(file));
//        peli.suoritaKomento("a1-a2");
//        System.out.println(peli.getLauta().getNappula("a1").getMerkki());
//        UI ui = new UI(peli);
//        System.out.println(peli.getLauta().getNappula("a1").getNimi());
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                ui.setVisible(true);
//                ui.updateUI();
//            }
//        });
        
        
//        Nappula.Puoli vuoro = Nappula.Puoli.VALKOINEN;
//        HashMap<String, Object> hash = new HashMap<>();
//        hash.put("valkoinenMerkki", 'T');
//        hash.put("notaatioMerkki", 'T');
//        hash.put("mustaMerkki", 't');
//        hash.put("nimi", "HyppaavaTorni");
//        
//        FairyPiece piece = new FairyPiece(hash, Nappula.Puoli.VALKOINEN);
//        System.out.println(piece.getNimi());
        
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
    }
}
