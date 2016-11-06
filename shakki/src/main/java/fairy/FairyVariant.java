package fairy;

import components.*;
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import java.util.HashMap;
import variants.Variant;

/**
 * Luokka toteuttaa halutun shakkivariantin. Variantti määritellään
 * xml-tiedostossa.
 *
 * @author Oskari Kulmala
 */
public class FairyVariant implements Variant {

    private ArrayList<Nappula> pieces;
    private Lauta lauta;
    private ArrayList<Lauta> laudat;
    private Nappula.Puoli aloittaja;
    private String[] nappuloittenNimet;
    private Nappula[] nappulaEsimerkit;
    private int lautojenMaara;
    private int lautojenLeveys;
    private int lautojenPituus;
    private ArrayList<Nappula> nappulat;
    private HashMap<Nappula, ArrayList<int[]>> nappuloittenKoordinaatit;
    private ArrayList<HashMap> stuff;
    BufferedReader lukija;

    /**
     * Variantin luonti xml-tieddoston perusteella.
     *
     * @param file tiedosto joka määrittelee variantin
     */
    public FairyVariant(File file) {
        stuff = FairyVariantReader.readXml(file);

        System.out.println(stuff);
        this.lautojenLeveys = Integer.parseInt((String) stuff.get(0).get("Leveys"));
        this.lautojenPituus = Integer.parseInt((String) stuff.get(0).get("Pituus"));

        ArrayList<Nappula> esimerkit = parseEsimerkit(stuff);
        ArrayList<String> nimet = parseNimet(stuff);

        this.nappuloittenNimet = new String[nimet.size()];
        for (int i = 0; i < nimet.size(); i++) {
            this.nappuloittenNimet[i] = nimet.get(i);
        }
        this.nappulaEsimerkit = new Nappula[nimet.size()];
        for (int i = 0; i < nimet.size(); i++) {
            this.nappulaEsimerkit[i] = esimerkit.get(i);
        }
        alustaNappuloittenKoordinaatit(esimerkit);

    }

    private void alustaNappuloittenKoordinaatit(ArrayList<Nappula> esimerkit) {
        this.nappuloittenKoordinaatit = new HashMap<>();
        for (HashMap hash : stuff) {
            if (((String) hash.get("nappulanNimi")) != null) {
                int[] koordinaatit = new int[2];
                koordinaatit[0] = Integer.parseInt((String) hash.get("xkoord"));
                koordinaatit[1] = Integer.parseInt((String) hash.get("ykoord"));
                ArrayList<Integer> indexes = new ArrayList<>();

                for (Nappula nappula : esimerkit) {
                    if (nappula.getNimi().equals(((String) hash.get("nappulanNimi")))
                            && nappula.getPuoliString().equalsIgnoreCase((String) hash.get("nappulanPuoli"))) {
                        indexes.add(esimerkit.indexOf(nappula));
                        break;
                    }
                }
                alustaNappuloittenKoordinaatit(esimerkit, indexes, koordinaatit);
            }
        }
    }

    private void alustaNappuloittenKoordinaatit(ArrayList<Nappula> esimerkit, ArrayList<Integer> indexes, int[] koordinaatit) {
        for (int index : indexes) {
            if (esimerkit.get(index) instanceof FairyPiece) {
                FairyPiece nappula = (FairyPiece) esimerkit.get(index);
                ArrayList<int[]> lista = this.nappuloittenKoordinaatit.get(nappula);
                if (lista == null) {
                    lista = new ArrayList<>();
                }
                lista.add(koordinaatit);
                this.nappuloittenKoordinaatit.put(nappula, lista);
            } else {
                ArrayList<int[]> lista = this.nappuloittenKoordinaatit.get(esimerkit.get(index));
                if (lista == null) {
                    lista = new ArrayList<>();
                }
                lista.add(koordinaatit);
                this.nappuloittenKoordinaatit.put(esimerkit.get(index), lista);
            }
        }
    }

    private ArrayList<int[]> liikkuneet(ArrayList<Nappula> esimerkit) {
        ArrayList<int[]> liikkuneet = new ArrayList<>();
        for (HashMap hash : stuff) {
            if (((String) hash.get("liikkunut")) != null) {
                int[] koordinaatit = new int[2];
                koordinaatit[0] = Integer.parseInt((String) hash.get("xkoord"));
                koordinaatit[1] = Integer.parseInt((String) hash.get("ykoord"));
                liikkuneet.add(koordinaatit);
            }
        }
        return liikkuneet;
    }

    private ArrayList<Nappula> parseEsimerkit(ArrayList<HashMap> stuff) {
        ArrayList<Nappula> esimerkit = new ArrayList<>();
        for (HashMap hash : stuff) {
            if (((Nappula) hash.get("nappula")) != null) {
                if (hash.get("nappula") instanceof FairyPiece) {
                    FairyPiece nappula = (FairyPiece) hash.get("nappula");
                    esimerkit.add(nappula);
                    esimerkit.add(new FairyPiece(nappula, Nappula.Puoli.MUSTA));
                } else {
                    Nappula nappula = (Nappula) hash.get("nappula");
                    esimerkit.add(nappula);
                    Nappula nappula2 = nappula.kopioi();
                    nappula2.setPuoli(Nappula.Puoli.MUSTA);
                    esimerkit.add(nappula2);
                }
            }
        }
        return esimerkit;
    }

    private ArrayList<String> parseNimet(ArrayList<HashMap> stuff) {
        ArrayList<String> nimet = new ArrayList<>();
        for (HashMap hash : stuff) {
            if (((Nappula) hash.get("nappula")) != null) {
                if (hash.get("nappula") instanceof FairyPiece) {
                    FairyPiece nappula = (FairyPiece) hash.get("nappula");
                    nimet.add(nappula.getNimi());
                } else {
                    Nappula nappula = (Nappula) hash.get("nappula");
                    nimet.add(nappula.getNimi());
                }
            }
        }
        return nimet;
    }

    @Override
    public Nappula[] getNappulaEsimerkit() {
        return this.nappulaEsimerkit;
    }

    @Override
    public Lauta getLauta() {
        return this.lauta;
    }

    @Override
    public String[] getNappuloittenNimet() {
        return this.nappuloittenNimet;
    }

    @Override
    public void setUp() {
        lauta = new Lauta(this.lautojenLeveys, this.lautojenPituus);
        lauta.alustaLauta();
        ArrayList<int[]> liikkuneet = liikkuneet(parseEsimerkit(stuff));
        for (Nappula nappula : this.nappuloittenKoordinaatit.keySet()) {
            for (int[] koordinaatit : nappuloittenKoordinaatit.get(nappula)) {
                logic.LaudanMuutokset.aseta(nappula.kopioi(), koordinaatit, lauta);
                for (int[] liikkunut : liikkuneet) {
                    if (Arrays.equals(liikkunut, koordinaatit)) {
                        lauta.getNappula(koordinaatit).setLiikkunut();
                        break;
                    }
                }
            }
        }
    }
}
