package com.gfg.movieshark.resource;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class ShowResource {

	private long id;

	@NotNull(message = "Show Date is Mandatory")
	private LocalDate showDate;

	@NotNull(message = "Show Time is Mandatory")
	private LocalTime showTime;

	@Min(value = 1, message = "Show Rate Multiplier Cannot be less than 1")
	private float rateMultiplier;

	@NotNull(message = "Movie is Mandatory for Show")
	private MovieResource movie;

	@NotNull(message = "Theater is Mandatory for Show")
	private TheaterResource theatre;

	private Date createdAt;

	private Date updatedAt;

	private MovieResource movieResource;

	private TheaterResource theaterResource;

	private List<ShowSeatsResource> seats;
}