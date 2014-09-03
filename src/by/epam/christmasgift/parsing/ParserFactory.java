package by.epam.christmasgift.parsing;

import by.epam.christmasgift.exception.FactoryException;
import by.epam.christmasgift.parsing.dom.GiftDomParser;
import by.epam.christmasgift.parsing.sax.GiftSaxParser;
import by.epam.christmasgift.parsing.stax.GiftStaxParser;

/**
 * Created by Vika on 18.08.2014.
 */
public class ParserFactory {

    public Parser createParser(ParserType type) throws FactoryException {
        switch (type){
            case DOM:{
                return new GiftDomParser();
            }
            case SAX:{
                return new GiftSaxParser();
            }
            case STAX:{
                return new GiftStaxParser();
            }
            default:{
                throw new FactoryException("Wrong type");
            }
        }
    }

    public enum ParserType{
        DOM, SAX, STAX
    }
}
