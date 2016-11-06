package fairy;

import components.Kuningas;
import components.Kuningatar;
import components.Lahetti;
import components.Nappula;
import components.Ratsu;
import components.Sotilas;
import components.Torni;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FairyVariantReader {

    /**
     * Metodi lukee xml-tiedostosta tarvittavat tiedot, joilla voidaan muodostaa
     * variantti.
     *
     * @param file tiedosto joka määrittelee variantin
     * @return ArrayList jossa tarvittavat tiedot (HashMapeissa)
     */
    public static ArrayList readXml(File file) {
        ArrayList<HashMap> array = new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = null;
            doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeLista = doc.getElementsByTagName("lauta");
            array = parseLeveysPituus(nodeLista, array);
            nodeLista = doc.getElementsByTagName("nappula");
            array = getNappulat(nodeLista, array);
            nodeLista = doc.getElementsByTagName("nappulanPaikka");
            array = parseLaitettavatNappulat(nodeLista, array);
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        return array;
    }

    private static ArrayList<HashMap> parseLaitettavatNappulat(NodeList nodeLista, ArrayList<HashMap> array) {
        for (int temp = 0; temp < nodeLista.getLength(); temp++) {
            Node node = nodeLista.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                HashMap<String, Object> hash = new HashMap<>();
                hash.put("nappulanNimi", element.getElementsByTagName("nappulanNimi").item(0).getTextContent());
                hash.put("nappulanPuoli", element.getElementsByTagName("nappulanPuoli").item(0).getTextContent());
                hash.put("xkoord", element.getElementsByTagName("xkoord").item(0).getTextContent());
                hash.put("ykoord", element.getElementsByTagName("ykoord").item(0).getTextContent());
                if (element.getElementsByTagName("liikkunut").getLength() > 0) {
                    hash.put("liikkunut", element.getElementsByTagName("liikkunut").item(0).getTextContent());
                }
                array.add(hash);
            }
        }
        return array;
    }

    private static ArrayList<HashMap> parseLeveysPituus(NodeList nodeLista, ArrayList<HashMap> array) {
        for (int temp = 0; temp < nodeLista.getLength(); temp++) {
            Node node = nodeLista.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                HashMap<String, String> hash = new HashMap<>();
                hash.put("Leveys", element.getElementsByTagName("leveys").item(0).getTextContent());
                hash.put("Pituus", element.getElementsByTagName("pituus").item(0).getTextContent());
                array.add(hash);
            }
        }
        return array;
    }

    private static ArrayList<HashMap> getNappulat(NodeList nodeLista, ArrayList<HashMap> array) {
        for (int temp = 0; temp < nodeLista.getLength(); temp++) {
            Node node = nodeLista.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                array = parseNappulat(element, array);
            }
        }
        return array;
    }

    private static ArrayList<HashMap> parseNappulat(Element element, ArrayList<HashMap> array) {
        HashMap<String, Object> hash = new HashMap<>();
        switch (element.getElementsByTagName("nimi").item(0).getTextContent()) {
            case "Sotilas":
                hash.put("nappula", new Sotilas("valkoinen"));
                array.add(hash);
                break;
            case "Kuningas":
                hash.put("nappula", new Kuningas("valkoinen"));
                array.add(hash);
                break;
            case "Kuningatar":
                hash.put("nappula", new Kuningatar("valkoinen"));
                array.add(hash);
                break;
            case "Ratsu":
                hash.put("nappula", new Ratsu("valkoinen"));
                array.add(hash);
                break;
            case "Torni":
                hash.put("nappula", new Torni("valkoinen"));
                array.add(hash);
                break;
            case "Lahetti":
                hash.put("nappula", new Lahetti("valkoinen"));
                array.add(hash);
                break;
            default:
                hash.put("nimi", element.getElementsByTagName("nimi").item(0).getTextContent());
                hash.put("notaatioMerkki", element.getElementsByTagName("notaatioMerkki").item(0).getTextContent());
                hash.put("valkoinenMerkki", element.getElementsByTagName("valkoinenMerkki").item(0).getTextContent());
                hash.put("mustaMerkki", element.getElementsByTagName("mustaMerkki").item(0).getTextContent());
                ArrayList<String> liikkumiset = new ArrayList<>();
                for (int i = 0; i < element.getElementsByTagName("liikkumistyyppi").getLength(); i++) {
                    liikkumiset.add(element.getElementsByTagName("liikkumistyyppi").item(i).getTextContent());
                }
                hash.put("liikkumiset", liikkumiset);
                FairyPiece piece = new FairyPiece(hash, Nappula.Puoli.VALKOINEN);
                hash = new HashMap<>();
                hash.put("nappula", piece);
                array.add(hash);
                break;
        }
        return array;
    }
}
