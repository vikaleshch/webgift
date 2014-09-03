package by.epam.christmasgift.candy.builder;

import by.epam.christmasgift.candy.Candy;
import by.epam.christmasgift.exception.CandyPropertyException;

public abstract class CandyBuilder {
    private int protein;
    private int fat;
    private int carbohydrate;
    private int kiloCalories;
    private int weight;
    private int sugarContent;

    protected CandyBuilder(int protein, int fat, int carbohydrate,
                           int kiloCalories, int weight, int sugarContent)
            throws CandyPropertyException {
        setProtein(protein);
        setFat(fat);
        setCarbohydrate(carbohydrate);
        setKiloCalories(kiloCalories);
        setWeight(weight);
        setSugarContent(sugarContent);
    }

    protected CandyBuilder(){}

    public void setProtein(int protein) throws CandyPropertyException {
        if (protein > 0) {
            this.protein = protein;
        } else {
            throw new CandyPropertyException("Mistake in protein");
        }
    }

    public void setFat(int fat) throws CandyPropertyException {
        if (fat > 0) {
            this.fat = fat;
        } else {
            throw new CandyPropertyException("Mistake in fat");
        }
    }

    public void setCarbohydrate(int carbohydrate) throws CandyPropertyException {
        if (carbohydrate > 0) {
            this.carbohydrate = carbohydrate;
        } else {
            throw new CandyPropertyException("Mistake in carbohydrate");
        }
    }

    public void setKiloCalories(int kiloCalories) throws CandyPropertyException {
        if (kiloCalories > 0) {
            this.kiloCalories = kiloCalories;
        } else {
            throw new CandyPropertyException("Mistake in kilo-calories");
        }
    }

    public void setWeight(int weight) throws CandyPropertyException {
        if (weight > 0) {
            this.weight = weight;
        } else {
            throw new CandyPropertyException("Mistake in weight");
        }
    }

    public void setSugarContent(int sugarContent) throws CandyPropertyException {
        if ((0 < sugarContent) && (sugarContent < 100)) {
            this.sugarContent = sugarContent;
        } else {
            throw new CandyPropertyException("Mistake in sugar content");
        }
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }

    public int getKiloCalories() {
        return kiloCalories;
    }

    public int getWeight() {
        return weight;
    }

    public int getSugarContent() {
        return sugarContent;
    }

    public abstract Candy createCandy() throws CandyPropertyException;
}