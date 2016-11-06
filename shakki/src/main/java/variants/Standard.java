package variants;

import components.*;
import java.util.ArrayList;

/**
 * Luokka toteuttaa standardishakin.
 * @author Oskari Kulmala
 */
public class Standard implements Variant {

    String[] nappuloittenNimet = {"Sotilas", "Torni", "Ratsu", "Lähetti", "Kuningatar", "Kuningas"};
    Nappula[] nappulaEsimerkit = 
    {new Sotilas("valkoinen"), new Torni("valkoinen"), 
        new Ratsu("valkoinen"), new Lahetti("valkoinen"), 
        new Kuningatar("valkoinen"), new Kuningas("valkoinen"),
        new Sotilas("musta"), new Torni("musta"), 
        new Ratsu("musta"), new Lahetti("musta"), 
        new Kuningatar("musta"), new Kuningas("musta")};
    /**
     * Standardishakki.
     */
    public Standard() {
        
    }
    @Override
    public String[] getNappuloittenNimet() {
        return this.nappuloittenNimet;
    }
    @Override
    public Nappula[] getNappulaEsimerkit() {
        return this.nappulaEsimerkit;
    }
    @Override
    public void setUp() {
        LAUTA.alustaLauta();
//        //System.out.println("alustettu");
        int[] koordinaatit = new int[2];
        koordinaatit[0] = 0;
        koordinaatit[1] = 0;
//        //System.out.println("Tornit");
        logic.LaudanMuutokset.aseta(new Torni("valkoinen"), koordinaatit, LAUTA);
        koordinaatit[0] = 7;
        logic.LaudanMuutokset.aseta(new Torni("valkoinen"), koordinaatit, LAUTA);

        koordinaatit[1] = 7;
        logic.LaudanMuutokset.aseta(new Torni("musta"), koordinaatit, LAUTA);
        koordinaatit[0] = 0;
        logic.LaudanMuutokset.aseta(new Torni("musta"), koordinaatit, LAUTA);
        //System.out.println("ratsut");
        koordinaatit[0] = 1;
        koordinaatit[1] = 0;
        logic.LaudanMuutokset.aseta(new Ratsu("valkoinen"), koordinaatit, LAUTA);
        koordinaatit[0] = 6;
        logic.LaudanMuutokset.aseta(new Ratsu("valkoinen"), koordinaatit, LAUTA);

        koordinaatit[1] = 7;
        logic.LaudanMuutokset.aseta(new Ratsu("musta"), koordinaatit, LAUTA);
        koordinaatit[0] = 1;
        logic.LaudanMuutokset.aseta(new Ratsu("musta"), koordinaatit, LAUTA);
        //System.out.println("lahetit");
        koordinaatit[0] = 2;
        koordinaatit[1] = 0;
        logic.LaudanMuutokset.aseta(new Lahetti("valkoinen"), koordinaatit, LAUTA);
        koordinaatit[0] = 5;
        logic.LaudanMuutokset.aseta(new Lahetti("valkoinen"), koordinaatit, LAUTA);

        koordinaatit[1] = 7;
        logic.LaudanMuutokset.aseta(new Lahetti("musta"), koordinaatit, LAUTA);
        koordinaatit[0] = 2;
        logic.LaudanMuutokset.aseta(new Lahetti("musta"), koordinaatit, LAUTA);
        //System.out.println("kuningattaret");
        koordinaatit[0] = 3;
        koordinaatit[1] = 0;
        logic.LaudanMuutokset.aseta(new Kuningatar("valkoinen"), koordinaatit, LAUTA);
        koordinaatit[1] = 7;
        logic.LaudanMuutokset.aseta(new Kuningatar("musta"), koordinaatit, LAUTA);
        //System.out.println("kuninkaat");
        koordinaatit[0] = 4;
        koordinaatit[1] = 0;
        logic.LaudanMuutokset.aseta(new Kuningas("valkoinen"), koordinaatit, LAUTA);
        koordinaatit[1] = 7;
        logic.LaudanMuutokset.aseta(new Kuningas("musta"), koordinaatit, LAUTA);

        //System.out.println("sotilaat");
        koordinaatit[1] = 1;
        for (int i = 0; i < 8; i++) {
            koordinaatit[0] = i;
            logic.LaudanMuutokset.aseta(new Sotilas("valkoinen"), koordinaatit, LAUTA);
        }
        koordinaatit[1] = 6;
        for (int i = 0; i < 8; i++) {
            koordinaatit[0] = i;
            logic.LaudanMuutokset.aseta(new Sotilas("musta"), koordinaatit, LAUTA);
        }
    }

