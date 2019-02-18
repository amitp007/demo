package com.example.demo.service;

        import com.example.demo.domain.Car;
        import com.example.demo.repository.CarRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Repository;
        import org.springframework.stereotype.Service;


        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
        import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public List<Car> getCars() {
        //List<Car> cars = Arrays.asList(new Car("prious", 5), new Car("prious", 6));
        List<Car> list = new ArrayList<>();
        carRepository.findAll().forEach(o -> list.add(o));
        return list;
    }

    public void update(Car car) {
        carRepository.findById(car.getId())
                .map(o -> {
                    o.setAge(car.getAge());
                    o.setName(car.getName());
                    return carRepository.save(o);
                }).orElse(carRepository.save(car));

    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> saveAll(List<Car> cars) {
         ArrayList<Car> carList = new ArrayList<>();
         carRepository.saveAll(cars).forEach(o -> carList.add(o));
         return  carList;
    }
}
