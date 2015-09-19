package api.rest;

import api.dto.Car;
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
@RunWith(Parameterized.class)
public class CarsRestAddTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Ford", 1911, "T"},
                {"Ford", null, null},
                {"Ford", 2000, "Fiesta"},
                {"Ford", null, "Fiesta"},
                {"Ford", 2000, null}
        });
    }


    private CarsRest carsRest;
    private String make;
    private Integer year;
    private String model;

    public CarsRestAddTest(String make, Integer year, String model) {
        this.make = make;
        this.year = year;
        this.model = model;
    }

    @Before
    public void setUp() {
        carsRest = new CarsRestImpl();
    }

    @Test
    @Parameterized.Parameters(name = "data")
    public void shouldCorrectlyAddCars() {
        Car car = new Car();
        car.setMake(make);
        car.setYearOfManufacture(year);
        car.setModel(model);

        final Long addedId = carsRest.add(car);
        assertNotNull(addedId);

        final Car retrieved = carsRest.retrieveById(addedId);
        assertNotNull(retrieved);
        assertEquals(retrieved.getMake(), make);
        assertEquals(retrieved.getYearOfManufacture(), year);
        assertEquals(retrieved.getModel(), model);


    }


}
