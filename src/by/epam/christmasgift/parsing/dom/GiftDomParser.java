package by.epam.christmasgift.parsing.dom;

import by.epam.christmasgift.candy.*;
import by.epam.christmasgift.exception.CandyParsingException;
import by.epam.christmasgift.exception.CandyPropertyException;
import by.epam.christmasgift.parsing.Parser;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Vika on 31.07.2014.
 */
public class GiftDomParser implements Parser{
    private static Logger logger = Logger.getLogger(GiftDomParser.class);

    public GiftDomParser(){};

    public ChristmasGift parse(String pathToXML)
            throws CandyParsingException {
        File fXmlFile = new File(pathToXML);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new CandyParsingException(e);
        }
        Document doc = null;
        try {
            doc = dBuilder.parse(fXmlFile);
        } catch (SAXException|IOException e) {
            throw new CandyParsingException(e);
        }

        ChristmasGift resultGift = new ChristmasGift();

        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getChildNodes().item(0).getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            String nodeName = node.getNodeName();
            switch (nodeName) {
                case "caramel": {
                    resultGift.addCandy(parseCaramel((Element) node));
                    break;
                }
                case "chocolate": {
                    resultGift.addCandy(parseChocolate((Element) node));
                    break;
                }
                case "lollipop": {
                    resultGift.addCandy(parseLollipop((Element) node));
                    break;
                }
                default:{
                    continue;
                }
            }
        }
        return resultGift;
    }

    private Caramel parseCaramel(Element element) throws CandyParsingException {
        logger.info("Parsing Caramel");
        int protein = Integer.parseInt(element.getElementsByTagName("protein")
                .item(0).getTextContent());
        int fat = Integer.parseInt(element.getElementsByTagName("fat").item(0)
                .getTextContent());
        int carbohydrate = Integer.parseInt(element
                .getElementsByTagName("carbohydrate").item(0).getTextContent());
        int kiloCalories = Integer.parseInt(element
                .getElementsByTagName("kilo-calories").item(0).getTextContent());
        int weight = Integer.parseInt(element.getElementsByTagName("weight")
                .item(0).getTextContent());
        int sugarContent = Integer.parseInt(element
                .getElementsByTagName("sugar-content").item(0).getTextContent());
        Caramel.CaramelType caramelType = Caramel.CaramelType.valueOf(element
                .getElementsByTagName("caramel-type").item(0).getTextContent()
                .toUpperCase());
        try {
            return new Caramel(protein, fat, carbohydrate, kiloCalories, weight,
                    sugarContent, caramelType);
        } catch (CandyPropertyException e) {
            throw new CandyParsingException(e);
        }
    }

    private Chocolate parseChocolate(Element element) throws CandyParsingException {
        logger.info("Parsing Chocolate");
        int protein = Integer.parseInt(element.getElementsByTagName("protein")
                .item(0).getTextContent());
        int fat = Integer.parseInt(element.getElementsByTagName("fat").item(0)
                .getTextContent());
        int carbohydrate = Integer.parseInt(element
                .getElementsByTagName("carbohydrate").item(0).getTextContent());
        int kiloCalories = Integer.parseInt(element
                .getElementsByTagName("kilo-calories").item(0).getTextContent());
        int weight = Integer.parseInt(element.getElementsByTagName("weight")
                .item(0).getTextContent());
        int sugarContent = Integer.parseInt(element
                .getElementsByTagName("sugar-content").item(0).getTextContent());
        Chocolate.ChocolateType chocolateType = Chocolate.ChocolateType
                .valueOf(element.getElementsByTagName("chocolate-type").item(0)
                        .getTextContent().toUpperCase());
        Chocolate.StuffingType stuffingType = Chocolate.StuffingType.valueOf(
                element.getElementsByTagName("stuffing-type").item(0)
                        .getTextContent().toUpperCase());
        try {
            return new Chocolate(protein, fat, carbohydrate, kiloCalories, weight,
                    sugarContent, chocolateType, stuffingType);
        } catch (CandyPropertyException e) {
            throw new CandyParsingException(e);
        }
    }

    private Lollipop parseLollipop(Element element) throws CandyParsingException {
        logger.info("Parsing Lollipop");
        int protein = Integer.parseInt(element.getElementsByTagName("protein")
                .item(0).getTextContent());
        int fat = Integer.parseInt(element.getElementsByTagName("fat").item(0)
                .getTextContent());
        int carbohydrate = Integer.parseInt(element
                .getElementsByTagName("carbohydrate").item(0).getTextContent());
        int kiloCalories = Integer.parseInt(element
                .getElementsByTagName("kilo-calories").item(0).getTextContent());
        int weight = Integer.parseInt(element.getElementsByTagName("weight")
                .item(0).getTextContent());
        int sugarContent = Integer.parseInt(element
                .getElementsByTagName("sugar-content").item(0).getTextContent());
        String flavor = element.getElementsByTagName("flavor").item(0).getTextContent();
        try {
            return new Lollipop(protein, fat, carbohydrate, kiloCalories, weight,
                    sugarContent, flavor);
        } catch (CandyPropertyException e) {
            throw new CandyParsingException(e);
        }
    }
}
