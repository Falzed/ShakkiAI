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
import AI.*;
import variants.Variant;

/**
 * Luokka on vain main-metodi minne tulee välillä työnnettyä bugista koodia
 * ajettavaksi.
 *
 * @author Oskari Kulmala
 */
public class GenericDebugger {

    public static void main(String[] args) throws FileNotFoundException, IOException, SAXException, ParserConfigurationException {
        Tekoaly ai = new Tekoaly(Nappula.Puoli.VALKOINEN);
        Variant standard = new Standard();
        standard.setUp();
        Lauta lauta = standard.getLauta();
        Solmu solmu = new Solmu(lauta, null, Nappula.Puoli.VALKOINEN, null);
        int[][] komento = ai.seuraavaKomento(lauta, null);
        System.out.println(Arrays.toString(komento[0])+" to "+Arrays.toString(komento[1]));
//        Tekoaly ai = new Tekoaly(Nappula.Puoli.MUSTA);
//        Standard variantti = new Standard();
//        variantti.setUp();
//        Lauta lauta = variantti.getLauta();
////        logic.liikkuminen.Liikkuminen.koitaSiirtya(Parser.parseAlgebraic("d2"), Parser.parseAlgebraic("d4"), lauta, null);
//        logic.liikkuminen.Liikkuminen.koitaSiirtya(Parser.parseAlgebraic("d7"), Parser.parseAlgebraic("d5"), lauta, null);
//        
//        Solmu solmu = new Solmu(lauta, null, Nappula.Puoli.VALKOINEN, null);
//        System.out.println(ai.heuristiikka(solmu));

//        float kerroin = 4;
//        kerroin -= Math.abs((double) (8 - 1) / 2 - 3);
//        System.out.println(kerroin);
//        System.out.println((int) (100 * (1 - kerroin)));
//        for (int i = 0; i < 8; i++) {
//            float kerroin2 = 4;
//            kerroin2 -= Math.abs((double) (8 - 1) / 2 - i);
//            System.out.println(kerroin2);
//            System.out.println((int) (100 *  kerroin2));
//        }

    }
}
