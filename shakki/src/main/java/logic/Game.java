package logic;

import logic.liikkuminen.Liikkuminen;
import logic.parser.Parser;
import components.Lauta;
import components.Nappula;
import history.*;
import variants.*;
import logic.parser.ParserReturn;

/**
 * Luokka toteuttaa shakkipelin.
 *
 * @author Oskari Kulmala
 */
public class Game {

    private Variant variant;
    private Lauta lauta;
    private TurnHistory historia;
    private Nappula.Puoli vuoro;
    private int[] enPassant;

//    private ui.UI ui;
    /**
     * Konstruktori pelille.
     *
     * @param variant pelin variantti
     */
    public Game(Variant variant) {
        this.variant = variant;
        variant.setUp();
        this.lauta = variant.getLauta();
        this.historia = new TurnHistory();
        this.vuoro = variant.getAloittaja();
        this.enPassant = null;
//        this.ui = new ui.UI(this);
    }

    /**
     * Metodi koittaa suorittaa käyttäjän antaman komennon.
     *
     * @param komento käyttäjän syöttämä komento.
     * @return onnistuiko komennon suoritus.
     *
     */
    public ParserReturn suoritaKomento(String komento) {
        ParserReturn parserTulos = Parser.parseCommand(komento, vuoro, lauta);
        if (!parserTulos.getError().isEmpty()) {
            return parserTulos;
        }
        int[][] startEndPoints = parserTulos.getCoordinates();
        int[] enPassantTemp = null;
        if (startEndPoints != null) {
            enPassantTemp = enPassantApu(startEndPoints);
            parserTulos = apusuoritus(parserTulos, komento, startEndPoints, enPassantTemp);
        }
        return parserTulos;
    }

    private int[] enPassantApu(int[][] startEndPoints) {
        int[] enPassantTemp = null;
        if (lauta.getNappula(startEndPoints[0]).onSotilas()
                && Math.abs(startEndPoints[0][1] - startEndPoints[1][1])
                == 2) {
            enPassantTemp = new int[2];
            enPassantTemp[0] = startEndPoints[0][0];
            if (vuoro == Nappula.Puoli.VALKOINEN) {
                enPassantTemp[1] = startEndPoints[0][1] + 1;
            } else {
                enPassantTemp[1] = startEndPoints[0][1] - 1;
            }
        }
        return enPassantTemp;
    }

    private ParserReturn apusuoritus(ParserReturn parserTulos, String komento, int[][] startEndPoints, int[] enPassantTemp) {
        Nappula nappula = lauta.getNappula(startEndPoints[0]);
        if (nappula.getPuoli() != vuoro) {
            return new ParserReturn("Ruudussa ei nappulaasi");
        }
        vuoronVaihtoJaHistorianJatko(parserTulos, komento, nappula, startEndPoints);
        if (!parserTulos.getError().isEmpty()) {
            return parserTulos;
        }

        if (enPassantTemp != null) {
            this.enPassant = enPassantTemp;
        } else {
            this.enPassant = null;
        }
        return parserTulos;
    }

    private ParserReturn vuoronVaihtoJaHistorianJatko(ParserReturn parserTulos, String komento, Nappula nappula, int[][] startEndPoints) {
        if (Liikkuminen.koitaSiirtyaTarkistaShakki(nappula, startEndPoints[1], lauta, vuoro, enPassant)) {
            if (vuoro == Nappula.Puoli.VALKOINEN) {
                vuoro = Nappula.Puoli.MUSTA;
            } else {
                vuoro = Nappula.Puoli.VALKOINEN;
            }
            if (!historia.getList().isEmpty()) {
                historia.jatkaVuoroHistoriaa(komento);
            } else {
                historia.addTurn(new Turn(historia.getVuoroNumero(), komento, ""));
            }
        } else {
            return new ParserReturn("(" + startEndPoints[0][0] + ","
                    + startEndPoints[0][1] + ")\n" + "-("
                    + startEndPoints[1][0] + "," + startEndPoints[1][1]
                    + ")\n" + "Laiton siirto");
        }
        return parserTulos;
    }

