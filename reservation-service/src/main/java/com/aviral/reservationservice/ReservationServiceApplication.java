package com.aviral.reservationservice;

import java.util.Collection;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class ReservationServiceApplication {

	@Bean
	CommandLineRunner commandLineRunner(ReservationRepository reservationRepository) {
		return strings -> { 
			Stream.of("Josh", "Ross", "Chandler", "Monica", "Rachel", "Phoebe", "Joey")
			.forEach(n -> reservationRepository.save(new Reservation()));;
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}

}

/*
 * @Component class DummyDataCLR implements CommandLineRunner {
 * 
 * @Autowired private ReservationRepository reservationRepository;
 * 
 * @Override public void run(String...strings) throws Exception{
 * Stream.of("Josh", "Ross", "Chandler", "Monica", "Rachel", "Phoebe", "Joey")
 * .forEach(n -> this.reservationRepository.save(new Reservation())); } }
 */

@RepositoryRestResource
interface ReservationRepository extends JpaRepository<Reservation, Long>{
	@RestResource(path="by-name")
	Collection <Reservation> findByReservationName(@Param("rn") String rn);
}

@Entity
class Reservation {
	
	@Id @GeneratedValue
	private Long id;
	private String reservationName;
	
	public Reservation() {
		//for JPA
	}
	
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", reservationName=" + reservationName + "]";
	}


	public Reservation(String reservationName) {
		super();
		this.reservationName = reservationName;
	}
	public Long getId() {
		return id;
	}
	public String getReservationName() {
		return reservationName;
	}
	
	
}
