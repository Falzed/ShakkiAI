package AI;
import components.*;
import logic.liikkuminen.Liikkuminen;
import logic.parser.Parser;
import variants.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class AlphaBetaTest {
    @Test
    public void asdfTest() {
        Tekoaly ai = new Tekoaly(Nappula.Puoli.VALKOINEN);
        Variant standard = new Standard();
        standard.setUp();
        Lauta lauta = standard.getLauta();
        Solmu solmu = new Solmu(lauta, null, Nappula.Puoli.VALKOINEN, null);
        System.out.println(ai.alphaBetaNormal(solmu, 2, Integer.MIN_VALUE, Integer.MAX_VALUE, true));
        
    }
    
}
