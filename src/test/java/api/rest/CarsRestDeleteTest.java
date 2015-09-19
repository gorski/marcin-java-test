package api.rest;

import api.dto.Car;
import api.exception.ApiException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Marcin Górski
 */
public class CarsRestDeleteTest {

    private CarsRest carsRest;

    @Before
    public void setUp() {
        carsRest = new CarsRestImpl();
    }

    @Test(expected = ApiException.class)
    public void shouldCorrectlyRemoveElement() {
        Car car = new Car();
        car.setMake("Audi");
        final Long id = carsRest.add(car);

        assertNotNull(id);

        final Car retrieved = carsRest.retrieveById(id);
        assertNotNull(retrieved);
        carsRest.remove(id);

        // throws not found
        carsRest.retrieveById(id);



    }

}
