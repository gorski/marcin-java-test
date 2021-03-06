package api.rest;

import api.dto.Car;
import api.dto.ErrorDto;
import api.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author Marcin Górski
 */
@RestController
public class CarsRestImpl implements CarsRest {

    private static final Map<Long, Car> storage = new WeakHashMap<>();
    private static long sequence = 1L;

    @Override
    public Long add(@RequestBody Car car) {
        if (car.getMake() == null || car.getMake().isEmpty()) {
            throw new ApiException("At least make has to be filled in.", HttpStatus.BAD_REQUEST);
        }
        long id = sequence;
        sequence++;
        car.setId(id);
        storage.put(id, car);
        return id;
    }

    @Override
    public List<Car> retrieveAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Car retrieveById(@PathVariable("id") Long id) {
        final Car car = storage.get(id);
        if (car == null) {
            throw new ApiException("Car with id " + id + " not found.", HttpStatus.NOT_FOUND);
        }
        return car;
    }

    @Override
    public void remove(@PathVariable("id") Long id) {
        final Car car = storage.get(id);
        if (car == null) {
            throw new ApiException("Car with id " + id + " not found.", HttpStatus.NOT_FOUND);
        }
        storage.remove(id);
    }

    @Override
    public Car update(@PathVariable("id") Long id, @RequestBody Car changes) {
        final Car car = storage.get(id);
        if (car == null) {
            throw new ApiException("Car with id " + id + " not found.", HttpStatus.NOT_FOUND);
        }
        // assumption: entity contains only modified elements, therefore this ugly rewrite below (ORM will handle)
        if (changes.getMake() != null) {
            car.setMake(changes.getMake());
        }
        if (changes.getModel() != null) {
            car.setModel(changes.getModel());
        }
        if (changes.getYearOfManufacture() != null) {
            car.setYearOfManufacture(changes.getYearOfManufacture());
        }

        storage.put(id, car);
        return car;
    }

    @ExceptionHandler(ApiException.class)
    public ErrorDto handleExceptions(ApiException ex, HttpServletResponse response) {
        response.setStatus(ex.getStatus().value());
        return new ErrorDto(ex.getMessage());
    }
}
