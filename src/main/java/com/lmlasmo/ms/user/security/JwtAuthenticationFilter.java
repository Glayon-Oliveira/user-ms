package com.lmlasmo.ms.user.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lmlasmo.ms.user.dto.auth.UserAuthDTO;
import com.lmlasmo.ms.user.model.UserStatusType;
import com.lmlasmo.ms.user.repository.UserRepository;
import com.lmlasmo.ms.user.security.token.AccessJWTProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private AccessJWTProvider jwtProvider;
	private UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getToken(request);

		if(token != null) authenticate(token);

		filterChain.doFilter(request, response);
	}

	protected void authenticate(String token) {
		UserAuthDTO user = jwtProvider.validateAccessDTO(token);

		if(!userRepository.existsByIdAndStatus(user.idToBigInteger(), UserStatusType.ACTIVE)) return;

		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.rolesToAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	protected String getToken(HttpServletRequest request) {
		String bearer = request.getHeader("Authorization");

		if((bearer == null) || !bearer.contains("Bearer")) return null;

		return bearer.replaceAll("(?i)Bearer\\s*", "");
	}

}
