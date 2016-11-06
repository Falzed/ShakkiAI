package components;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import logic.liikkuminen.Liikkuminen;

public class LahettiTest {

    @Test
    public void testLiikkuminenTyhjallaLaudalla() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = {0, 0};
        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        Lahetti lahetti1 = new Lahetti("valkoinen");
        logic.LaudanMuutokset.aseta(lahetti1, koordinaatit, lauta);

        koordinaatit[0] = 5;
        koordinaatit[1] = 4;
        assertFalse(Liikkuminen.koitaSiirtya(lahetti1, koordinaatit, lauta));
        koordinaatit[0] = 2;
        koordinaatit[1] = 4;
        assertFalse(Liikkuminen.koitaSiirtya(lahetti1, koordinaatit, lauta));
        koordinaatit[0] = 3;
        koordinaatit[1] = 5;
        assertFalse(Liikkuminen.koitaSiirtya(lahetti1, koordinaatit, lauta));
        koordinaatit[0] = 3;
        koordinaatit[1] = 3;
        assertFalse(Liikkuminen.koitaSiirtya(lahetti1, koordinaatit, lauta));
        koordinaatit[0] = 4;
        koordinaatit[1] = 5;
        assertTrue(Liikkuminen.koitaSiirtya(lahetti1, koordinaatit, lauta));
        koordinaatit[0] = 6;
        koordinaatit[1] = 7;
        assertTrue(Liikkuminen.koitaSiirtya(lahetti1, koordinaatit, lauta));
        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        assertTrue(Liikkuminen.koitaSiirtya(lahetti1, koordinaatit, lauta));
        koordinaatit[0] = 4;
        koordinaatit[1] = 3;
        assertTrue(Liikkuminen.koitaSiirtya(lahetti1, koordinaatit, lauta));
        koordinaatit[0] = 6;
        koordinaatit[1] = 1;
        assertTrue(Liikkuminen.koitaSiirtya(lahetti1, koordinaatit, lauta));
        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        assertTrue(Liikkuminen.koitaSiirtya(lahetti1, koordinaatit, lauta));
    }

    @Test
    public void eiVoiLiikkuaToisenNappulanLäpi() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = {0, 0};
        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        Lahetti lahetti1 = new Lahetti("valkoinen");
        logic.LaudanMuutokset.aseta(lahetti1, koordinaatit, lauta);
        Lahetti lahetti2 = new Lahetti("musta");
        koordinaatit[0] = 4;
        koordinaatit[1] = 5;
        logic.LaudanMuutokset.aseta(lahetti2, koordinaatit, lauta);
        koordinaatit[0] = 5;
        koordinaatit[1] = 6;
        assertFalse(Liikkuminen.koitaSiirtya(lahetti1, koordinaatit, lauta));
    }

    @Test
    public void testSyöminen() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = {0, 0};
        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        Lahetti lahetti1 = new Lahetti("valkoinen");
        logic.LaudanMuutokset.aseta(lahetti1, koordinaatit, lauta);
        Lahetti lahetti2 = new Lahetti("musta");
        koordinaatit[0] = 5;
        koordinaatit[1] = 6;
        logic.LaudanMuutokset.aseta(lahetti2, koordinaatit, lauta);
        assertTrue(Liikkuminen.koitaSiirtya(lahetti1, koordinaatit, lauta));
    }
}
