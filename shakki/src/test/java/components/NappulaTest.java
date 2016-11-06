package components;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import logic.liikkuminen.Liikkuminen;

public class NappulaTest {

    @Test
    public void kopiointiTest() {
        Nappula nappula = new Nappula("valkoinen");
        Nappula kopio = nappula.kopioi();
        assertEquals(nappula.getPuoli(), kopio.getPuoli());
    }   
    
//    @Test
//    public void konstruktoriTest() {
//        Nappula nappula = new Nappula("valkoinen");
//        Nappula kopio = new Nappula(nappula, "musta");
//        assertEquals(nappula.getPuoli(), Nappula.Puoli.VALKOINEN);
//        assertEquals(kopio.getPuoli(), Nappula.Puoli.MUSTA);
//        assertEquals(nappula.getPuoliString(), "valkoinen");
//        assertEquals(kopio.getPuoliString(), "musta");
//    }   
}
