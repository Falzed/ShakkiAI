package variants;

import logic.*;
import fairy.*;
import org.junit.Test;
import org.junit.Assert;
import components.Nappula;
import java.io.*;
import java.util.Arrays;
import logic.parser.Parser;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;

public class FairyMovementTest {

    @Test
    public void torniHyppyTest() {
        File file = new File("src/main/resources/testXml.xml");
        Game peli = new Game(new FairyVariant(file));
        peli.suoritaKomento("a1-a3");
        peli.suoritaKomento("a8-a1");
        assertTrue(peli.getLauta().getNappula("a3").getNimi().equals("HyppaavaTorni"));
        assertTrue(peli.getLauta().getNappula("a3").getPuoli() == Nappula.Puoli.VALKOINEN);
        assertTrue(peli.getLauta().getNappula("a1").getNimi().equals("HyppaavaTorni"));
        assertTrue(peli.getLauta().getNappula("a1").getPuoli() == Nappula.Puoli.MUSTA);
        peli.suoritaKomento("a3-g3");
        peli.suoritaKomento("a1-h1");
        peli.suoritaKomento("g3-c3");
        assertTrue(peli.getLauta().getNappula("c3").getNimi().equals("HyppaavaTorni"));
        assertTrue(peli.getLauta().getNappula("c3").getPuoli() == Nappula.Puoli.VALKOINEN);
        assertTrue(peli.getLauta().getNappula("h1").getNimi().equals("HyppaavaTorni"));
        assertTrue(peli.getLauta().getNappula("h1").getPuoli() == Nappula.Puoli.MUSTA);
    }
    
