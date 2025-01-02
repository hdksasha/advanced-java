package entities;

import annotations.*;

/**
 * Represents a car with validation annotations.
 */
public class Car {

    @NotNull
    private String model;

    @MinValue(1985)
    private Integer year;

    @MaxValue(1000000)
    private Integer price;

    public Car(String model, Integer year, Integer price) {
        this.model = model;
        this.year = year;
        this.price = price;
    }
}
