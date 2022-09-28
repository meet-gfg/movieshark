package com.gfg.movieshark.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gfg.movieshark.resource.ShowResource;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Entity
@Table(name = "shows")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Show {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "show_date", columnDefinition = "DATE", nullable = false)
	private LocalDate showDate;

	@Column(name = "show_time", columnDefinition = "TIME", nullable = false)
	private LocalTime showTime;

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "created_at")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Column(name = "updated_at")
	private Date updatedAt;

	@ManyToOne
	@JsonIgnore
	private Movie movie;

	@ManyToOne
	@JsonIgnore
	private Theater theater;

	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Ticket> tickets;

	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ShowSeat> seats;


	public static List<ShowResource> toResource(List<Show> show) {

		if (!CollectionUtils.isEmpty(show)) {
			return show.stream().map(Show::toResource).collect(Collectors.toList());
		}

		return new ArrayList<>();
	}

	public static ShowResource toResource(Show show) {

		return ShowResource.builder()
				.id(show.getId())
				.showDate(show.getShowDate())
				.showTime(show.getShowTime())
				.movie(Movie.toResource(show.getMovie()))
				.theatre(Theater.toResource(show.getTheater()))
				.seats(ShowSeat.toResource(show.getSeats()))
				.createdAt(show.getCreatedAt())
				.updatedAt(show.getUpdatedAt())
				.build();

	}

	public static Show toEntity(ShowResource showDto) {

		return Show.builder()
				.showDate(showDto.getShowDate())
				.showTime(showDto.getShowTime())
				.build();

	}
}