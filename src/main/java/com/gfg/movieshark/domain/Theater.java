/**
 * 
 */
package com.gfg.movieshark.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gfg.movieshark.resource.TheaterResource;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theaters")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class Theater {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "address", nullable = false)
	private String address;

	@OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
	@JsonIgnore
	@Builder.Default
	private List<Show> shows = new ArrayList<>();

	@OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
	@JsonIgnore
	@Builder.Default
	private List<TheaterSeats> seats = new ArrayList<>();

	public static Theater toEntity(TheaterResource theaterResource) {

		return Theater.builder()
				.name(theaterResource.getName())
				.city(theaterResource.getCity())
				.address(theaterResource.getAddress())
				.build();
	}

	public static TheaterResource toResource(Theater theater) {

		return TheaterResource.builder()
				.id(theater.getId())
				.name(theater.getName())
				.city(theater.getCity())
				.address(theater.getAddress())
				.build();
	}

}