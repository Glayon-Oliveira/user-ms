package com.lmlasmo.ms.user.security.token;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "app.jwt")
public class JWTProperties {

	private String issuer;
	private Duration refleshDuration;
	private Duration accessDuration;

}
