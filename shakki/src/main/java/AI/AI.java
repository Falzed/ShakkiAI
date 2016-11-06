package AI;

import components.Lauta;
import components.Nappula;
import java.util.ArrayList;
import static logic.Shakitus.shakissa;
import logic.liikkuminen.Liikkuminen;
import logic.liikkuminen.VoikoSiirtya;

public class AI {

    private Nappula.Puoli puoli;
    private static final int lepotilahakuSyvyys = 5;

    public AI(Nappula.Puoli puoli) {
        this.puoli = puoli;
    }

    public int quiescenceSearchAlphaBeta(Solmu solmu, int syvyys, int alpha, int beta, boolean maksimoitavaPelaaja) {
        if (hiljainen(solmu)) {
            return heuristiikka(solmu);
        }

        int arvio = 0;
        ArrayList<Solmu> lapset = lapset(solmu);
        if (syvyys == 0 || lapset.isEmpty()) {
            return heuristiikka(solmu);
        }
        if (maksimoitavaPelaaja) {
            arvio = Integer.MIN_VALUE;
            for (Solmu lapsi : lapset) {
                arvio = Integer.max(arvio, quiescenceSearchAlphaBeta(lapsi, syvyys - 1, alpha, beta, false));
                alpha = Integer.max(alpha, arvio);
                if (beta <= alpha) {
                    break;
                }
            }
            return arvio;
        } else {
            arvio = Integer.MAX_VALUE;
            for (Solmu lapsi : lapset) {
                arvio = Integer.min(arvio, quiescenceSearchAlphaBeta(lapsi, syvyys - 1, alpha, beta, true));
                beta = Integer.min(beta, arvio);
                if (beta <= alpha) {
                    break;
                }
            }
            return arvio;
        }
    }

    public int alphaBetaNormal(Solmu solmu, int syvyys, int alpha, int beta, boolean maksimoitavaPelaaja) {
        int arvio = 0;
        ArrayList<Solmu> lapset = lapset(solmu);
        if (lapset.isEmpty()) {
            return heuristiikka(solmu);
        } else if (syvyys == 0) {
            return quiescenceSearchAlphaBeta(solmu, lepotilahakuSyvyys, alpha, beta, maksimoitavaPelaaja);
        } else {
            if (maksimoitavaPelaaja) {
                arvio = Integer.MIN_VALUE;
                for (Solmu lapsi : lapset) {
                    arvio = Integer.max(arvio, alphaBetaNormal(lapsi, syvyys - 1, alpha, beta, false));
                    alpha = Integer.max(alpha, arvio);
                    if (beta <= alpha) {
                        break;
                    }
                }
                return arvio;
            } else {
                arvio = Integer.MAX_VALUE;
                for (Solmu lapsi : lapset) {
                    arvio = Integer.min(arvio, alphaBetaNormal(lapsi, syvyys - 1, alpha, beta, true));
                    beta = Integer.min(beta, arvio);
                    if (beta <= alpha) {
                        break;
                    }
                }
                return arvio;
            }
        }
    }

    public boolean hiljainen(Solmu solmu) {
        //Ei toteutettu
        return false;
    }

    public ArrayList<Solmu> lapset(Solmu solmu) {
        Lauta lauta = solmu.getLauta();
        Nappula.Puoli lapsiVuoro;
        if(solmu.getVuoro() == Nappula.Puoli.VALKOINEN) {
            lapsiVuoro = Nappula.Puoli.MUSTA;
        } else {
            lapsiVuoro = Nappula.Puoli.VALKOINEN;
        }
        int[] enPassant = solmu.getEnPassant();
        ArrayList<Nappula> omatNappulat = new ArrayList<>();
        ArrayList<Solmu> lapset = new ArrayList<>();
        for (int i = 0; i < lauta.getLeveys(); i++) {
            for (int j = 0; j < lauta.getPituus(); j++) {
                int[] mista = {i, j};
                Nappula nappula = lauta.getNappula(mista);
                if (nappula.getPuoli() == puoli) {
                    omatNappulat.add(nappula);
                }
            }
        }
        for (Nappula nappula : omatNappulat) {
            for (int i = 0; i < lauta.getLeveys(); i++) {
                for (int j = 0; j < lauta.getPituus(); j++) {
                    int[] minne = {i, j};
                    if (VoikoSiirtya.voikoSiirtya(nappula.getKoordinaatit(), minne, lauta, enPassant)) {
                        Lauta testLauta = lauta.kopioi();
                        int[] uusiEnPassant = ohestaLyo(nappula.getKoordinaatit(), minne, lauta);

                        Liikkuminen.koitaSiirtya(nappula.getKoordinaatit(), minne, testLauta, enPassant);
                        if (!shakissa(testLauta, puoli)) {
                            lapset.add(new Solmu(testLauta, uusiEnPassant, lapsiVuoro));
                        }
                    }
                }
            }
        }
        return lapset;
    }

    public int[] ohestaLyo(int[] mista, int[] minne, Lauta lauta) {
        if (lauta.getNappula(mista).getNimi().equals("Sotilas") && Math.abs(mista[1] - mista[0]) == 2) {
            int[] enPassant = new int[2];
            if (lauta.getNappula(mista).getPuoli() == Nappula.Puoli.VALKOINEN) {
                enPassant[0] = mista[0];
                enPassant[1] = mista[1] + 1;
            } else if (lauta.getNappula(mista).getPuoli() == Nappula.Puoli.MUSTA) {
                enPassant[0] = mista[0];
                enPassant[1] = mista[1] - 1;
            }
            return enPassant;
        }
        return null;
    }

    public int heuristiikka(Solmu solmu) {
        //Ei vielä toteutettu
        return 0;
    }
}
