package components;

/**
 *Luokka toteuttaa tyhjän ruudun.
 * @author Oskari Kulmala
 */
public class Tyhja extends Nappula {

    private static char notaatioMerkki = ' ';

    /**
     * Luo tyhjän ruudun.
     */
    public Tyhja() {
        super();
    }


    @Override
    public boolean isEmpty() {
        return true;
    }


    @Override
    public void asetaKoordinaatit(int[] koordinaatit) {
        throw new UnsupportedOperationException("Tyhjän ruudun ei tarvitse tietää koordinaattejaan");
    }


    @Override
    public int[] getKoordinaatit() {
        throw new UnsupportedOperationException("Tyhjän ruudun ei tarvitse tietää koordinaattejaan");
    }

    @Override
    public char getNotaatioMerkki() {
        return notaatioMerkki;
    }
    
    @Override
    public Tyhja kopioi() {
        return new Tyhja();
    }
}
