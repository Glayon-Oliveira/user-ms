package com.lmlasmo.ms.user.model;

import java.math.BigInteger;
import java.time.Instant;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "profiles")
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private BigInteger id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "tel", length = 20)
	private String tel;

	@Column(name = "updated_at", nullable = false)
	@UpdateTimestamp
	private Instant updatedAt;

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	private User user;

}
