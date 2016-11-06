package components;

/**
 * Luokka toteuttaa shakkinappulan. Nappulalla on puoli, sijainti laudalla ja
 * merkki notaatiota varten. Tyhjän ruudun notaatiomerkiksi on valittu
 * välilyönti. Sama on käytössä sotilaalla (vaikka null olisi sille oikeampi),
 * mutta kummankaan merkkiä ei pitäisi koskaan käyttää mihinkään.
 *
 * @author Oskari Kulmala
 */
public class Nappula {

    /**
     * Vaihtoehdot nappulan puolelle, valkoinen/musta/tyhjä ruutu.
     * Refaktoroidaan luultavasti myöhemmin variantti-luokkaan ja sen
     * aliluokkiin.
     */
    public enum Puoli {

        /**
         * valkoinen.
         */
        VALKOINEN,
        /**
         * musta.
         */
        MUSTA,
        /**
         * Tyhjä ruutu.
         */
        TYHJA
    }
    private Puoli puoli;
    private int[] sijainti = {-1, -1};
    private static char notaatioMerkki = ' ';
    private boolean liikkunut;
    private static final String NIMI = "TYHJA";

    /**
     * Konstruktori, asettaa puolen ja ettei ole liikkunut.
     *
     * @param puoli valkoinen vai musta
     */
    public Nappula(String puoli) {
        if (puoli.equalsIgnoreCase("valkoinen")) {
            this.puoli = Puoli.VALKOINEN;
        } else if (puoli.equalsIgnoreCase("musta")) {
            this.puoli = Puoli.MUSTA;
        } else {
            System.out.println("Puoli ei kelpaa tai puuttuu");
        }
        this.liikkunut = false;
    }

    /**
     * Luo uuden nappulan ja asettaa sen puolen.
     *
     * @param puoli Valkoinen vai musta
     */
    public Nappula(Nappula.Puoli puoli) {
        this.puoli = puoli;
    }

    /**
     * Luo uuden nappulan ja asettaa sen puolen tyhjäksi ruuduksi.
     */
    public Nappula() {
        puoli = Puoli.TYHJA;
    }

    /**
     * Metodi palauttaa saman tyyppisen nappulan.
     *
     * @return kopio
     */
    public Nappula kopioi() {
        Nappula kopio = new Nappula(puoli);
//        kopio.asetaKoordinaatit(sijainti);
        return kopio;
    }

//    /**
//     * Luo uuden nappulan, jolla on annettu puoli.
//     *
//     * @return 
//     */
//    public Nappula(Nappula nappula, String puoli) {
//        if (puoli.equalsIgnoreCase("valkoinen")) {
//            this.puoli = Puoli.VALKOINEN;
//        } else if (puoli.equalsIgnoreCase("musta")) {
//            this.puoli = Puoli.MUSTA;
//        } else {
//            this.puoli = Puoli.TYHJA;
//        }
//    }

    public String getNimi() {
        return NIMI;
    }

    /**
     * getteri.
     *
     * @return puoli
     */
    public Puoli getPuoli() {
        return puoli;
    }

    /**
     * setteri.
     * @param puoli miksi puoli asetetaan
     */
    public void setPuoli(Nappula.Puoli puoli) {
        this.puoli = puoli;
    }

    /**
     * getteri.
     *
     * @return puoli
     */
    public String getPuoliString() {
        if (puoli == Puoli.VALKOINEN) {
            return "valkoinen";
        }
        if (puoli == Puoli.MUSTA) {
            return "musta";
        }
        return "tyhja";
    }

    /**
     * setteri.
     *
     * @param koordinaatit koordinaatit
     */
    public void asetaKoordinaatit(int[] koordinaatit) {
        this.sijainti[0] = koordinaatit[0];
        this.sijainti[1] = koordinaatit[1];
    }

    /**
     * getteri.
     *
     * @return koordinaatit
     */
    public int[] getKoordinaatit() {
        return this.sijainti;
    }

    /**
     * Onko tyhjä vai ei, ei ole.
     *
     * @return false
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * getteri.
     *
     * @return merkki
     */
    public char getMerkki() {
        return ' ';
    }

    /**
     * getteri.
     *
     * @return onko liikkunut
     */
    public boolean onkoLiikkunut() {
        return liikkunut;
    }

    /**
     * setteri.
     */
    public void setLiikkunut() {
        this.liikkunut = true;
    }

    /**
     * getteri.
     *
     * @return notaatiomerkki
     */
    public char getNotaatioMerkki() {
        return notaatioMerkki;
    }

    /**
     * Onko sotilas, ei ole.
     *
     * @return false
     */
    public boolean onSotilas() {
        return false;
    }
}
