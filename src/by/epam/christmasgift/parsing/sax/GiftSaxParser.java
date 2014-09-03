package by.epam.christmasgift.parsing.sax;

import by.epam.christmasgift.candy.ChristmasGift;
import by.epam.christmasgift.exception.CandyParsingException;
import by.epam.christmasgift.parsing.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Created by Vika on 16.08.2014.
 */
public class GiftSaxParser implements Parser{

    public GiftSaxParser(){}

    public ChristmasGift parse(String pathToXML)
            throws CandyParsingException {
        ChristmasGift gift = new ChristmasGift();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException|SAXException e) {
            throw new CandyParsingException(e);
        }

        DefaultHandler handler = new CandyHandler(gift);
        try {
            parser.parse(pathToXML, handler);
        } catch (IOException e) {
            throw new CandyParsingException(e);
        } catch (SAXException e) {
            throw new CandyParsingException(e.getCause());
        }

        return gift;
    }
}
