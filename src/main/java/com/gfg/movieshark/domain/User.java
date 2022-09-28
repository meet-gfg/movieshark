package com.gfg.movieshark.domain;

import com.gfg.movieshark.resource.UserResource;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "mobile", nullable = false)
	private String mobile;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Ticket> ticketEntities;

	public static User toEntity(UserResource userResource) {

		return User.builder()
				.name(userResource.getName())
				.mobile(userResource.getMobile())
				.build();

	}

	public static UserResource toResource(User user) {

		return UserResource.builder()
				.id(user.getId())
				.name(user.getName())
				.mobile(user.getMobile())
				.build();
	}

}