package com.lmlasmo.ms.user.security.token;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lmlasmo.ms.user.mapper.UserMapper;
import com.lmlasmo.ms.user.model.User;
import com.lmlasmo.ms.user.security.UserAuth;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class RefleshJWTProvider {

	private JWTProperties properties;

	public String gerateRefleshToken(User user) {
		UserAuth userAuth = Mappers.getMapper(UserMapper.class).toUserAuth(user);
		return gerateRefleshToken(userAuth);
	}

	public String gerateRefleshToken(UserAuth user) {
		RSAPrivateKey privateKey = (RSAPrivateKey) KeysProvider.getRefleshKeys().getPrivate();
		Algorithm algorithm = Algorithm.RSA256(null, privateKey);

		Instant now = Instant.now();
		Instant expires = now.plus(properties.getRefleshDuration());

		return JWT.create()
				.withIssuer(properties.getIssuer())
				.withSubject(user.getId())
				.withArrayClaim("roles", user.getRoles())
				.withIssuedAt(now)
				.withExpiresAt(expires)
				.sign(algorithm);
	}

	public UserAuth validateRefleshToken(String token) {
		RSAPublicKey publicKey = (RSAPublicKey) KeysProvider.getRefleshKeys().getPublic();
		Algorithm algorithm = Algorithm.RSA256(publicKey, null);
		DecodedJWT decoded = JWT.require(algorithm).build().verify(token);

		String[] roles = decoded.getClaim("roles").asArray(String.class);
		return new UserAuth(decoded.getSubject(), roles);
	}

}
