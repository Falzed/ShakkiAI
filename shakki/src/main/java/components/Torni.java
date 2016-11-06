package components;

/**
 *Luokka toteuttaa standardishakin tornin. Tornitusta ei ole vielä toteutettu.
 * @author Oskari Kulmala
 */
public class Torni extends Nappula {

    private static final char VALKOINENMERKKI = '\u2656';
    private static final char MUSTAMERKKI = '\u265C';
    private static final char NOTAATIOMERKKI = 'R';
    private static final String NIMI = "Torni";

    /**
     * Konstruktori, kutsuu vain Nappula-luokan konstruktoria. Puoli stringinä.
     * @param puoli valkoinen/musta
     */
    public Torni(String puoli) {
        super(puoli);
    }
    /**
     * Konstruktori, kutsuu vain Nappula-luokan konstruktoria. Puoli enumina.
     * @param puoli valkoinen/musta
     */
    public Torni(Nappula.Puoli puoli) {
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
    public Torni kopioi() {
        Torni kopio = new Torni(super.getPuoli());
//        kopio.asetaKoordinaatit(sijainti);
        return kopio;
    }
    @Override
    public String getNimi() {
        return NIMI;
    }
}
