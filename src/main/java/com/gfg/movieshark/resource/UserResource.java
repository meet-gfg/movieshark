package com.gfg.movieshark.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class UserResource {

	private long id;

	@NotBlank(message = "User name is Mandatory")
	private String name;

	@NotBlank(message = "Mobile is Mandatory")
	private String mobile;

	private List<TicketResource> tickets;
}