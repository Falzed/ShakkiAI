package util;

import java.util.Scanner;
import components.*;
import logic.LaudanMuutokset;
import logic.parser.ParserReturn;

/**
 * Luokka on vain main-metodi minne tulee välillä työnnettyä bugista koodia
 * ajettavaksi.
 *
 * @author Oskari Kulmala
 */
public class ParseDebugger {

    public static void main(String[] args) {
        String line;
        Scanner lukija = new Scanner(System.in);
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = new int[2];
        koordinaatit[0] = 4;
        koordinaatit[1] = 4;
        Torni torni = new Torni("valkoinen");
        Torni torni2 = new Torni("valkoinen");
        LaudanMuutokset.aseta(torni, koordinaatit, lauta);
        koordinaatit[0] = 4;
        koordinaatit[1] = 7;
        LaudanMuutokset.aseta(torni2, koordinaatit, lauta);
        Nappula.Puoli vuoro = Nappula.Puoli.VALKOINEN;
        while (true) {
            line = lukija.nextLine();
            if (line.isEmpty()) {
                break;
            }
            if (line.equals("algebraic")) {
                line = lukija.nextLine();
                int[] table = logic.parser.Parser.parseAlgebraic(line);
                System.out.println(table[0] + "," + table[1]);
            }
            if (line.equals("musta")) {
                vuoro = Nappula.Puoli.MUSTA;
            }
            if (line.equals("valkoinen")) {
                vuoro = Nappula.Puoli.VALKOINEN;
            }
            ParserReturn parserReturn = logic.parser.Parser.parseCommand(line, vuoro, lauta);
            
            System.out.println(parserReturn);

            if (parserReturn.getCoordinates() == null || parserReturn.getCoordinates()[0] == null) {
                System.out.println("table null");
                System.out.println("table.length " + parserReturn.getCoordinates().length);
                System.out.println("table[0].length " + parserReturn.getCoordinates()[0].length);
            } else {
                System.out.println(parserReturn.getCoordinates()[0][0] + "," + parserReturn.getCoordinates()[0][1] + "-" + parserReturn.getCoordinates()[1][0] + "," + parserReturn.getCoordinates()[1][1]);
            }
        }
    }
}
