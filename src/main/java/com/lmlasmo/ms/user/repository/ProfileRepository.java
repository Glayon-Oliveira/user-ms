package com.lmlasmo.ms.user.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmlasmo.ms.user.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, BigInteger>{

	public Optional<Profile> findByUserId(BigInteger userId);

	public boolean existsByUserId(BigInteger userId);

}