    @Test
    public void bigTest() {
        File file = new File("src/main/resources/testing.xml");
        Game peli = new Game(new FairyVariant(file));
        peli.suoritaKomento("a1-d4");
        peli.suoritaKomento("a8-a4");
        assertTrue(peli.getLauta().getNappula("d4").getNimi().equals("HyppaavaLahetti"));
        assertTrue(peli.getLauta().getNappula("d4").getPuoli() == Nappula.Puoli.VALKOINEN);
        assertTrue(peli.getLauta().getNappula("a4").getNimi().equals("OutoKuningatar"));
        assertTrue(peli.getLauta().getNappula("a4").getPuoli() == Nappula.Puoli.MUSTA);
        peli.suoritaKomento("d4-c3");
        peli.suoritaKomento("a4-a6");
        assertTrue(peli.getLauta().getNappula("c3").getNimi().equals("HyppaavaLahetti"));
        assertTrue(peli.getLauta().getNappula("c3").getPuoli() == Nappula.Puoli.VALKOINEN);
        assertTrue(peli.getLauta().getNappula("a6").getNimi().equals("OutoKuningatar"));
        assertTrue(peli.getLauta().getNappula("a6").getPuoli() == Nappula.Puoli.MUSTA);
        peli.suoritaKomento("c3-e1");
        peli.suoritaKomento("a6-g6");
        assertTrue(peli.getLauta().getNappula("e1").getNimi().equals("HyppaavaLahetti"));
        assertTrue(peli.getLauta().getNappula("e1").getPuoli() == Nappula.Puoli.VALKOINEN);
        assertTrue(peli.getLauta().getNappula("g6").getNimi().equals("OutoKuningatar"));
        assertTrue(peli.getLauta().getNappula("g6").getPuoli() == Nappula.Puoli.MUSTA);
        peli.suoritaKomento("e1-a5");
        peli.suoritaKomento("g6-c6");
        assertTrue(peli.getLauta().getNappula("a5").getNimi().equals("HyppaavaLahetti"));
        assertTrue(peli.getLauta().getNappula("a5").getPuoli() == Nappula.Puoli.VALKOINEN);
        assertTrue(peli.getLauta().getNappula("c6").getNimi().equals("OutoKuningatar"));
        assertTrue(peli.getLauta().getNappula("c6").getPuoli() == Nappula.Puoli.MUSTA);
        peli.suoritaKomento("a5-c7");
        peli.suoritaKomento("b8-b3");
        assertTrue(peli.getLauta().getNappula("c7").getNimi().equals("HyppaavaLahetti"));
        assertTrue(peli.getLauta().getNappula("c7").getPuoli() == Nappula.Puoli.VALKOINEN);
        assertTrue(peli.getLauta().getNappula("b3").getNimi().equals("OutoKuningatar2"));
        assertTrue(peli.getLauta().getNappula("b3").getPuoli() == Nappula.Puoli.MUSTA);
        peli.suoritaKomento("c7-a5");
        peli.suoritaKomento("b3-b8");
        assertTrue(peli.getLauta().getNappula("a5").getNimi().equals("HyppaavaLahetti"));
        assertTrue(peli.getLauta().getNappula("a5").getPuoli() == Nappula.Puoli.VALKOINEN);
        assertTrue(peli.getLauta().getNappula("b8").getNimi().equals("OutoKuningatar2"));
        assertTrue(peli.getLauta().getNappula("b8").getPuoli() == Nappula.Puoli.MUSTA);
        peli.suoritaKomento("a5-c7");
        peli.suoritaKomento("b8-h8");
        assertTrue(peli.getLauta().getNappula("c7").getNimi().equals("HyppaavaLahetti"));
        assertTrue(peli.getLauta().getNappula("c7").getPuoli() == Nappula.Puoli.VALKOINEN);
        assertTrue(peli.getLauta().getNappula("h8").getNimi().equals("OutoKuningatar2"));
        assertTrue(peli.getLauta().getNappula("h8").getPuoli() == Nappula.Puoli.MUSTA);
        peli.suoritaKomento("c7-a5");
        peli.suoritaKomento("h8-e8");
        assertTrue(peli.getLauta().getNappula("a5").getNimi().equals("HyppaavaLahetti"));
        assertTrue(peli.getLauta().getNappula("a5").getPuoli() == Nappula.Puoli.VALKOINEN);
        assertTrue(peli.getLauta().getNappula("e8").getNimi().equals("OutoKuningatar2"));
        assertTrue(peli.getLauta().getNappula("e8").getPuoli() == Nappula.Puoli.MUSTA);
        peli.suoritaKomento("a5-c7");
        peli.suoritaKomento("e8-g6");
        assertTrue(peli.getLauta().getNappula("c7").getNimi().equals("HyppaavaLahetti"));
        assertTrue(peli.getLauta().getNappula("c7").getPuoli() == Nappula.Puoli.VALKOINEN);
        assertTrue(peli.getLauta().getNappula("g6").getNimi().equals("OutoKuningatar2"));
        assertTrue(peli.getLauta().getNappula("g6").getPuoli() == Nappula.Puoli.MUSTA);
        peli.suoritaKomento("c7-a5");
        peli.suoritaKomento("g6-e4");
        assertTrue(peli.getLauta().getNappula("a5").getNimi().equals("HyppaavaLahetti"));
        assertTrue(peli.getLauta().getNappula("a5").getPuoli() == Nappula.Puoli.VALKOINEN);
        assertTrue(peli.getLauta().getNappula("e4").getNimi().equals("OutoKuningatar2"));
        assertTrue(peli.getLauta().getNappula("e4").getPuoli() == Nappula.Puoli.MUSTA);
        peli.suoritaKomento("a5-c7");
        peli.suoritaKomento("e4-d5");
        assertTrue(peli.getLauta().getNappula("c7").getNimi().equals("HyppaavaLahetti"));
        assertTrue(peli.getLauta().getNappula("c7").getPuoli() == Nappula.Puoli.VALKOINEN);
        assertTrue(peli.getLauta().getNappula("d5").getNimi().equals("OutoKuningatar2"));
        assertTrue(peli.getLauta().getNappula("d5").getPuoli() == Nappula.Puoli.MUSTA);
        peli.suoritaKomento("c7-a5");
        peli.suoritaKomento("d5-e6");
        assertTrue(peli.getLauta().getNappula("a5").getNimi().equals("HyppaavaLahetti"));
        assertTrue(peli.getLauta().getNappula("a5").getPuoli() == Nappula.Puoli.VALKOINEN);
        assertTrue(peli.getLauta().getNappula("e6").getNimi().equals("OutoKuningatar2"));
        assertTrue(peli.getLauta().getNappula("e6").getPuoli() == Nappula.Puoli.MUSTA);
        
        peli.suoritaKomento("a5-c7");
        peli.suoritaKomento("c6-f3");
        assertTrue(peli.getLauta().getNappula("c7").getNimi().equals("HyppaavaLahetti"));
        assertTrue(peli.getLauta().getNappula("c7").getPuoli() == Nappula.Puoli.VALKOINEN);
        assertTrue(peli.getLauta().getNappula("f3").getNimi().equals("OutoKuningatar"));
        assertTrue(peli.getLauta().getNappula("f3").getPuoli() == Nappula.Puoli.MUSTA);
        peli.suoritaKomento("c7-a5");
        peli.suoritaKomento("f3-d1");
        assertTrue(peli.getLauta().getNappula("a5").getNimi().equals("HyppaavaLahetti"));
        assertTrue(peli.getLauta().getNappula("a5").getPuoli() == Nappula.Puoli.VALKOINEN);
        assertTrue(peli.getLauta().getNappula("d1").getNimi().equals("OutoKuningatar"));
        assertTrue(peli.getLauta().getNappula("d1").getPuoli() == Nappula.Puoli.MUSTA);
        peli.suoritaKomento("a5-c7");
        peli.suoritaKomento("d1-b3");
        assertTrue(peli.getLauta().getNappula("c7").getNimi().equals("HyppaavaLahetti"));
        assertTrue(peli.getLauta().getNappula("c7").getPuoli() == Nappula.Puoli.VALKOINEN);
        assertTrue(peli.getLauta().getNappula("b3").getNimi().equals("OutoKuningatar"));
        assertTrue(peli.getLauta().getNappula("b3").getPuoli() == Nappula.Puoli.MUSTA);
        peli.suoritaKomento("c7-a5");
        peli.suoritaKomento("b3-d5");
        assertTrue(peli.getLauta().getNappula("a5").getNimi().equals("HyppaavaLahetti"));
        assertTrue(peli.getLauta().getNappula("a5").getPuoli() == Nappula.Puoli.VALKOINEN);
        assertTrue(peli.getLauta().getNappula("d5").getNimi().equals("OutoKuningatar"));
        assertTrue(peli.getLauta().getNappula("d5").getPuoli() == Nappula.Puoli.MUSTA);
    }

    
    @Test
    public void variantinVaihto() {
        File file = new File("src/main/resources/dunsanysChess.xml");
        Game peli = new Game(new FairyVariant(file));
        assertTrue(peli.getLauta().getNappula("b1").getNimi().equals("Sotilas"));
        File file2 = new File("src/main/resources/chargeOfTheLightBrigade.xml");
        peli.vaihdaVariantti(new FairyVariant(file2));
        assertTrue(peli.getLauta().getNappula("b1").getNimi().equals("Kuningatar"));
    }
}
