package AI;
import components.*;
import logic.liikkuminen.Liikkuminen;
import logic.parser.Parser;
import variants.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class HeuristicsTest {
    @Test
    public void testSotilasMuunnos() {
        Tekoaly ai = new Tekoaly(Nappula.Puoli.VALKOINEN);
        Variant standard = new Standard();
        standard.setUp();
        Lauta lauta = standard.getLauta();
        assertEquals(ai.sotilasMuunnokset(0, new Solmu(lauta, null, Nappula.Puoli.VALKOINEN, null)), 0);
        Liikkuminen.koitaSiirtya(Parser.parseAlgebraic("d2"), Parser.parseAlgebraic("d4"), lauta, null);
        assertEquals(ai.sotilasMuunnokset(0, new Solmu(lauta, null, Nappula.Puoli.VALKOINEN, null)), 52);
        Liikkuminen.koitaSiirtya(Parser.parseAlgebraic("d7"), Parser.parseAlgebraic("d5"), lauta, null);
        assertEquals(ai.sotilasMuunnokset(0, new Solmu(lauta, null, Nappula.Puoli.VALKOINEN, null)), 0);
    }
    
    @Test
    public void testSotilasMuunnos2() {
        Tekoaly ai = new Tekoaly(Nappula.Puoli.VALKOINEN);
        Variant standard = new Standard();
        standard.setUp();
        Lauta lauta = standard.getLauta();
        assertEquals(ai.sotilasMuunnokset(0, new Solmu(lauta, null, Nappula.Puoli.VALKOINEN, null)), 0);
        Liikkuminen.koitaSiirtya(Parser.parseAlgebraic("d7"), Parser.parseAlgebraic("d5"), lauta, null);
        assertEquals(ai.sotilasMuunnokset(0, new Solmu(lauta, null, Nappula.Puoli.VALKOINEN, null)), -52);
    }
    
    @Test
    public void testBasicHeuristic() {
        Tekoaly ai = new Tekoaly(Nappula.Puoli.VALKOINEN);
        Variant standard = new Standard();
        standard.setUp();
        Lauta lauta = standard.getLauta();
        assertEquals(ai.heuristiikka(new Solmu(lauta, null, Nappula.Puoli.VALKOINEN, null)), 0);
        Liikkuminen.koitaSiirtya(Parser.parseAlgebraic("d2"), Parser.parseAlgebraic("d4"), lauta, null);
        assertEquals(ai.heuristiikka(new Solmu(lauta, null, Nappula.Puoli.VALKOINEN, null)), 52);
        Liikkuminen.koitaSiirtya(Parser.parseAlgebraic("d7"), Parser.parseAlgebraic("d5"), lauta, null);
        assertEquals(ai.heuristiikka(new Solmu(lauta, null, Nappula.Puoli.VALKOINEN, null)), 0);
    }
}
