package com.lmlasmo.ms.user.security.token;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lmlasmo.ms.user.dto.auth.UserAuthDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class AccessJWTProvider {

	private JWTProperties properties;

	public String gerateAccessToken(UserAuthDTO user) {
		RSAPrivateKey privateKey = (RSAPrivateKey) KeysProvider.getAccessKeys().getPrivate();
		Algorithm algorithm = Algorithm.RSA256(null, privateKey);

		Instant now = Instant.now();
		Instant expires = now.plus(properties.getAccessDuration());

		return JWT.create().withIssuer(properties.getIssuer())
				.withSubject(user.getId())
				.withArrayClaim("roles", user.getRoles())
				.withIssuedAt(now)
				.withExpiresAt(expires)
				.sign(algorithm);
	}

	public UserAuthDTO validateAccessToken(String token) {
		RSAPublicKey publicKey = (RSAPublicKey) KeysProvider.getAccessKeys().getPublic();
		Algorithm algorithm = Algorithm.RSA256(publicKey, null);
		DecodedJWT decoded = JWT.require(algorithm).build().verify(token);

		String[] roles = decoded.getClaim("roles").asArray(String.class);

		return new UserAuthDTO(decoded.getSubject(), roles);
	}

}
