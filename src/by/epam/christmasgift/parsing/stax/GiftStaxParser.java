package by.epam.christmasgift.parsing.stax;

import by.epam.christmasgift.candy.Caramel;
import by.epam.christmasgift.candy.Chocolate;
import by.epam.christmasgift.candy.ChristmasGift;
import by.epam.christmasgift.candy.Lollipop;
import by.epam.christmasgift.candy.builder.CaramelBuilder;
import by.epam.christmasgift.candy.builder.ChocolateBuilder;
import by.epam.christmasgift.candy.builder.LollipopBuilder;
import by.epam.christmasgift.exception.CandyParsingException;
import by.epam.christmasgift.exception.CandyPropertyException;
import by.epam.christmasgift.parsing.Parser;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;

/**
 * Created by Vika on 17.08.2014.
 */
public class GiftStaxParser implements Parser{
    private static Logger logger = Logger.getLogger(GiftStaxParser.class);

    public GiftStaxParser(){}

    public ChristmasGift parse(String pathToXML) throws CandyParsingException {
        ChristmasGift gift = new ChristmasGift();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try (InputStream stream = new FileInputStream(pathToXML)){
            XMLEventReader reader = factory.createXMLEventReader(stream);
            while (reader.hasNext()){
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()){
                    StartElement startElement = event.asStartElement();
                    if ("chocolate".equalsIgnoreCase(startElement.getName().getLocalPart())){
                        gift.addCandy(parseChocolate(reader));
                    } else if ("caramel".equalsIgnoreCase(startElement.getName().getLocalPart())){
                        gift.addCandy(parseCaramel(reader));
                    } else if ("lollipop".equalsIgnoreCase(startElement.getName().getLocalPart())){
                        gift.addCandy(parseLollipop(reader));
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            throw new CandyParsingException(e);
        }
        return gift;
    }

    private Chocolate parseChocolate(XMLEventReader reader)
            throws XMLStreamException, CandyParsingException {
        logger.info("Parsing chocolate");
        ChocolateBuilder chocolate = new ChocolateBuilder();
        while (reader.hasNext()){
            XMLEvent event = reader.nextEvent();
            try {
                if (event.isStartElement()){
                    String name = event.asStartElement().getName().getLocalPart();
                        switch (name){
                            case "protein":{
                                chocolate.setProtein(Integer.parseInt(reader.nextEvent()
                                        .asCharacters().getData()));
                                break;
                            }
                            case "fat":{
                                chocolate.setFat(Integer.parseInt(reader.nextEvent()
                                        .asCharacters().getData()));
                                break;
                            }
                            case "carbohydrate":{
                                chocolate.setCarbohydrate(Integer.parseInt(reader
                                        .nextEvent().asCharacters().getData()));
                                break;
                            }
                            case "kilo-calories":{
                                chocolate.setKiloCalories(Integer.parseInt(reader
                                        .nextEvent().asCharacters().getData()));
                                break;
                            }
                            case "weight":{
                                chocolate.setWeight(Integer.parseInt(reader.nextEvent()
                                        .asCharacters().getData()));
                                break;
                            }
                            case "sugar-content":{
                                chocolate.setSugarContent(Integer.parseInt(reader
                                        .nextEvent().asCharacters().getData()));
                                break;
                            }
                            case "chocolate-type":{
                                chocolate.setChocolateType(Chocolate.ChocolateType
                                        .valueOf(reader.nextEvent().asCharacters()
                                                .getData().toUpperCase()));
                                break;
                            }
                            case "stuffing-type":{
                                chocolate.setStuffingType(Chocolate.StuffingType
                                        .valueOf(reader.nextEvent().asCharacters()
                                                .getData().toUpperCase()));
                                break;
                            }
                            default:{
                                throw new CandyParsingException("Wrong tag");
                            }
                        }
                } else if ((event.isEndElement()) && ("chocolate".equalsIgnoreCase(
                        event.asEndElement().getName().getLocalPart()))){
                        return chocolate.createCandy();
                }
            } catch (CandyPropertyException e) {
                throw new CandyParsingException(e);
            }
        }
        throw new CandyParsingException("Can not parse chocolate");
    }

    private Caramel parseCaramel(XMLEventReader reader)
            throws XMLStreamException, CandyParsingException {
        logger.info("Parsing caramel");
        CaramelBuilder caramel = new CaramelBuilder();
        while (reader.hasNext()) {
            XMLEvent type = reader.nextEvent();
            if (type.isStartElement()) {
                String name = type.asStartElement().getName().getLocalPart();
                try {
                    switch (name) {
                        case "protein": {
                            caramel.setProtein(Integer.parseInt(reader.nextEvent()
                                    .asCharacters().getData()));
                            break;
                        }
                        case "fat": {
                            caramel.setFat(Integer.parseInt(reader.nextEvent()
                                    .asCharacters().getData()));
                            break;
                        }
                        case "carbohydrate": {
                            caramel.setCarbohydrate(Integer.parseInt(reader.nextEvent()
                                    .asCharacters().getData()));
                            break;
                        }
                        case "kilo-calories": {
                            caramel.setKiloCalories(Integer.parseInt(reader.nextEvent()
                                    .asCharacters().getData()));
                            break;
                        }
                        case "weight": {
                            caramel.setWeight(Integer.parseInt(reader.nextEvent()
                                    .asCharacters().getData()));
                            break;
                        }
                        case "sugar-content": {
                            caramel.setSugarContent(Integer.parseInt(reader
                                    .nextEvent().asCharacters().getData()));
                            break;
                        }
                        case "caramel-type": {
                            caramel.setCaramelType(Caramel.CaramelType.valueOf(reader
                                    .nextEvent().asCharacters().getData().toUpperCase()));
                            break;
                        }
                        default: {
                            throw new CandyParsingException("Wrong tag");
                        }
                    }
                } catch (CandyPropertyException e) {
                    throw new CandyParsingException(e);
                }
            } else if ((type.isEndElement()) && ("caramel".equalsIgnoreCase(type
                    .asEndElement().getName().getLocalPart()))) {
                try {
                    return (Caramel) caramel.createCandy();
                } catch (CandyPropertyException e) {
                    throw new CandyParsingException(e);
                }
            }
        }
        throw new CandyParsingException("Can not parse caramel");
    }

    private Lollipop parseLollipop(XMLEventReader reader)
            throws XMLStreamException, CandyParsingException {
        logger.info("Parsing lollipop");
        LollipopBuilder lollipop = new LollipopBuilder();
        while (reader.hasNext()) {
            XMLEvent type = reader.nextEvent();
            if (type.isStartElement()) {
                String name = type.asStartElement().getName().getLocalPart();
                try {
                    switch (name) {
                        case "protein": {
                            lollipop.setProtein(Integer.parseInt(reader.nextEvent()
                                    .asCharacters().getData()));
                            break;
                        }
                        case "fat": {
                            lollipop.setFat(Integer.parseInt(reader.nextEvent()
                                    .asCharacters().getData()));
                            break;
                        }
                        case "carbohydrate": {
                            lollipop.setCarbohydrate(Integer.parseInt(reader
                                    .nextEvent().asCharacters().getData()));
                            break;
                        }
                        case "kilo-calories": {
                            lollipop.setKiloCalories(Integer.parseInt(reader
                                    .nextEvent().asCharacters().getData()));
                            break;
                        }
                        case "weight": {
                            lollipop.setWeight(Integer.parseInt(reader.nextEvent()
                                    .asCharacters().getData()));
                            break;
                        }
                        case "sugar-content": {
                            lollipop.setSugarContent(Integer.parseInt(reader
                                    .nextEvent().asCharacters().getData()));
                            break;
                        }
                        case "flavor": {
                            lollipop.setFlavor(reader.nextEvent().asCharacters()
                                    .getData());
                            break;
                        }
                        default: {
                            throw new CandyParsingException("Wrong tag");
                        }
                    }
                } catch (CandyPropertyException e) {
                    throw new CandyParsingException(e);
                }
            } else if ((type.isEndElement()) && ("lollipop".equalsIgnoreCase(
                    type.asEndElement().getName().getLocalPart()))) {
                try {
                    return (Lollipop) lollipop.createCandy();
                } catch (CandyPropertyException e) {
                    throw new CandyParsingException(e);
                }
            }
        }
        throw new CandyParsingException("Can not parse lollipop");
    }
}
