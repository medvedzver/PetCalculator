package com.tractive.PetCalculator;

import com.tractive.PetCalculator.controller.dto.CreatePetDto;
import com.tractive.PetCalculator.controller.dto.OutsideOfZoneDto;
import com.tractive.PetCalculator.controller.dto.PetDto;
import com.tractive.PetCalculator.persistence.model.PetType;
import com.tractive.PetCalculator.persistence.model.TrackerType;
import com.tractive.PetCalculator.persistence.model.Cat;
import com.tractive.PetCalculator.persistence.CatsRepository;
import com.tractive.PetCalculator.persistence.model.Dog;
import com.tractive.PetCalculator.persistence.DogsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PetControllerTest {

	@Autowired
	public DogsRepository dogsRepository;

	@Autowired
	public CatsRepository catsRepository;

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;


	@BeforeEach
	public void setUp() {
		dogsRepository.deleteAll();
		catsRepository.deleteAll();
	}

	@Test
	void findByOwnerIdFindsAllPets() {
		dogsRepository.save(new Dog(
				1,
				TrackerType.BIG,
				true
		));
		dogsRepository.save(new Dog(
				2,
				TrackerType.MEDIUM,
				true
		));
		catsRepository.save(new Cat(
				3,
				TrackerType.BIG,
				true,
				true
		));

		List<PetDto> expected = List.of(
				new PetDto(PetType.DOG, 1, TrackerType.BIG, true, null),
				new PetDto(PetType.DOG, 2, TrackerType.MEDIUM, true, null),
				new PetDto(PetType.CAT, 3, TrackerType.BIG, true, true)
		);

		var response = restTemplate
				.getForEntity("http://localhost:" + port + "/pets", PetDto[].class)
				.getBody();
		Assertions.assertThat(response)
				.hasSameElementsAs(expected);
	}

	@Test
	void findByOwnerIdFindsPetsByUserId() {
		dogsRepository.save(new Dog(
				1,
				TrackerType.BIG,
				true
		));
		dogsRepository.save(new Dog(
				2,
				TrackerType.MEDIUM,
				true
		));
		catsRepository.save(new Cat(
				2,
				TrackerType.BIG,
				true,
				true
		));

		List<PetDto> expected = List.of(
				new PetDto(PetType.DOG, 2, TrackerType.MEDIUM, true, null),
				new PetDto(PetType.CAT, 2, TrackerType.BIG, true, true)
		);

		var response = restTemplate
				.getForEntity("http://localhost:" + port + "/pets?ownerId=2", PetDto[].class)
				.getBody();
		Assertions.assertThat(response)
				.hasSameElementsAs(expected);
	}

	@Test
	void findAllOutOfZone() {
		dogsRepository.save(new Dog(
				1,
				TrackerType.BIG,
				false
		));
		dogsRepository.save(new Dog(
				2,
				TrackerType.MEDIUM,
				true
		));
		dogsRepository.save(new Dog(
				2,
				TrackerType.MEDIUM,
				false
		));
		dogsRepository.save(new Dog(
				3,
				TrackerType.MEDIUM,
				false
		));
		catsRepository.save(new Cat(
				3,
				TrackerType.BIG,
				true,
				false
		));
		catsRepository.save(new Cat(
				3,
				TrackerType.BIG,
				false,
				true
		));

		OutsideOfZoneDto expected = new OutsideOfZoneDto(
				Map.of(PetType.DOG,
						Map.of(TrackerType.BIG, 1L,
								TrackerType.MEDIUM, 2L),

						PetType.CAT,
						Map.of(TrackerType.BIG, 1L)
				)
		);

		var response = restTemplate
				.getForEntity("http://localhost:" + port + "/pets/outofzone", OutsideOfZoneDto.class)
				.getBody();

		Assertions.assertThat(response)
				.isEqualTo(expected);
	}

	@Test
	void addPetAddsDogs() {
		Dog expected = new Dog(
					1,
						TrackerType.MEDIUM,
						true
				);

		restTemplate
			.postForEntity("http://localhost:" + port + "/pets", new CreatePetDto(PetType.DOG, 1, TrackerType.MEDIUM, true, null), String.class);

		var response = dogsRepository.findAll();

		Assertions.assertThat(response).hasSize(1);
		Assertions.assertThat(response.get(0))
				.usingRecursiveComparison()
				.ignoringFields("id")
				.isEqualTo(expected);
	}

	@Test
	void addPetAddsCats() {
		Cat expected = new Cat(
				1,
				TrackerType.BIG,
				true,
				false
		);

		restTemplate
			.postForEntity("http://localhost:" + port + "/pets", new CreatePetDto(PetType.CAT, 1, TrackerType.BIG, true, false), String.class);

		var response = catsRepository.findAll();

		Assertions.assertThat(response).hasSize(1);
		Assertions.assertThat(response.get(0))
				.usingRecursiveComparison()
				.ignoringFields("id")
				.isEqualTo(expected);
	}

	@Test
	void addPetCantCreateCatWithMediumTracker() {
		var result = restTemplate
			.postForEntity("http://localhost:" + port + "/pets", new CreatePetDto(PetType.CAT, 1, TrackerType.MEDIUM, true, false), String.class);

		Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

	}
}
