package components;

import components.Torni;
import components.Lauta;
import org.junit.Test;
import static org.junit.Assert.*;
import logic.liikkuminen.Liikkuminen;

public class TorniTest {

    public TorniTest() {
    }

    @Test
    public void testLiikkuminenTyhjälläLaudalla() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = {0, 0};
        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        Torni torni1 = new Torni("valkoinen");
        logic.LaudanMuutokset.aseta(torni1, koordinaatit, lauta);

        assertFalse(Liikkuminen.koitaSiirtya(torni1, koordinaatit, lauta));
        koordinaatit[0] = 4;
        koordinaatit[1] = 2;
        assertFalse(Liikkuminen.koitaSiirtya(torni1, koordinaatit, lauta));
        koordinaatit[0] = 4;
        koordinaatit[1] = 4;
        assertTrue(Liikkuminen.koitaSiirtya(torni1, koordinaatit, lauta));
        koordinaatit[0] = 2;
        koordinaatit[1] = 4;
        assertTrue(Liikkuminen.koitaSiirtya(torni1, koordinaatit, lauta));
        koordinaatit[0] = 2;
        koordinaatit[1] = 6;
        assertTrue(Liikkuminen.koitaSiirtya(torni1, koordinaatit, lauta));
        koordinaatit[0] = 2;
        koordinaatit[1] = 3;
        assertTrue(Liikkuminen.koitaSiirtya(torni1, koordinaatit, lauta));
    }

    @Test
    public void eiVoiLiikkuaToisenNappulanLäpi() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = {0, 0};
        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        Torni torni1 = new Torni("valkoinen");
        logic.LaudanMuutokset.aseta(torni1, koordinaatit, lauta);
        koordinaatit[0] = 4;
        koordinaatit[1] = 4;
        Torni torni2 = new Torni("valkoinen");
        logic.LaudanMuutokset.aseta(torni2, koordinaatit, lauta);

        koordinaatit[0] = 5;
        koordinaatit[1] = 4;
        assertFalse(Liikkuminen.koitaSiirtya(torni1, koordinaatit, lauta));
    }

    @Test
    public void testSyöminen() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = {0, 0};
        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        Torni torni1 = new Torni("valkoinen");
        logic.LaudanMuutokset.aseta(torni1, koordinaatit, lauta);
        koordinaatit[0] = 4;
        koordinaatit[1] = 4;
        Torni torni2 = new Torni("musta");
        logic.LaudanMuutokset.aseta(torni2, koordinaatit, lauta);

        assertTrue(Liikkuminen.koitaSiirtya(torni1, koordinaatit, lauta));
    }
}
