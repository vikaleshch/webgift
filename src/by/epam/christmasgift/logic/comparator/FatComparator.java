package by.epam.christmasgift.logic.comparator;

import by.epam.christmasgift.candy.Candy;

import java.util.Comparator;

/**
 * Created by Student on 8/12/2014.
 */
public class FatComparator implements Comparator<Candy> {
    @Override
    public int compare(Candy o1, Candy o2) {
        return Integer.compare(o1.getFat(), o2.getFat());
    }
}
