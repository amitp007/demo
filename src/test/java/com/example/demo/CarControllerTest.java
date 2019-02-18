package com.example.demo;

import com.example.demo.domain.Car;
import com.example.demo.rest.CarController;
import com.example.demo.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarService carService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getCar_returnsCarDetails() throws Exception{
        //act

        RequestBuilder get = get("/api/cars/1"); //.param("id", "1");

        given(carService.getCarById(1L)).willReturn( new Car( 1L,"prious", 5));
        //arrage
        mvc.perform(get)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("prious"))
                .andExpect(jsonPath("age").value(5));
    }


    @Test
    public void getCar_returnsCars() throws Exception{
        //act
        List<Car> cars = Arrays.asList( new Car("prious", 5),  new Car("prious", 6));

        given(carService.getCars()).willReturn(cars);
        //arrage
        mvc.perform(get("/api/cars"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("prious"))
                .andExpect(jsonPath("$[0].age").value(5));

        //https://www.programcreek.com/java-api-examples/index.php?api=org.springframework.test.web.servlet.result.MockMvcResultMatchers
    }

    @Test
    public void updateCar_returnsSuccess() throws Exception {

        Car car = new Car(1L, "", 5);
        doNothing().when(carService).update(car);

        RequestBuilder put = put("/api/cars/1/edit")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsBytes(car));

        mvc.perform(put)
                .andExpect(status().isOk());


        verify(carService, times(1)).update(car);

    }

    @Test
    public void addNewCar_returnsSuccess() throws Exception {

        Car car = new Car(1L, "honda", 5);
        given(carService.save(car)).willReturn(car);

        RequestBuilder post = post("/api/cars/new")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsBytes(car));

        mvc.perform(post)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("name").value("honda"));

        verify(carService, times(1)).save(car);

    }

    @Test
    public void addCars_returnsSuccess() throws Exception {

        List<Car> carList = Arrays.asList(
                new Car( "Ford", 5),
                new Car( "GM", 5),
                new Car( "Tesla", 5)
        );

        given(carService.saveAll(carList)).willReturn(carList);

        RequestBuilder post = patch("/api/cars/allNew")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsBytes(carList));

        mvc.perform(post)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].name").value("Ford"));

        verify(carService, times(1)).saveAll(carList);

    }
}

