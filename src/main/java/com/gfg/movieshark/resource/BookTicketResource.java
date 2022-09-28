package com.gfg.movieshark.resource;


import com.gfg.movieshark.enums.SeatType;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class BookTicketResource {

	@NotEmpty(message = "Select atleast 1 Seat to Book")
	private Set<String> seatsNumbers;

	@Min(value = 1, message = "User is Invalid")
	private long userId;

	@Min(value = 1, message = "Show is Invalid")
	private long showId;

	@NotNull(message = "Seat Type is Mandatory to Book")
	private SeatType seatType;

}