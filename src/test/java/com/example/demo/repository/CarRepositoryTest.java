package com.example.demo.repository;

import com.example.demo.domain.Car;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository repository;


    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findCarByName() throws Exception{

        repository.save(new Car("prius",5));
        Car car = repository.findByName("prius");
        Assertions.assertThat(car.getName()).isEqualTo("prius");
    }

    @Test
    public void deleteAllCars() {
        repository.deleteAll();
        Iterable<Car> cars = repository.findAll();
        Assertions.assertThat(cars).isEmpty();
    }
}