package history;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TurnTest {
    @Test
    public void toStringTest() {
        Turn vuoro = new Turn(1, "d4", "d5");
        assertTrue(vuoro.toString().equals("1. d4 d5"));
        assertTrue(vuoro.getWhiteMove().equals("d4"));
        assertTrue(vuoro.getBlackMove().equals("d5"));
        
        assertTrue(vuoro.isComplete());
        
        Turn vuoro2 = new Turn(1, "d4");
        assertTrue(vuoro2.toString().equals("1. d4"));
        assertTrue(vuoro2.getWhiteMove().equals("d4"));
        assertFalse(vuoro2.isComplete());
    }
    @Test
    public void parseStringTest() {
        Turn vuoro = new Turn("1. d4 d5");
        assertTrue(vuoro.toString().equals("1. d4 d5"));
        assertTrue(vuoro.getWhiteMove().equals("d4"));
        assertTrue(vuoro.getBlackMove().equals("d5"));
        
        assertTrue(vuoro.isComplete());
        
        Turn vuoro2 = new Turn("1. d4");
        assertTrue(vuoro2.toString().equals("1. d4"));
        assertTrue(vuoro2.getWhiteMove().equals("d4"));
        assertFalse(vuoro2.isComplete());
    }
}
