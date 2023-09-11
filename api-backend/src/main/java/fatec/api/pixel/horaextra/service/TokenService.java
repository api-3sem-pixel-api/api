package fatec.api.pixel.horaextra.service;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import fatec.api.pixel.horaextra.model.AutenticacaoUsuario;

@Service
public class TokenService {
	
	@Value("{api.security.token.secret}")
	private String secret;
	
	public String gerarToken(AutenticacaoUsuario autenticacaoUsuario) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256(secret);
		    return JWT.create()
			        .withIssuer("API HoraExtra")
			        .withSubject(autenticacaoUsuario.getLogin())
			        .withExpiresAt(dataExpiracao())
			        .sign(algorithm);
		} catch (JWTCreationException exception){
		    throw new RuntimeException("erro ao gerar token jwt", exception);
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusMinutes(30l).toInstant(ZoneOffset.UTC.of("-03:00"));
	}
}
