package components;

/**
 * Luokka toteuttaa standardishakin sotilaan. Korotus puuttuu vielä.
 * @author Oskari Kulmala
 */
public class Sotilas extends Nappula {

    private static final char VALKOINENMERKKI = '\u2659';
    private static final char MUSTAMERKKI = '\u265F';
    private static final char NOTAATIOMERKKI = ' ';
    private static final String NIMI = "Sotilas";

    /**
     * Konstruktori, kutsuu vain Nappula-luokan konstruktoria. Puoli stringinä.
     * @param puoli valkoinen/musta
     */
    public Sotilas(String puoli) {
        super(puoli);
    }
    /**
     * Konstruktori, kutsuu vain Nappula-luokan konstruktoria. Puoli enumina.
     * @param puoli valkoinen/musta
     */
    public Sotilas(Nappula.Puoli puoli) {
        super(puoli);
    }
    
    @Override
    public char getNotaatioMerkki() {
        return NOTAATIOMERKKI;
    }

    @Override
    public char getMerkki() {
        if (getPuoli().equals(Puoli.VALKOINEN)) {
            return VALKOINENMERKKI;
        }
        if (getPuoli().equals(Puoli.MUSTA)) {
            return MUSTAMERKKI;
        }
        return super.getMerkki();
    }
    
    @Override
    public Sotilas kopioi() {
        Sotilas kopio = new Sotilas(super.getPuoli());
//        kopio.asetaKoordinaatit(sijainti);
        return kopio;
    }
    
    /**
     * Onko sotilas (on).
     * @return true
     */
    @Override
    public boolean onSotilas() {
        return true;
    }
    @Override
    public String getNimi() {
        return NIMI;
    }
}
