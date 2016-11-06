package components;

/**
 * Luokka toteuttaa standardishakin ratsun.
 * @author Oskari Kulmala
 */
public class Ratsu extends Nappula {

    private static final char VALKOINENMERKKI = '\u2658';
    private static final char MUSTAMERKKI = '\u265E';
    private static final char NOTAATIOMERKKI = 'N';
    private static final String NIMI = "Ratsu";

    /**
     * Konstruktori, kutsuu vain Nappula-luokan konstruktoria. Puoli stringinä.
     * @param puoli valkoinen/musta
     */
    public Ratsu(String puoli) {
        super(puoli);
    }
    /**
     * Konstruktori, kutsuu vain Nappula-luokan konstruktoria. Puoli enumina.
     * @param puoli valkoinen/musta
     */
    public Ratsu(Nappula.Puoli puoli) {
        super(puoli);
    }

    @Override
    public void asetaKoordinaatit(int[] koordinaatit) {
        getKoordinaatit()[0] = koordinaatit[0];
        getKoordinaatit()[1] = koordinaatit[1];
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
    public Ratsu kopioi() {
        Ratsu kopio = new Ratsu(super.getPuoli());
//        kopio.asetaKoordinaatit(sijainti);
        return kopio;
    }
    @Override
    public String getNimi() {
        return NIMI;
    }
}
