package com.lmlasmo.ms.user.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmlasmo.ms.user.model.Address;

public interface AddressRepository extends JpaRepository<Address, BigInteger>{

	public Optional<Address> findByUserId(BigInteger userId);

	public boolean existsByUserId(BigInteger userId);

}
