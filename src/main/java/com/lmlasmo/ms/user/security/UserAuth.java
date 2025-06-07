package com.lmlasmo.ms.user.security;

import java.math.BigInteger;
import java.util.Collection;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserAuth {

	@NonNull
	private String id;

	@NonNull
	private String[] roles;

	public BigInteger idToBigInteger() {
		return new BigInteger(id);
	}

	public Collection<? extends GrantedAuthority> rolesToAuthorities(){
		return Stream.of(roles).map(SimpleGrantedAuthority::new).toList();
	}

	public static UserAuth getAuth() {
		return (UserAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
