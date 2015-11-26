package eltiempo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    public  String nombreCiudad;
    public  ArrayList<String> dias = new <String>ArrayList();
    public  ArrayList<String> temperatura = new <String> ArrayList();
    public  ArrayList<String> temperaturaMax = new <String>ArrayList();
    public  ArrayList<String> temperaturaMin = new <String>ArrayList();
    public  ArrayList<String> nubes = new <String>ArrayList();
    public static final File XML = new File("forecast.xml");

    /**
     *
     * @return nombreCiudad
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public String getNombreCiudad() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(XML);

        nombreCiudad = doc.getElementsByTagName("name").item(0).getTextContent();
        return nombreCiudad;
    }

    /**
     *
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public void anadirInfoArrays() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(XML);

        NodeList nList = doc.getElementsByTagName("time");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Element elementos = (Element) nList.item(temp);
            dias.add(elementos.getAttributes().getNamedItem("day").getNodeValue());
            temperatura.add(elementos.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("day").getNodeValue());
            temperaturaMin.add(elementos.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("min").getNodeValue());
            temperaturaMax.add(elementos.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("max").getNodeValue());
            nubes.add(elementos.getElementsByTagName("clouds").item(0).getAttributes().getNamedItem("value").getNodeValue());
        }
    }

    /**
     *
     * @param pos
     * @return
     */
    public String toPrevision(int pos){
        return nubes.get(pos);
    }

    /**
     *
     * @param pos
     * @return descrpicion
     */
    public String toString(int pos) {
        return  "Dia = " + dias.get(pos) + "\n" +
                "Temperatura = " + temperatura.get(pos) + "\n" +
                "TemperaturaMax = " + temperaturaMax.get(pos) +"\n" +
                "TemperaturaMin = " + temperaturaMin.get(pos) +"\n" +
                "Nubes = " + nubes.get(pos);
    }
}
