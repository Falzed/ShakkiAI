package AI;

import components.Lauta;
import components.Nappula;

public class Solmu {

    private Lauta lauta;
    private int[] enPassant;
    private Nappula.Puoli vuorossa;
    private int[][] edellinenKomento;

    public Solmu(Lauta lauta, int[] enPassant, Nappula.Puoli vuoro, int[][] edellinenKomento) {
        this.enPassant = enPassant;
        this.lauta = lauta;
        this.edellinenKomento = edellinenKomento;
        this.vuorossa = vuoro;
    }

    public Lauta getLauta() {
        return this.lauta;
    }

    public int[] getEnPassant() {
        return enPassant;
    }

    public Nappula.Puoli getVuoro() {
        return this.vuorossa;
    }
    
    public int[][] getEdellinenKomento() {
        return this.edellinenKomento;
    }

}
