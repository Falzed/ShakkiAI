package logic;

import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;
import components.*;
import logic.liikkuminen.Liikkuminen;
import logic.parser.Parser;

public class ShakitusTest {

    @Test
    public void tunnistaaShakin() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        logic.LaudanMuutokset.aseta(new Kuningas("valkoinen"), "e1", lauta);
        assertFalse(Shakitus.uhattu(lauta, "e1", Nappula.Puoli.VALKOINEN));
        logic.LaudanMuutokset.aseta(new Torni("musta"), "a1", lauta);
        assertTrue(Shakitus.uhattu(lauta, "e1", Nappula.Puoli.VALKOINEN));
    }

    @Test
    public void tunnistaaEtteiShakissa() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        logic.LaudanMuutokset.aseta(new Kuningas("valkoinen"), "e2", lauta);
        assertFalse(Shakitus.uhattu(lauta, "e1", Nappula.Puoli.VALKOINEN));
        logic.LaudanMuutokset.aseta(new Torni("musta"), "a1", lauta);
        assertFalse(Shakitus.uhattu(lauta, "e2", Nappula.Puoli.VALKOINEN));
    }

    @Test
    public void tunnistaaMatin() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        logic.LaudanMuutokset.aseta(new Kuningas("valkoinen"), "e1", lauta);
        logic.LaudanMuutokset.aseta(new Torni("musta"), "a1", lauta);
        assertFalse(Shakitus.matissa(lauta, "valkoinen", null));
        logic.LaudanMuutokset.aseta(new Torni("musta"), "a2", lauta);
        assertTrue(Shakitus.matissa(lauta, "valkoinen", null));
    }

    @Test
    public void tunnistaaPatin() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        logic.LaudanMuutokset.aseta(new Kuningas("valkoinen"), "e1", lauta);
        logic.LaudanMuutokset.aseta(new Torni("musta"), "a2", lauta);
        logic.LaudanMuutokset.aseta(new Torni("musta"), "d8", lauta);
        assertFalse(Shakitus.patissa(lauta, Nappula.Puoli.VALKOINEN, null));
        logic.LaudanMuutokset.aseta(new Torni("musta"), "f8", lauta);
        assertTrue(Shakitus.patissa(lauta, Nappula.Puoli.VALKOINEN, null));
    }

    @Test
    public void tunnistaaMatinMusta() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        logic.LaudanMuutokset.aseta(new Kuningas("musta"), "e1", lauta);
        logic.LaudanMuutokset.aseta(new Torni("valkoinen"), "a1", lauta);
        assertFalse(Shakitus.matissa(lauta, "musta", null));
        logic.LaudanMuutokset.aseta(new Torni("valkoinen"), "a2", lauta);
        assertTrue(Shakitus.matissa(lauta, "musta", null));
    }

    @Test
    public void tunnistaaPatinMusta() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        logic.LaudanMuutokset.aseta(new Kuningas("musta"), "e1", lauta);
        logic.LaudanMuutokset.aseta(new Torni("valkoinen"), "a2", lauta);
        logic.LaudanMuutokset.aseta(new Torni("valkoinen"), "d8", lauta);
        assertFalse(Shakitus.patissa(lauta, Nappula.Puoli.MUSTA, null));
        logic.LaudanMuutokset.aseta(new Torni("valkoinen"), "f8", lauta);
        assertTrue(Shakitus.patissa(lauta, Nappula.Puoli.MUSTA, null));
    }
    
    //esim. dunsany's chess -varianttia varten
    @Test
    public void mattiJosEiNappuloitaLaudalla() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        logic.LaudanMuutokset.aseta(new Torni("musta"), "e2", lauta);
        logic.LaudanMuutokset.aseta(new Torni("valkoinen"), "a2", lauta);
        assertFalse(Shakitus.matissa(lauta, Nappula.Puoli.MUSTA, null));
        Liikkuminen.koitaSiirtya(Parser.parseAlgebraic("a2"), Parser.parseAlgebraic("e2"), lauta, null);
        assertTrue(Shakitus.matissa(lauta, Nappula.Puoli.MUSTA, null));
    }
}
