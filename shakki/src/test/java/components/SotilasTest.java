package components;

import components.Sotilas;
import components.Lauta;
import org.junit.Test;
import static org.junit.Assert.*;
import logic.liikkuminen.Liikkuminen;

public class SotilasTest {

    @Test
    public void valkoinenLiikkuuEteenpäin() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = {0, 0};
        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        Sotilas sotilas1 = new Sotilas("valkoinen");
        logic.LaudanMuutokset.aseta(sotilas1, koordinaatit, lauta);

        koordinaatit[0] = 3;
        koordinaatit[1] = 5;
        assertTrue(Liikkuminen.koitaSiirtya(sotilas1, koordinaatit, lauta));

        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        assertFalse(Liikkuminen.koitaSiirtya(sotilas1, koordinaatit, lauta));
    }

    @Test
    public void mustaLiikkuuTaaksepäin() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = {0, 0};
        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        Sotilas sotilas1 = new Sotilas("musta");
        logic.LaudanMuutokset.aseta(sotilas1, koordinaatit, lauta);

        koordinaatit[0] = 3;
        koordinaatit[1] = 3;
        assertTrue(Liikkuminen.koitaSiirtya(sotilas1, koordinaatit, lauta));

        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        assertFalse(Liikkuminen.koitaSiirtya(sotilas1, koordinaatit, lauta));
    }

    @Test
    public void syoVinoonValkoinen() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = {0, 0};
        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        Sotilas sotilas1 = new Sotilas("valkoinen");
        logic.LaudanMuutokset.aseta(sotilas1, koordinaatit, lauta);
        koordinaatit[0] = 4;
        koordinaatit[1] = 5;
        Sotilas sotilas2 = new Sotilas("musta");
        logic.LaudanMuutokset.aseta(sotilas2, koordinaatit, lauta);
        koordinaatit[0] = 3;
        koordinaatit[1] = 5;
        Sotilas sotilas3 = new Sotilas("musta");
        logic.LaudanMuutokset.aseta(sotilas3, koordinaatit, lauta);

        koordinaatit[0] = 3;
        koordinaatit[1] = 5;
        assertFalse(Liikkuminen.koitaSiirtya(sotilas1, koordinaatit, lauta));
        koordinaatit[0] = 4;
        koordinaatit[1] = 5;
        assertTrue(Liikkuminen.koitaSiirtya(sotilas1, koordinaatit, lauta));
    }

    @Test
    public void syoVinoonMusta() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = {0, 0};
        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        Sotilas sotilas1 = new Sotilas("musta");
        logic.LaudanMuutokset.aseta(sotilas1, koordinaatit, lauta);
        koordinaatit[0] = 2;
        koordinaatit[1] = 3;
        Sotilas sotilas2 = new Sotilas("valkoinen");
        logic.LaudanMuutokset.aseta(sotilas2, koordinaatit, lauta);
        koordinaatit[0] = 3;
        koordinaatit[1] = 3;
        Sotilas sotilas3 = new Sotilas("valkoinen");
        logic.LaudanMuutokset.aseta(sotilas3, koordinaatit, lauta);

        koordinaatit[0] = 3;
        koordinaatit[1] = 3;
        assertFalse(Liikkuminen.koitaSiirtya(sotilas1, koordinaatit, lauta));
        koordinaatit[0] = 2;
        koordinaatit[1] = 3;
        assertTrue(Liikkuminen.koitaSiirtya(sotilas1, koordinaatit, lauta));
    }

    @Test
    public void eiVoiLiikkuaSivulle() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[] koordinaatit = {0, 0};
        koordinaatit[0] = 3;
        koordinaatit[1] = 4;
        Sotilas sotilas1 = new Sotilas("valkoinen");
        logic.LaudanMuutokset.aseta(sotilas1, koordinaatit, lauta);

        koordinaatit[0] = 4;
        koordinaatit[1] = 4;
        assertFalse(Liikkuminen.koitaSiirtya(sotilas1, koordinaatit, lauta));

        koordinaatit[0] = 2;
        koordinaatit[1] = 4;
        assertFalse(Liikkuminen.koitaSiirtya(sotilas1, koordinaatit, lauta));
    }
}
