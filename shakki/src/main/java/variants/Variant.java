package variants;

import components.*;
import java.util.ArrayList;

/**
 * Rajapinta shakkivarianteille.
 *
 * @author Oskari Kulmala
 */
public interface Variant {

    /**
     * Kumpi puoli aloittaa.
     */
    Nappula.Puoli ALOITTAJA = Nappula.Puoli.VALKOINEN;

    /**
     * Laudan leveys.
     */
    int LAUDAN_LEVEYS = 8;

    /**
     * Laudan pituus.
     */
    int LAUDAN_PITUUS = 8;

    /**
     * Kuinka monta lautaa on (esim. tandemshakkia varten).
     */
    int LAUTOJEN_MAARA = 1;

    /**
     * Lauta.
     */
    Lauta LAUTA = new Lauta(LAUDAN_LEVEYS, LAUDAN_PITUUS);

    /**
     * Laudat.
     */
    ArrayList<Lauta> LAUDAT = new ArrayList<>();

    /**
     * Metodi joka laittaa nappulat poikoilleen aloitusasemiin.
     */
    public void setUp();

    /**
     * getteri.
     * @return lauta
     */
    default public Lauta getLauta() {
        if (LAUTOJEN_MAARA == 1) {
            return LAUTA;
        } else {
            return null;
        }
    }

    /**
     * getteri.
     * @return aloittaja
     */
    default public Nappula.Puoli getAloittaja() {
        return ALOITTAJA;
    }
    
    /**
     * getteri.
     * @return nappuloitten nimet
     */
    public String[] getNappuloittenNimet();

    /**
     * getteri.
     * @return nappula esimerkit
     */
    public Nappula[] getNappulaEsimerkit();
}
