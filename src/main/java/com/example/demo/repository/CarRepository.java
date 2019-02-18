package com.example.demo.repository;

import com.example.demo.domain.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    Car findByName(String name);
//
//    @Query("select * from Car c where c.name = ?1 and c.id = ?2")
//    Car findByNameAndId(String name, Long id);
//
//    @Query("select c from Car c where c.name = :fname")
//    Car findByfname(@Param("fname") String name);
}
