package components;

/**
 *Luokka toteuttaa standardishakin lähetin.
 * @author Oskari Kulmala
 */
public class Lahetti extends Nappula {

    private static final char VALKOINENMERKKI = '\u2657';
    private static final char MUSTAMERKKI = '\u265D';
    private static final char NOTAATIOMERKKI = 'B';
    private static final String NIMI = "Lahetti";

    /**
     *Konstruktori, kutsuu vain Nappula-luokan konstruktoria. Puoli stringinä.
     * @param puoli   valkoinen vai musta  * 
     */
    public Lahetti(String puoli) {
        super(puoli);
    }
    
    /**
     * Konstruktori, kutsuu vain Nappula-luokan konstruktoria. Puoli enumina.
     * @param puoli valkoinen/musta
     */
    public Lahetti(Nappula.Puoli puoli) {
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
    public Lahetti kopioi() {
        Lahetti kopio = new Lahetti(super.getPuoli());
//        kopio.asetaKoordinaatit(sijainti);
        return kopio;
    }
    @Override
    public String getNimi() {
        return NIMI;
    }
}
