package com.lmlasmo.ms.user.dto.auth;

import java.math.BigInteger;
import java.util.Collection;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class UserAuthDTO {

	@NonNull
	private String id;

	@NonNull
	private String[] roles;

	@JsonIgnore
	public BigInteger idToBigInteger() {
		return new BigInteger(id);
	}

	@JsonIgnore
	public Collection<? extends GrantedAuthority> rolesToAuthorities(){
		return Stream.of(roles).map(SimpleGrantedAuthority::new).toList();
	}

}
