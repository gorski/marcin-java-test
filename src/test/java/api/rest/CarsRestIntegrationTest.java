package api.rest;

import api.Application;
import api.dto.Car;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author Marcin Górski
 */

@ContextConfiguration(classes = {Application.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Category(IntegrationTest.class)
public class CarsRestIntegrationTest {

    @Autowired
    private CarsRest carsRest;

    @Test
    public void shouldManipulateCars() {

        Car car = new Car();
        car.setMake("Tesla");
        car.setModel("Roadster");
        car.setYearOfManufacture(2006);

        final Long id = carsRest.add(car);

        final Car retrieved = carsRest.retrieveById(id);

        assertEquals(retrieved.getId(), id);
        assertEquals(retrieved.getMake(), car.getMake());
        assertEquals(retrieved.getModel(), car.getModel());
        assertEquals(retrieved.getYearOfManufacture(), car.getYearOfManufacture());

        Car update = new Car();
        final Integer yearOfManufacture = 2007;
        update.setYearOfManufacture(yearOfManufacture);
        carsRest.update(id, update);

        final Car afterUpdate = carsRest.retrieveById(id);
        assertEquals(yearOfManufacture, afterUpdate.getYearOfManufacture());

        carsRest.remove(id);
    }
}
