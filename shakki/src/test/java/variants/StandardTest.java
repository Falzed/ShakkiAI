package variants;

import static org.junit.Assert.*;
import org.junit.Test;
import components.Lauta;

public class StandardTest {

    @Test
    public void setUpTest() {
        Lauta lauta = new Lauta(8, 8);
        Standard.setUp(lauta);
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                int[] koordinaatit = {j, i};
                assertTrue(lauta.getNappula(koordinaatit).isEmpty());
            }
        }
        for (int j = 0; j < 8; j++) {
            int[] koordinaatit = {j, 1};
            assertFalse(lauta.getNappula(koordinaatit).isEmpty());
            assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u2659');
            int[] koordinaatit2 = {j, 6};
            assertFalse(lauta.getNappula(koordinaatit2).isEmpty());
            assertEquals(lauta.getNappula(koordinaatit2).getMerkki(), '\u265F');
        }
        int[] koordinaatit = {0, 0};
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u2656');
        koordinaatit[0]=7;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u2656');
        koordinaatit[1]=7;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u265C');
        koordinaatit[0]=0;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u265C');
        
        koordinaatit[0] = 1;
        koordinaatit[1]=0;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u2658');
        koordinaatit[0]=6;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u2658');
        koordinaatit[1]=7;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u265E');
        koordinaatit[0]=1;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u265E');
        
        koordinaatit[0] = 2;
        koordinaatit[1]=0;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u2657');
        koordinaatit[0]=5;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u2657');
        koordinaatit[1]=7;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u265D');
        koordinaatit[0]=2;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u265D');
        
        koordinaatit[0] = 3;
        koordinaatit[1]=0;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u2655');
        koordinaatit[0]=4;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u2654');
        koordinaatit[1]=7;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u265A');
        koordinaatit[0]=3;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u265B');
    }
    
    public void setUpInstanceTest() {
        
        Standard standard = new Standard();
        standard.setUp();
        Lauta lauta = standard.getLauta();
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                int[] koordinaatit = {j, i};
                assertTrue(lauta.getNappula(koordinaatit).isEmpty());
            }
        }
        for (int j = 0; j < 8; j++) {
            int[] koordinaatit = {j, 1};
            assertFalse(lauta.getNappula(koordinaatit).isEmpty());
            assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u2659');
            int[] koordinaatit2 = {j, 6};
            assertFalse(lauta.getNappula(koordinaatit2).isEmpty());
            assertEquals(lauta.getNappula(koordinaatit2).getMerkki(), '\u265F');
        }
        int[] koordinaatit = {0, 0};
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u2656');
        koordinaatit[0]=7;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u2656');
        koordinaatit[1]=7;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u265C');
        koordinaatit[0]=0;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u265C');
        
        koordinaatit[0] = 1;
        koordinaatit[1]=0;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u2658');
        koordinaatit[0]=6;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u2658');
        koordinaatit[1]=7;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u265E');
        koordinaatit[0]=1;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u265E');
        
        koordinaatit[0] = 2;
        koordinaatit[1]=0;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u2657');
        koordinaatit[0]=5;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u2657');
        koordinaatit[1]=7;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u265D');
        koordinaatit[0]=2;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u265D');
        
        koordinaatit[0] = 3;
        koordinaatit[1]=0;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u2655');
        koordinaatit[0]=4;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u2654');
        koordinaatit[1]=7;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u265A');
        koordinaatit[0]=3;
        assertEquals(lauta.getNappula(koordinaatit).getMerkki(), '\u265B');
    }

}
