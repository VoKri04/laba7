package org.example;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Flat implements Serializable {
    private int number;
    private double square;
    private List<Person> owners;

    @JsonCreator
    public Flat(@JsonProperty("number") int number,
                @JsonProperty("square") double square,
                @JsonProperty("owners") List<Person> owners) {
        this.number = number;
        this.square = square;
        this.owners = owners;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public List<Person> getOwners() {
        return owners;
    }

    public void setOwners(List<Person> owners) {
        this.owners = owners;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return number == flat.number && Double.compare(square, flat.square) == 0 && Objects.equals(owners, flat.owners);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, square, owners);
    }

    @Override
    public String toString() {
        return "Flat{" +
                "number=" + number +
                ", square=" + square +
                ", owners=" + owners +
                '}';
    }
}