    /**
     * Staattinen metodi laudan alustamiseen. Ei tällä hetkellä käytössä.
     * @param lauta alustettava lauta
     */
    public static void setUp(Lauta lauta) {
        lauta.alustaLauta();
//        //System.out.println("alustettu");
        int[] koordinaatit = new int[2];
        koordinaatit[0] = 0;
        koordinaatit[1] = 0;
//        //System.out.println("Tornit");
        logic.LaudanMuutokset.aseta(new Torni("valkoinen"), koordinaatit, lauta);
        koordinaatit[0] = 7;
        logic.LaudanMuutokset.aseta(new Torni("valkoinen"), koordinaatit, lauta);

        koordinaatit[1] = 7;
        logic.LaudanMuutokset.aseta(new Torni("musta"), koordinaatit, lauta);
        koordinaatit[0] = 0;
        logic.LaudanMuutokset.aseta(new Torni("musta"), koordinaatit, lauta);
        //System.out.println("ratsut");
        koordinaatit[0] = 1;
        koordinaatit[1] = 0;
        logic.LaudanMuutokset.aseta(new Ratsu("valkoinen"), koordinaatit, lauta);
        koordinaatit[0] = 6;
        logic.LaudanMuutokset.aseta(new Ratsu("valkoinen"), koordinaatit, lauta);

        koordinaatit[1] = 7;
        logic.LaudanMuutokset.aseta(new Ratsu("musta"), koordinaatit, lauta);
        koordinaatit[0] = 1;
        logic.LaudanMuutokset.aseta(new Ratsu("musta"), koordinaatit, lauta);
        //System.out.println("lahetit");
        koordinaatit[0] = 2;
        koordinaatit[1] = 0;
        logic.LaudanMuutokset.aseta(new Lahetti("valkoinen"), koordinaatit, lauta);
        koordinaatit[0] = 5;
        logic.LaudanMuutokset.aseta(new Lahetti("valkoinen"), koordinaatit, lauta);

        koordinaatit[1] = 7;
        logic.LaudanMuutokset.aseta(new Lahetti("musta"), koordinaatit, lauta);
        koordinaatit[0] = 2;
        logic.LaudanMuutokset.aseta(new Lahetti("musta"), koordinaatit, lauta);
        //System.out.println("kuningattaret");
        koordinaatit[0] = 3;
        koordinaatit[1] = 0;
        logic.LaudanMuutokset.aseta(new Kuningatar("valkoinen"), koordinaatit, lauta);
        koordinaatit[1] = 7;
        logic.LaudanMuutokset.aseta(new Kuningatar("musta"), koordinaatit, lauta);
        //System.out.println("kuninkaat");
        koordinaatit[0] = 4;
        koordinaatit[1] = 0;
        logic.LaudanMuutokset.aseta(new Kuningas("valkoinen"), koordinaatit, lauta);
        koordinaatit[1] = 7;
        logic.LaudanMuutokset.aseta(new Kuningas("musta"), koordinaatit, lauta);

        //System.out.println("sotilaat");
        koordinaatit[1] = 1;
        for (int i = 0; i < 8; i++) {
            koordinaatit[0] = i;
            logic.LaudanMuutokset.aseta(new Sotilas("valkoinen"), koordinaatit, lauta);
        }
        koordinaatit[1] = 6;
        for (int i = 0; i < 8; i++) {
            koordinaatit[0] = i;
            logic.LaudanMuutokset.aseta(new Sotilas("musta"), koordinaatit, lauta);
        }
    }
}
