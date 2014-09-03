package by.epam.christmasgift.parsing.sax.state;

import by.epam.christmasgift.candy.ChristmasGift;
import by.epam.christmasgift.candy.builder.LollipopBuilder;
import by.epam.christmasgift.exception.CandyParsingException;
import by.epam.christmasgift.exception.CandyPropertyException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Vika on 16.08.2014.
 */
public class LollipopState extends DefaultHandler {
    ChristmasGift gift;
    LollipopBuilder current;
    String element = "";

    public LollipopState(ChristmasGift gift) {
        this.gift = gift;
        current = new LollipopBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        element = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if ("lollipop".equalsIgnoreCase(qName)){
            try {
                gift.addCandy(current.createCandy());
            } catch (CandyPropertyException e) {
                throw new SAXException(e);
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        try {
            switch (element) {
                case "protein": {
                    current.setProtein(Integer.parseInt(new String(ch, start, length)));
                    break;
                }
                case "fat": {
                    current.setFat(Integer.parseInt(new String(ch, start, length)));
                    break;
                }
                case "carbohydrate": {
                    current.setCarbohydrate(Integer.parseInt(new String(ch, start, length)));
                    break;
                }
                case "kilo-calories": {
                    current.setKiloCalories(Integer.parseInt(new String(ch, start, length)));
                    break;
                }
                case "weight": {
                    current.setWeight(Integer.parseInt(new String(ch, start, length)));
                    break;
                }
                case "sugar-content": {
                    current.setSugarContent(Integer.parseInt(new String(ch, start, length)));
                    break;
                }
                case "flavor": {
                    current.setFlavor(new String(ch, start, length));
                    break;
                }
                default: {
                    return;
                }
            }
        } catch (CandyPropertyException e) {
            throw new SAXException(e);
        }
        element = "";
    }
}
