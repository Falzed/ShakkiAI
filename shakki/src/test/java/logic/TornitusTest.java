package logic;
import variants.Standard;
import org.junit.Test;
import org.junit.Assert;
import components.*;
import logic.parser.Parser;
import static org.junit.Assert.*;

public class TornitusTest {
    @Test
    public void tornitaOikealleTest() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        LaudanMuutokset.aseta(new Kuningas("valkoinen"), "e1", lauta);
        LaudanMuutokset.aseta(new Torni("valkoinen"), "h1", lauta);
        assertTrue(Tornitus.voikoTornittaa(Parser.parseAlgebraic("e1"), Parser.parseAlgebraic("g1"), lauta, Nappula.Puoli.VALKOINEN));
        Tornitus.tornita(Parser.parseAlgebraic("e1"), Parser.parseAlgebraic("g1"), lauta);
        assertTrue(lauta.getNappula("g1").getKoordinaatit()[0] == 6);
        assertTrue(lauta.getNappula("g1").getKoordinaatit()[1] == 0);
        assertTrue(lauta.getNappula("g1").getNimi().equals("Kuningas"));
        assertTrue(lauta.getNappula("g1").getPuoli() == Nappula.Puoli.VALKOINEN);
        assertTrue(lauta.getNappula("f1").getKoordinaatit()[0] == 5);
        assertTrue(lauta.getNappula("f1").getKoordinaatit()[1] == 0);
        assertTrue(lauta.getNappula("f1").getNimi().equals("Torni"));
        assertTrue(lauta.getNappula("f1").getPuoli() == Nappula.Puoli.VALKOINEN);
    }
    @Test
    public void tornitaVasemmalleTest() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        LaudanMuutokset.aseta(new Kuningas("valkoinen"), "e1", lauta);
        LaudanMuutokset.aseta(new Torni("valkoinen"), "a1", lauta);
        assertTrue(Tornitus.voikoTornittaa(Parser.parseAlgebraic("e1"), Parser.parseAlgebraic("c1"), lauta, Nappula.Puoli.VALKOINEN));
        Tornitus.tornita(Parser.parseAlgebraic("e1"), Parser.parseAlgebraic("c1"), lauta);
        assertTrue(lauta.getNappula("c1").getKoordinaatit()[0] == 2);
        assertTrue(lauta.getNappula("c1").getKoordinaatit()[1] == 0);
        assertTrue(lauta.getNappula("c1").getNimi().equals("Kuningas"));
        assertTrue(lauta.getNappula("c1").getPuoli() == Nappula.Puoli.VALKOINEN);
        assertTrue(lauta.getNappula("d1").getKoordinaatit()[0] == 3);
        assertTrue(lauta.getNappula("d1").getKoordinaatit()[1] == 0);
        assertTrue(lauta.getNappula("d1").getNimi().equals("Torni"));
        assertTrue(lauta.getNappula("d1").getPuoli() == Nappula.Puoli.VALKOINEN);
    }
    
    @Test
    public void eiVoiTornittaaJosJotainValissa() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        LaudanMuutokset.aseta(new Kuningas("valkoinen"), "e1", lauta);
        LaudanMuutokset.aseta(new Torni("valkoinen"), "a1", lauta);
        LaudanMuutokset.aseta(new Kuningatar("valkoinen"), "d1", lauta);
        assertFalse(Tornitus.voikoTornittaa(Parser.parseAlgebraic("e1"), Parser.parseAlgebraic("c1"), lauta, Nappula.Puoli.VALKOINEN));
    }
    @Test
    public void eiVoiTornittaaJosRuutuValissaUhattu() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        LaudanMuutokset.aseta(new Kuningas("valkoinen"), "e1", lauta);
        LaudanMuutokset.aseta(new Torni("valkoinen"), "a1", lauta);
        LaudanMuutokset.aseta(new Kuningatar("musta"), "d8", lauta);
        assertFalse(Tornitus.voikoTornittaa(Parser.parseAlgebraic("e1"), Parser.parseAlgebraic("c1"), lauta, Nappula.Puoli.VALKOINEN));
    }
}
