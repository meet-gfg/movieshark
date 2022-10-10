package com.gfg.movieshark.domain;

import com.gfg.movieshark.enums.Role;
import com.gfg.movieshark.resource.UserResource;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name="password",nullable = false)
	private String password;

	@Column(name = "mobile", nullable = false)
	private String mobile;

	@Column(name = "email", nullable = false)
	private String email;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Ticket> ticketEntities;

	@Enumerated(EnumType.STRING)
	@Column(name = "role" ,columnDefinition = "varchar(30) default 'USER'")
	private Role role;

	public static User toEntity(UserResource userResource) {

		return User.builder()
				.name(userResource.getName())
				.password(userResource.getPassword())
				.role(userResource.getRole())
				.mobile(userResource.getMobile())
				.email(userResource.getEmail())
				.build();

	}

	public static UserResource toResource(User user) {

		return UserResource.builder()
				.id(user.getId())
				.name(user.getName())
				.mobile(user.getMobile())
				.email(user.getEmail())
				.tickets(Ticket.toResource(user.getTicketEntities()))
				.build();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		  return Arrays.stream(this.role.toString().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}