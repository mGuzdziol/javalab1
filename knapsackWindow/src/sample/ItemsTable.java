package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ItemsTable {

    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty weight;
    private final SimpleStringProperty value;

    public ItemsTable(int id, int weight, String value) {
        this.id =new SimpleIntegerProperty(id);
        this.weight = new SimpleIntegerProperty(weight);
        this.value = new SimpleStringProperty(value);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getWeight() {
        return weight.get();
    }

    public void setWeight(int weight) {
        this.weight.set(weight);
    }

    public String getValue() {
        return value.get();
    }

    public void setValue(String value) {
        this.value.set(value);
    }


}
