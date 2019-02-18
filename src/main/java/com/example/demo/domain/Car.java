package com.example.demo.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Data
public class Car {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer age;

    public Car(){}

    public Car(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Car(Long id,  String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

}
