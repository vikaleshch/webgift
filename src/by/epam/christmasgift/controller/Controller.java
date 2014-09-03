package by.epam.christmasgift.controller;

import by.epam.christmasgift.candy.Candy;
import by.epam.christmasgift.candy.ChristmasGift;
import by.epam.christmasgift.exception.CandyParsingException;
import by.epam.christmasgift.exception.FactoryException;
import by.epam.christmasgift.parsing.Parser;
import by.epam.christmasgift.parsing.ParserFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vika on 24.08.2014.
 */
public class Controller extends HttpServlet {
    private static final String XML_PATH = "/resources/gift.xml";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("parserType");
        ParserFactory factory = new ParserFactory();
        Parser parser = null;
        Iterable<Candy> gift = null;
        try {
            parser = factory.createParser(ParserFactory.ParserType.valueOf(type.toUpperCase()));
            String path = getServletContext().getRealPath(XML_PATH);
            gift = parser.parse(path).getCandies();
        } catch (FactoryException | CandyParsingException e) {
            throw new ServletException(e);
        } catch (IllegalArgumentException e) {
            throw new ServletException(new FactoryException(e));
        }
        request.setAttribute("gift", gift);
        request.getRequestDispatcher("jsp/parsing.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("parserType");
        ParserFactory factory = new ParserFactory();
        Parser parser = null;
        Iterable<Candy> gift = null;
        try {
            parser = factory.createParser(ParserFactory.ParserType.valueOf(type.toUpperCase()));
            String path = getServletContext().getRealPath(XML_PATH);
            gift = parser.parse(path).getCandies();
        } catch (FactoryException | CandyParsingException e) {
            throw new ServletException(e);
        } catch (IllegalArgumentException e) {
            throw new ServletException(new FactoryException(e));
        }
        request.setAttribute("gift", gift);
        request.getRequestDispatcher("jsp/parsing.jsp").forward(request, response);
    }
}
