package model.entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Withdraw {
    private IntegerProperty id;
    private IntegerProperty value;

    public Withdraw(int id, int value) {
        this.id = new SimpleIntegerProperty(id);
        this.value = new SimpleIntegerProperty(value);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public int getValue() {
        return value.get();
    }

    public void setValue(int value) {
        this.value.set(value);
    }

    public IntegerProperty valueProperty() {
        return value;
    }
}