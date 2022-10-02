package com.gfg.movieshark.service;


import com.gfg.movieshark.domain.Theater;
import com.gfg.movieshark.domain.TheaterSeats;
import com.gfg.movieshark.enums.SeatType;
import com.gfg.movieshark.repository.TheaterRepository;
import com.gfg.movieshark.repository.TheaterSeatsRepository;
import com.gfg.movieshark.resource.TheaterResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class TheaterService {

	@Autowired
	private TheaterRepository theaterRepository;

	@Autowired
	private TheaterSeatsRepository theaterSeatsRepository;


	public TheaterResource addTheater(TheaterResource theaterDto) {

		Theater theater = Theater.toEntity(theaterDto);

		theater.getSeats().addAll(getTheaterSeats());

		for (TheaterSeats seatsEntity : theater.getSeats()) {
			seatsEntity.setTheater(theater);
		}

		theater = theaterRepository.save(theater);

		log.info("Added New User [id: " + theater.getId() + ", Name: " + theater.getName() + "]");

		return Theater.toResource(theater);
	}

	private List<TheaterSeats> getTheaterSeats() {

		List<TheaterSeats> seats = new ArrayList<>();

		seats.add(getTheaterSeat("1A", SeatType.REGULAR));
		seats.add(getTheaterSeat("1B", SeatType.REGULAR));
		seats.add(getTheaterSeat("1C", SeatType.REGULAR));
		seats.add(getTheaterSeat("1D", SeatType.REGULAR));
		seats.add(getTheaterSeat("1E", SeatType.REGULAR));

		seats.add(getTheaterSeat("2A", SeatType.RECLINER));
		seats.add(getTheaterSeat("2B", SeatType.RECLINER));
		seats.add(getTheaterSeat("2C", SeatType.RECLINER));
		seats.add(getTheaterSeat("2D", SeatType.RECLINER));
		seats.add(getTheaterSeat("2E", SeatType.RECLINER));

		seats = theaterSeatsRepository.saveAll(seats);

		return seats;
	}

	private TheaterSeats getTheaterSeat(String seatNumber, SeatType seatType) {
		return TheaterSeats.builder().seatNumber(seatNumber).seatType(seatType).build();
	}


	public TheaterResource getTheater(long id) {
		log.info("Searching Theater by id: " + id);

		Optional<Theater> theaterEntity = theaterRepository.findById(id);

		if (theaterEntity.isEmpty()) {
			log.error("Theater not found for id: " + id);
			throw new EntityNotFoundException("Theater Not Found with ID: " + id);
		}

		return Theater.toResource(theaterEntity.get());
	}

}