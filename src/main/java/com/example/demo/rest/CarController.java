package com.example.demo.rest;

import com.example.demo.domain.Car;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }



    @RequestMapping("/cars/{id}")
    public Car getCar(@PathVariable("id") Long id){
        return carService.getCarById(id);
    }

    @RequestMapping("/cars")
    public List<Car> getCars() {
        return carService.getCars();
    }

    @RequestMapping(value = "/cars/{id}/edit", method = RequestMethod.PUT)
    public void updateCar(@RequestBody() Car car, @PathVariable("id") Long id) {
         carService.update(car);
         //return ResponseEntity.status(HttpStatus.CREATED).body(car);
    }

    @RequestMapping(value = "/cars/new", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Car addCar(@RequestBody() Car car) {
        return carService.save(car);
    }

    @RequestMapping(value = "/cars/allNew", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.CREATED)
    public List<Car> addAllCar(@RequestBody() List<Car> cars) {
        return carService.saveAll(cars);
    }
}
