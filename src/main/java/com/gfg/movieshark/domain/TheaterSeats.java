package com.gfg.movieshark.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gfg.movieshark.enums.SeatType;
import lombok.*;

import javax.persistence.*;



@Entity
@Table(name = "theater_seats")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class TheaterSeats {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "seat_number", nullable = false)
	private String seatNumber;

	@Enumerated(EnumType.STRING)
	@Column(name = "seat_type", nullable = false)
	private SeatType seatType;

	@ManyToOne
	@JsonIgnore
	private Theater theater;
}