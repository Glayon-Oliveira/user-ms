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
@Table(name = "addresses")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private BigInteger id;

	@Column(name = "nation", nullable = false, length = 2)
	private String nation;

	@Column(name = "state", nullable = false, length = 2)
	private String state;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "district", nullable = false)
	private String district;

	@Column(name = "street_name", nullable = false)
	private String street;

	@Column(name = "street_number", nullable = false, length = 20)
	private String number;

	@Column(name = "complement")
	private String complement;

	@Column(name = "postal_code", length = 20)
	private String postalCode;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private Instant updatedAt;

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	private User user;

}
