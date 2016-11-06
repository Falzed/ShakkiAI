package logic;

import logic.parser.Parser;
import components.Nappula;
import components.Tyhja;
import components.Lauta;

/**
 * Luokka tarjoaa staattisia metodeja nappuloitten asettamiseen laudalle, 
 * poistamiseen laudalta, ja siirtämiseen laudalla.
 * @author Oskari Kulmala
 */
public class LaudanMuutokset {
/**
     * Metodi asettaa annetun nappulan tyhjään ruutuun.
     *
     * @param nappula laudalle asetettava nappula
     * @param koordinaatit minne nappula asetetaan
     * @param lauta lauta jolla tämä tehdään
     * @return onnistuiko asetus
     */
    public static boolean aseta(Nappula nappula, int[] koordinaatit, Lauta lauta) {
        if (koordinaatit[0] < 0 || koordinaatit[0] >= lauta.getLeveys()
                || koordinaatit[1] < 0 || koordinaatit[1] >= lauta.getPituus()) {
            return false;
        }
        if (lauta.getNappula(koordinaatit).isEmpty()) {
            lauta.setNappula(nappula, koordinaatit);
            if (!nappula.isEmpty()) {
                int[] vanhaSijainti = nappula.getKoordinaatit();
                if (vanhaSijainti[0] != -1) {
                    lauta.setNappula(new Tyhja(), vanhaSijainti);
                }
                nappula.asetaKoordinaatit(koordinaatit);
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Metodi asettaa annetun nappulan tyhjään ruutuun.
     *
     * @param nappula asetettava nappula
     * @param algebraic algebralliset koordinaatit ruudulle minne nappula
     * asetetaan
     * @param lauta lauta jolla tämä tehdään
     * @return onnistuiko asetus
     */
    public static boolean aseta(Nappula nappula, String algebraic, Lauta lauta) {
        int[] koordinaatit = Parser.parseAlgebraic(algebraic);
        if (koordinaatit == null) {
            return false;
        }
        return aseta(nappula, koordinaatit, lauta);
    }

    /**
     * Metodi asettaa korvaa annetussa ruudussa olevan nappulan annetulla
     * nappulalla. Käytetty korottamisessa.
     *
     * @param nappula asetettava nappula
     * @param koordinaatit minne nappula asetetaan
     * @param lauta lauta jolla tämä tehdään
     * @return onnistuiko korvaus
     */
    public static boolean korvaa(Nappula nappula, int[] koordinaatit, Lauta lauta) {
        if (koordinaatit == null) {
            return false;
        }
        if (koordinaatit[0] < 0 || koordinaatit[0] >= lauta.getLeveys()
                || koordinaatit[1] < 0 || koordinaatit[1] >= lauta.getPituus()) {
            return false;
        }
        if (lauta.getNappula(koordinaatit).isEmpty()) {
            return false;
        }
        poista(koordinaatit, lauta);
        return aseta(nappula, koordinaatit, lauta);
    }

    /**
     * Metodi siirtää nappulan toiseen ruutuun ja syö siinä olevan nappulan.
     *
     * @param nappula siirrettävä nappula
     * @param koordinaatit ruutu missä nappula syö toisen nappulan
     * @param lauta lauta jolla tämä tehdään
     * @return onnistuiko siirto (ei voi syödä omaa)
     */
    public static boolean syo(Nappula nappula, int[] koordinaatit, Lauta lauta) {
        if (koordinaatit[0] < 0 || koordinaatit[0] >= lauta.getLeveys()
                || koordinaatit[1] < 0 || koordinaatit[1] >= lauta.getPituus()) {
            return false;
        }
        if (lauta.getNappula(koordinaatit).isEmpty()) {
            return false;
        } else if (nappula.getPuoli()
                == lauta.getNappula(koordinaatit).getPuoli()) {
            return false;
        } else {
            lauta.setNappula(nappula, koordinaatit);
            int[] vanhaSijainti = nappula.getKoordinaatit();
            lauta.setNappula(new Tyhja(), vanhaSijainti);
            nappula.asetaKoordinaatit(koordinaatit);
        }
        return true;
    }

    /**
     * Metodi siirtää nappulan toiseen ruutuun ja syö siinä olevan nappulan.
     *
     * @param nappula siirrettävä nappula
     * @param algebraic algebralliset koordinaatit ruudulle mistä syödään
     * nappula
     * @param lauta lauta jolla tämä tehdään
     * @return onnistuiko siirto (ei voi syödä omaa)
     */
    public static boolean syo(Nappula nappula, String algebraic, Lauta lauta) {
        int[] koordinaatit = Parser.parseAlgebraic(algebraic);
        if (koordinaatit == null) {
            return false;
        }
        if (koordinaatit[0] < 0 || koordinaatit[0] >= lauta.getLeveys()
                || koordinaatit[1] < 0 || koordinaatit[1] >= lauta.getPituus()) {
            return false;
        }
        return syo(nappula, koordinaatit, lauta);
    }

    /**
     * Metodi laittaa annettuun ruutuun Tyhja-olion. Tarkoitettu ohestalyönnille
     * mutta voi tarvittaessa käyttää muuhunkin muissa varianteissa.
     *
     * @param syotava koordinaatit ruudulle josta poistetaan nappula
     * @param lauta lauta jolla tämä tehdään
     */
    public static void poista(int[] syotava, Lauta lauta) {
        int[] koord = {-1, -1};
        lauta.getNappula(syotava).asetaKoordinaatit(koord);
        lauta.setNappula(new Tyhja(), syotava);
    }
}
