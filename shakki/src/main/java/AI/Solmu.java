package AI;
import components.Lauta;
import components.Nappula;

public class Solmu {
    private Lauta lauta;
    private int[] enPassant;
    private Nappula.Puoli vuorossa;
    
    public Solmu(Lauta lauta, int[] enPassant, Nappula.Puoli vuoro) {
        this.enPassant = enPassant;
        this.lauta = lauta;
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
    
}
