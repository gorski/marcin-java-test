package api.rest;

import api.dto.Car;
import api.exception.ApiException;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Marcin Górski
 */
public class CarsRestAddIncorrectTest {

    private CarsRest carsRest;

    @Before
    public void setUp() {
        carsRest = new CarsRestImpl();
    }

    @Test(expected = ApiException.class)
    public void shouldDisallowToAddEmptyMake() {
        Car car = new Car();
        carsRest.add(car);
    }
}
