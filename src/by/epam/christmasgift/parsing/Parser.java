package by.epam.christmasgift.parsing;

import by.epam.christmasgift.candy.ChristmasGift;
import by.epam.christmasgift.exception.CandyParsingException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Vika on 18.08.2014.
 */
public interface Parser {
    public ChristmasGift parse(String pathToXML)
            throws CandyParsingException;
}
