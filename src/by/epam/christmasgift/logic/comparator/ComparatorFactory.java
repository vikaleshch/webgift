package by.epam.christmasgift.logic.comparator;

import by.epam.christmasgift.candy.Candy;
import by.epam.christmasgift.exception.FactoryException;

import java.util.Comparator;

/**
 * Created by Student on 8/12/2014.
 */
public class ComparatorFactory {
    public Comparator<Candy> createComparator(String comparatorType)
            throws FactoryException {
        switch (comparatorType){
            case "protein":{
                return new ProteinComparator();
            }
            case "fat":{
                return new FatComparator();
            }
            case "carbohydrate":{
                return new CarbohydrateComparator();
            }
            case "kiloCalories":{
                return new KiloCaloriesComparator();
            }
            case "weight":{
                return new WeightComparator();
            }
            case "sugarContent":{
                return new SugarContentComparator();
            }
            default:{
                throw new FactoryException("Wrong type");
            }
        }
    }
}
