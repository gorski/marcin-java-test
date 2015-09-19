package api.rest;

import api.dto.Car;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Marcin Górski
 */
@RequestMapping("/cars")
public interface CarsRest {

    @RequestMapping(method = RequestMethod.POST)
    Long add(Car car);

    @RequestMapping(method = RequestMethod.GET)
    List<Car> retrieveAll();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Car retrieveById(Long id);

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    void remove(Long id);

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Car update(Long id, Car toUpdate);
}
