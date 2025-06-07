package com.lmlasmo.ms.user.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmlasmo.ms.user.model.User;
import com.lmlasmo.ms.user.model.UserStatusType;

public interface UserRepository extends JpaRepository<User, BigInteger> {

	public Optional<User> findByEmail(String email);

	public boolean existsByEmail(String email);

	public boolean existsByIdAndStatus(BigInteger id, UserStatusType status);

}
