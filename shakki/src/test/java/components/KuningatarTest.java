package components;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import logic.liikkuminen.Liikkuminen;

public class KuningatarTest {

    @Test
    public void testLiikkuminenTyhjallaLaudallaLahettimainen() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = {0, 0};
        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        Kuningatar kuningatar1 = new Kuningatar("valkoinen");
        logic.LaudanMuutokset.aseta(kuningatar1, koordinaatit, lauta);

        assertFalse(Liikkuminen.koitaSiirtya(kuningatar1, koordinaatit, lauta));
        koordinaatit[0] = 6;
        koordinaatit[1] = 7;
        assertTrue(Liikkuminen.koitaSiirtya(kuningatar1, koordinaatit, lauta));
        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        assertTrue(Liikkuminen.koitaSiirtya(kuningatar1, koordinaatit, lauta));
        koordinaatit[0] = 4;
        koordinaatit[1] = 3;
        assertTrue(Liikkuminen.koitaSiirtya(kuningatar1, koordinaatit, lauta));
        koordinaatit[0] = 6;
        koordinaatit[1] = 1;
        assertTrue(Liikkuminen.koitaSiirtya(kuningatar1, koordinaatit, lauta));
        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        assertTrue(Liikkuminen.koitaSiirtya(kuningatar1, koordinaatit, lauta));
    }

    @Test
    public void testLiikkuminenTyhjallaLaudallaTornimainen() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = {0, 0};
        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        Kuningatar kuningatar = new Kuningatar("valkoinen");
        logic.LaudanMuutokset.aseta(kuningatar, koordinaatit, lauta);

        assertFalse(Liikkuminen.koitaSiirtya(kuningatar, koordinaatit, lauta));
        koordinaatit[0] = 2;
        koordinaatit[1] = 4;
        assertTrue(Liikkuminen.koitaSiirtya(kuningatar, koordinaatit, lauta));
        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        assertTrue(Liikkuminen.koitaSiirtya(kuningatar, koordinaatit, lauta));
        koordinaatit[0] = 2;
        koordinaatit[1] = 3;
        assertTrue(Liikkuminen.koitaSiirtya(kuningatar, koordinaatit, lauta));
    }

    @Test
    public void eiVoiLiikkuaToisenNappulanLäpi() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = {0, 0};
        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        Kuningatar kuningatar = new Kuningatar("valkoinen");
        logic.LaudanMuutokset.aseta(kuningatar, koordinaatit, lauta);
        Lahetti lahetti2 = new Lahetti("musta");
        koordinaatit[0] = 4;
        koordinaatit[1] = 5;
        logic.LaudanMuutokset.aseta(lahetti2, koordinaatit, lauta);
        koordinaatit[0] = 5;
        koordinaatit[1] = 6;
        assertFalse(Liikkuminen.koitaSiirtya(kuningatar, koordinaatit, lauta));
    }

    @Test
    public void testSyominen() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = {0, 0};
        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        Kuningatar kuningatar = new Kuningatar("valkoinen");
        logic.LaudanMuutokset.aseta(kuningatar, koordinaatit, lauta);
        Lahetti lahetti2 = new Lahetti("musta");
        koordinaatit[0] = 5;
        koordinaatit[1] = 6;
        logic.LaudanMuutokset.aseta(lahetti2, koordinaatit, lauta);
        assertTrue(Liikkuminen.koitaSiirtya(kuningatar, koordinaatit, lauta));
    }

    @Test
    public void eiVoiSyodaOmaa() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = {0, 0};
        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        Kuningatar kuningatar = new Kuningatar("valkoinen");
        logic.LaudanMuutokset.aseta(kuningatar, koordinaatit, lauta);
        Lahetti lahetti2 = new Lahetti("valkoinen");
        koordinaatit[0] = 5;
        koordinaatit[1] = 6;
        logic.LaudanMuutokset.aseta(lahetti2, koordinaatit, lauta);
        assertFalse(Liikkuminen.koitaSiirtya(kuningatar, koordinaatit, lauta));
    }
}
