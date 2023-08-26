package doublecolonoperator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {

    private String make;
    private int model;

    public Car() {
    }

    public Car(int model) {
        this.model = model;
    }

    public Car(String make, int model) {
        this.make = make;
        this.model = model;
    }
}