    /**
     * Metodi koittaa suorittaa käyttäjän antaman komennon.
     *
     * @param startEndPoints käyttäjän syöttämä komento sen alku- ja
     * loppupisteinä.
     * @return onnistuiko komennon suoritus.
     *
     */
    public ParserReturn suoritaKomento(int[][] startEndPoints) {
        String komento = Parser.parseToAlgebraicCommand(startEndPoints);
        return suoritaKomento(komento);
    }

    /**
     * Metodi tarkistaa, onko annetuissa koordinaateissa sotilasta (ja onko se
     * viimeisellä rivillä).
     *
     * @param koordinaatit missä saattaisi olla korotettava sotilas
     * @return korottuuko
     */
    public boolean tarkistaKorotus(int[] koordinaatit) {
        if (koordinaatit[1] == 0
                || koordinaatit[1] == lauta.getPituus() - 1) {
            if (lauta.getNappula(koordinaatit).onSotilas()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodi korottaa annetussa ruudussa olevan sotilaan annetuksi upseeriksi.
     *
     * @param koordinaatit missä korotetaan
     * @param korotetaanNimi mihin korotetaan
     */
    public void korota(int[] koordinaatit, String korotetaanNimi) {
        for (Nappula korotuskandidaatti : variant.getNappulaEsimerkit()) {
            //vuoro jo vaihdettu seuraavaan
            if (korotuskandidaatti.getNimi().equals(korotetaanNimi) && korotuskandidaatti.getPuoli() != vuoro) {
                LaudanMuutokset.korvaa(korotuskandidaatti.kopioi(), koordinaatit, lauta);
            }
        }
    }

    /**
     * getteri.
     *
     * @return lauta
     */
    public Lauta getLauta() {
        return lauta;
    }

    /**
     * getteri.
     *
     * @return historia
     */
    public TurnHistory getTurnHistory() {
        return historia;
    }

    /**
     * getteri.
     *
     * @return vuoro
     */
    public Nappula.Puoli getVuoro() {
        return vuoro;
    }

    /**
     * Poistaa vuorohistorian.
     */
    public void clearHistory() {
        this.historia.removeAfter(0);
    }

    /**
     * (Yrittää) suorittaa annetun vuorohistorian.
     *
     * @param history vuorohistoria TurnHistoryna
     * @return parserTulos kertoo kävikö virhe
     */
    public ParserReturn applyHistory(TurnHistory history) {
        variant.setUp();
        lauta = variant.getLauta();
        vuoro = variant.getAloittaja();
        System.out.println("history: " + history);
        String komento = "";
        ParserReturn parserTulos = new ParserReturn("error");
        for (Turn turn : history.getList()) {
            komento = turn.getWhiteMove();
            if (!komento.isEmpty()) {
//                System.out.println(komento);
                parserTulos = suoritaKomento(komento);
                if (!parserTulos.getError().isEmpty()) {
                    return parserTulos;
                }
            }

            komento = turn.getBlackMove();
            if (!komento.isEmpty()) {
//                System.out.println(komento);
                parserTulos = suoritaKomento(komento);
                if (!parserTulos.getError().isEmpty()) {
                    return parserTulos;
                }
            }
        }
        return parserTulos;
    }

    /**
     * (Yrittää) suorittaa annetun vuorohistorian.
     *
     * @param string merkkijonona
     * @return parserTulos kertoo kävikö virhe
     */
    public ParserReturn applyHistory(String string) {
        TurnHistory history = new TurnHistory(string);
        return applyHistory(history);
    }

    /**
     * Tarkistaa onko vuorossa oleva pelaaja matissa.
     *
     * @return onko matissa
     */
    public boolean tarkistaMatti() {
        if (Shakitus.matissa(lauta, vuoro, enPassant)) {
            return true;
        }
        return false;
    }

    /**
     * Tarkistaa onko vuorossa oleva pelaaja patissa.
     *
     * @return onko patissa
     */
    public boolean tarkistaPatti() {
        if (Shakitus.patissa(lauta, vuoro, enPassant)) {
            return true;
        }
        return false;
    }

    /**
     * Metodi vaihtaa pelin toiseen varianttiin.
     *
     * @param variant variantti johon vaihdetaan
     */
    public void vaihdaVariantti(Variant variant) {
        this.variant = variant;
        variant.setUp();
        this.lauta = variant.getLauta();
        clearHistory();
        this.vuoro = variant.getAloittaja();
        this.enPassant = null;
    }
}
