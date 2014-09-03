package by.epam.christmasgift.parsing.sax;

import by.epam.christmasgift.candy.ChristmasGift;
import by.epam.christmasgift.parsing.sax.state.CaramelState;
import by.epam.christmasgift.parsing.sax.state.ChocolateState;
import by.epam.christmasgift.parsing.sax.state.LollipopState;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Vika on 16.08.2014.
 */
public class CandyHandler extends DefaultHandler {
    private static Logger logger = Logger.getLogger(CandyHandler.class);
    private CaramelState caramelState;
    private ChocolateState chocolateState;
    private LollipopState lollipopState;
    private DefaultHandler state = new DefaultHandler();

    public CandyHandler(ChristmasGift gift) {
        caramelState = new CaramelState(gift);
        chocolateState = new ChocolateState(gift);
        lollipopState = new LollipopState(gift);
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        if ("caramel".equalsIgnoreCase(qName.trim())){
            logger.info("Parsing caramel");
            state = caramelState;
        } else if ("chocolate".equalsIgnoreCase(qName.trim())){
            logger.info("Parsing chocolate");
            state = chocolateState;
        } else if ("lollipop".equalsIgnoreCase(qName.trim())){
            logger.info("Parsing lollipop");
            state = lollipopState;
        } else {
            state.startElement(uri, localName, qName, attributes);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        state.endElement(uri, localName, qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        state.characters(ch, start, length);
    }
}
