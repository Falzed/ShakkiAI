package AI;

import components.Lauta;
import components.Nappula;
import java.util.ArrayList;
import java.util.Arrays;
import static logic.Shakitus.shakissa;
import logic.liikkuminen.Liikkuminen;
import logic.liikkuminen.VoikoSiirtya;

/**
 * Tekoäly
 *
 * @author Oskari Kulmala
 */
public class Tekoaly {

    private Nappula.Puoli puoli;
    private static final int lepotilahakuSyvyys = 0;

    private static final int VAPAA_LINJA_BONUS = 500;
    private static final int KAKSOISSOTILAS_MALUS = 300;
    private static final int KOLMOIS_TAI_ENEMMAN_MALUS_PER = 200;

    /**
     * Konstruktorille annetaan tiedoksi pelaako AI valkoista vai mustaa
     *
     * @param puoli valkoinen/musta
     */
    public Tekoaly(Nappula.Puoli puoli) {
        this.puoli = puoli;
    }

    /**
     * Lepotilahaku.
     *
     * @param solmu tutkittava solmu
     * @param syvyys kuinka syvälle tutkitaan
     * @param alpha
     * @param beta
     * @param maksimoitavaPelaaja onko solmussa AIn vuoro
     * @return
     */
    public int quiescenceSearchAlphaBeta(Solmu solmu, int syvyys, int alpha, int beta, boolean maksimoitavaPelaaja) {
        if (hiljainen(solmu)) {
            return heuristiikka(solmu);
        }

        int arvio = 0;
        ArrayList<Solmu> lapset = lapset(solmu);
        if (syvyys == 0 || lapset.isEmpty()) {
//            System.out.println(Arrays.toString(solmu.getEdellinenKomento()[0])+" to "+Arrays.toString(solmu.getEdellinenKomento()[1]));
//            System.out.println(heuristiikka(solmu));
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

    /**
     * Alpha-beta karsinta
     *
     * @param solmu tutkittava solmu
     * @param syvyys kuinka syvälle tutkitaan
     * @param alpha
     * @param beta
     * @param maksimoitavaPelaaja onko solmussa AIn vuoro
     * @return
     */
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

    /**
     * Metodi, joka tutkii onko annettu solmu tarkemman tutkimisen arvoinen
     *
     * @param solmu tutkittava solmu
     * @return
     */
    public boolean hiljainen(Solmu solmu) {
        //Ei toteutettu
        return false;
    }

    /**
     * Metodi joka generoi annetun solmun kaikki lapset (eli mitkä siirrot voi
     * tehdä)
     *
     * @param solmu parent
     * @return lista lapsisolmuista
     */
    public ArrayList<Solmu> lapset(Solmu solmu) {
        Lauta lauta = solmu.getLauta();
        Nappula.Puoli lapsiVuoro;
        if (solmu.getVuoro() == Nappula.Puoli.VALKOINEN) {
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
                            int[][] komento = new int[2][2];
                            komento[0][0] = nappula.getKoordinaatit()[0];
                            komento[0][1] = nappula.getKoordinaatit()[1];
                            komento[1][0] = minne[0];
                            komento[1][1] = minne[1];
                            lapset.add(new Solmu(testLauta, uusiEnPassant, lapsiVuoro, komento));
                        }
                    }
                }
            }
        }
        return lapset;
    }

    /**
     * Apufunktio lapsien generoimiselle
     */
    private int[] ohestaLyo(int[] mista, int[] minne, Lauta lauta) {
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

    /**
     * Heuristiikka solmun arvioimiselle
     *
     * @param solmu tutkittava solmu
     * @return arvio
     */
    public int heuristiikka(Solmu solmu) {
        int arvo = 0;

        if (logic.Shakitus.matissa(solmu.getLauta(), Nappula.Puoli.VALKOINEN, solmu.getEnPassant())) {
            if (solmu.getVuoro() == Nappula.Puoli.VALKOINEN) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        if (logic.Shakitus.matissa(solmu.getLauta(), Nappula.Puoli.MUSTA, solmu.getEnPassant())) {
            if (solmu.getVuoro() == Nappula.Puoli.VALKOINEN) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        }
        arvo = materiaaliMuutos(arvo, solmu);
        arvo = sotilasMuunnokset(arvo, solmu);
        arvo = upseerienKehitys(arvo, solmu);
        return arvo;
    }

    /**
     * Apufunktio heuristiikalle, laskee molempien puolien materiaalin
     */
    public int materiaaliMuutos(int arvo, Solmu solmu) {
        for (int i = 0; i < solmu.getLauta().getLeveys(); i++) {
            for (int j = 0; j < solmu.getLauta().getLeveys(); j++) {
                int[] koordinaatit = new int[2];
                koordinaatit[0] = i;
                koordinaatit[1] = j;
                Nappula nappula = solmu.getLauta().getNappula(koordinaatit);
                if (nappula.isEmpty()) {
                    continue;
                }
                int muutos = 0;
                switch (nappula.getNimi()) {
                    case "Sotilas":
                        muutos += 1000;
                        break;
                    case "Ratsu":
                    case "Lahetti":
                        muutos += 3000;
                        break;
                    case "Torni":
                        muutos += 5000;
                        break;
                    case "Kuningatar":
                        muutos += 9000;
                        break;
                }
                if (nappula.getPuoli() == this.puoli) {
                    arvo += muutos;
                } else {
                    arvo -= muutos;
                }
            }
        }
        return arvo;
    }

    /**
     * Apufunktio heuristiikalle, muuttaa arviota sotilaitten position
     * perusteella
     */
    public int sotilasMuunnokset(int arvo, Solmu solmu) {
        for (int i = 0; i < solmu.getLauta().getLeveys(); i++) {
            int[] sotilaita = sotilaitaLinjalla(i, solmu.getLauta(), solmu.getVuoro());
            if (sotilaita[0] > 0 && sotilaita[1] == 0) {
                arvo += VAPAA_LINJA_BONUS;
            }
            if (sotilaita[0] > 1) {
                arvo -= KAKSOISSOTILAS_MALUS;
                if (sotilaita[0] > 2) {
                    for (int j = 2; j < sotilaita[0]; j++) {
                        arvo -= KOLMOIS_TAI_ENEMMAN_MALUS_PER;
                    }
                }
            }
        }
        for (int i = 0; i < solmu.getLauta().getLeveys(); i++) {
            for (int j = 0; j < solmu.getLauta().getLeveys(); j++) {
                int[] koordinaatit = new int[2];
                koordinaatit[0] = i;
                koordinaatit[1] = j;
                Nappula nappula = solmu.getLauta().getNappula(koordinaatit);
                if (nappula.onSotilas()) {
                    if (nappula.getPuoli() == puoli) {
                        if (nappula.getPuoli().equals(Nappula.Puoli.VALKOINEN)) {
                            float kerroin = 4.0f;
                            kerroin -= Math.abs((double) (solmu.getLauta().getLeveys() - 1) / 2 - i);
                            if (j == solmu.getLauta().getPituus() / 2 - 1) {
                                arvo += (int) (15 * kerroin);
                            }
                            if (j >= solmu.getLauta().getPituus() / 2) {
                                arvo += (int) (25 * kerroin);
                            }
                            if (j == solmu.getLauta().getPituus() - 3) {
                                arvo += (int) (35 * kerroin);
                            }
                            if (j == solmu.getLauta().getPituus() - 2) {
                                arvo += (int) (50 * kerroin);
                            }
                            if (j == solmu.getLauta().getPituus() - 1) {
                                arvo += 9000;
                            }
                        } else {
                            float kerroin = 4.0f;
                            kerroin -= Math.abs((double) (solmu.getLauta().getLeveys() - 1) / 2 - i);
                            if (j == solmu.getLauta().getPituus() / 2) {
                                arvo += (int) (15 * kerroin);
                            }
                            if (j < solmu.getLauta().getPituus() / 2) {
                                arvo += (int) (25 * kerroin);
                            }
                            if (j == 2) {
                                arvo += (int) (35 * kerroin);
                            }
                            if (j == 1) {
                                arvo += (int) (50 * kerroin);
                            }
                            if (j == 0) {
                                arvo += 9000;
                            }
                        }
                    } else {
                        if (nappula.getPuoli().equals(Nappula.Puoli.VALKOINEN)) {
                            float kerroin = 4.0f;
                            kerroin -= Math.abs((double) (solmu.getLauta().getLeveys() - 1) / 2 - i);
                            if (j == solmu.getLauta().getPituus() / 2 - 1) {
                                arvo -= (int) (15 * kerroin);
                            }
                            if (j >= solmu.getLauta().getPituus() / 2) {
                                arvo -= (int) (25 * kerroin);
                            }
                            if (j == solmu.getLauta().getPituus() - 2) {
                                arvo -= (int) (35 * kerroin);
                            }
                            if (j == solmu.getLauta().getPituus() - 1) {
                                arvo -= (int) (50 * kerroin);
                            }
                        } else {
                            float kerroin = 4.0f;
                            kerroin -= Math.abs((double) (solmu.getLauta().getLeveys() - 1) / 2 - i);
                            if (j == solmu.getLauta().getPituus() / 2) {
                                arvo -= (int) (15 * kerroin);
                            }
                            if (j < solmu.getLauta().getPituus() / 2) {
                                arvo -= (int) (25 * kerroin);

                            }
                            if (j == 2) {
                                arvo -= (int) (35 * kerroin);
                            }
                            if (j == 1) {
                                arvo -= (int) (50 * kerroin);
                            }
                        }
                    }
                }
            }
        }

        return arvo;
    }

    /**
     * Apufunktio, laskee omien ja vastustajan sotilaitten määrän linjalla
     * (esim. e)
     */
    private int[] sotilaitaLinjalla(int linja, Lauta lauta, Nappula.Puoli puoli) {
        int omiaSotilaita = 0;
        int vastustajanSotilaita = 0;
        for (int i = 0; i < lauta.getPituus(); i++) {
            int[] koordinaatit = new int[2];
            koordinaatit[0] = linja;
            koordinaatit[1] = i;
            if (lauta.getNappula(koordinaatit).onSotilas()) {
                if (lauta.getNappula(koordinaatit).getPuoli() == puoli) {
                    omiaSotilaita++;
                } else {
                    vastustajanSotilaita++;
                }
            }
        }
        int[] sotilaita = {omiaSotilaita, vastustajanSotilaita};
        return sotilaita;
    }

    /**
     * Apufunktio heuristiikalle, muuttaa arviota upseerien position perusteella
     */
    private int upseerienKehitys(int arvo, Solmu solmu) {
        for (int i = 0; i < solmu.getLauta().getLeveys(); i++) {
            for (int j = 0; j < solmu.getLauta().getLeveys(); j++) {
                int[] koordinaatit = new int[2];
                koordinaatit[0] = i;
                koordinaatit[1] = j;
                Nappula nappula = solmu.getLauta().getNappula(koordinaatit);
                if (!nappula.isEmpty() && !nappula.onSotilas()) {
                    switch (nappula.getNimi()) {
                        case "Ratsu":
                            float kerroin = 5; //n. 3.5*sqrt(2)=3.5^2+3.5^2
                            float etaisyysX =(solmu.getLauta().getLeveys()-1)/2;
                            etaisyysX = etaisyysX-i;
                            
                            float etaisyysY =(solmu.getLauta().getPituus()-1)/2;
                            etaisyysY = etaisyysY-j;
                            
                            etaisyysX = etaisyysX * etaisyysX;
                            etaisyysY = etaisyysY * etaisyysY;
                            
                            kerroin -= etaisyysX;
                            kerroin -= etaisyysY;
                            
                            arvo += (int) 100 * kerroin;
                            break;
                        case "Lahetti":
                            //TODO
                            break;
                        case "Torni":
                            //TODO
                            break;
                        case "Kuningatar":
                            //TODO
                            break;
                    }
                }
            }
        }
        return arvo;
    }

    /**
     * Palauttaa siirron, joka on tekoälyn mielestä paras
     *
     * @param lauta pelilauta
     * @param enPassant mistä voi ohestalyödä (jos voi)
     * @return siirto
     */
    public int[][] seuraavaKomento(Lauta lauta, int[] enPassant) {
        int[][] komento = new int[2][2];
        int arvo = Integer.MIN_VALUE;

        Solmu solmu = new Solmu(lauta, enPassant, this.puoli, null);
        ArrayList<Solmu> lapset = lapset(solmu);
        komento = lapset.get(0).getEdellinenKomento();
//        for (Solmu lapsi : lapset) {
//            System.out.println(Arrays.toString(lapsi.getEdellinenKomento()[0]) + " to " + Arrays.toString(lapsi.getEdellinenKomento()[1]));
//        }
        int i = 0;
        for (Solmu lapsi : lapset) {
            System.out.println("Seuraava solmu" + i + "/" + lapset.size() + "...");
            int arvoEhdokas = this.alphaBetaNormal(lapsi, 2, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
            if (arvoEhdokas > arvo) {
                arvo = arvoEhdokas;
                komento = lapsi.getEdellinenKomento();
            }

            System.out.println(Arrays.toString(lapsi.getEdellinenKomento()[0]) + " to " + Arrays.toString(lapsi.getEdellinenKomento()[1]));
            System.out.println(arvoEhdokas);

//            System.out.println("uusi komentoehdokas:" + Arrays.toString(lapsi.getEdellinenKomento()[0]) + " to " + Arrays.toString(lapsi.getEdellinenKomento()[1]));
//            System.out.println("arvoEhdokas: " + arvoEhdokas);
            i++;
        }

        return komento;
    }
}
