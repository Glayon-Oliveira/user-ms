package com.lmlasmo.ms.user.model;

import java.math.BigInteger;
import java.time.Instant;
import java.util.Collection;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails{

	private static final long serialVersionUID = -1469476032126724886L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private BigInteger id;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserStatusType status = UserStatusType.ACTIVE;

	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserRoleType role = UserRoleType.COMUM;

	@Column(name = "created_at", nullable = false)
	@CreationTimestamp
	private Instant createdAt;

	@Column(name = "updated_at", nullable = false)
	@UpdateTimestamp
	private Instant updated_at;

	@OneToOne(mappedBy = "user")
	private Profile profile;

	@OneToOne(mappedBy = "user")
	private Address address;

	@PrePersist
	protected void onCreate() {
		email = email.toLowerCase();
	}

	@PreUpdate
	protected void onUpdate() {
		email = email.toLowerCase();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return role.getHierarchy().stream().map(r -> new SimpleGrantedAuthority(r.name())).toList();
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonLocked() {
		return UserStatusType.ACTIVE.equals(status);
	}

}
