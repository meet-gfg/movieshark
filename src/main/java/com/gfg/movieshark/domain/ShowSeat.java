package com.gfg.movieshark.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gfg.movieshark.enums.SeatType;
import com.gfg.movieshark.resource.ShowSeatsResource;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "show_seats")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class ShowSeat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "seat_number", nullable = false)
	private String seatNumber;

	@Column(name = "rate", nullable = false)
	private int rate;

	@Enumerated(EnumType.STRING)
	@Column(name = "seat_type", nullable = false)
	private SeatType seatType;

	@Column(name = "is_booked", columnDefinition = "bit(1) default 0", nullable = false)
	private boolean booked;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "booked_at")
	@CreationTimestamp
	private Date bookedAt;

	@ManyToOne
	@JsonIgnore
	private Show show;

	@ManyToOne
	@JsonIgnore
	private Ticket ticket;

	public static List<ShowSeatsResource> toResource(List<ShowSeat> seats) {

		if (!CollectionUtils.isEmpty(seats)) {
			return seats.stream().map(ShowSeat::toResource).collect(Collectors.toList());
		}

		return new ArrayList<>();
	}

	public static ShowSeatsResource toResource(ShowSeat seatsEntity) {

		return ShowSeatsResource.builder()
				.id(seatsEntity.getId())
				.seatNumber(seatsEntity.getSeatNumber())
				.rate(seatsEntity.getRate())
				.seatType(seatsEntity.getSeatType())
				.booked(seatsEntity.isBooked())
				.bookedAt(seatsEntity.getBookedAt())
				.build();

	}

}