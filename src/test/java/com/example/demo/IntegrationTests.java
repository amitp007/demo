package com.example.demo;

import com.example.demo.domain.Car;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= RANDOM_PORT )
public class IntegrationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getCar_returnsCardDetails() throws Exception {

		//restTemplate.postForEntity("/api/cars/new", new Car("Honda", 5), Car.class);

		ResponseEntity<List<Car>> responseEntity = restTemplate.
				exchange("/api/cars",
						HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>(){});

		Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(responseEntity.getBody().size()).isGreaterThan(0);
	}

	@Test
	public void getCarById_returnsCardDetails() throws Exception {

		//restTemplate.postForEntity("/api/cars/new", new Car("Honda", 5), Car.class);

		ResponseEntity<Car> responseEntity = restTemplate.getForEntity("/api/cars/1", Car.class);

		Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(responseEntity.getBody().getName()).isEqualTo("Honda");
	}


	@Test
	public void addNewCar_returnsSuccess() throws Exception {

		ResponseEntity<Car> responseEntity =
				restTemplate.postForEntity("/api/cars/new", new Car("Honda", 5), Car.class);

		//ResponseEntity<Car> responseEntity = restTemplate.getForEntity("/api/cars/1", Car.class);

		Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		Assertions.assertThat(responseEntity.getBody().getName()).isEqualTo("Honda");
	}
}


