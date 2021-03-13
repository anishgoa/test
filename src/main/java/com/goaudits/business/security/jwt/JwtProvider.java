package com.goaudits.business.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.goaudits.business.security.services.UserPrinciple;
import com.goaudits.business.util.EncrypterHelper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {

	@Autowired
	EncrypterHelper EncrypterHelper;

	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

	@Value("${goaconsole.app.jwtSecret}")
	private String jwtSecret;

	@Value("${goaconsole.app.jwtExpiration}")
	private int jwtExpiration;

	public String generateJwtToken(Authentication authentication) {

		UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

		String jwt = Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpiration * 1000))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).setId(userPrincipal.getGuid())
				.setIssuer(userPrincipal.getUid()).compact(); // build jwt string which is url safe
//		System.out.println(jwt);
		String enpt= EncrypterHelper.encrypt(jwt).replaceAll("[\\t\\n\\r]+","");
//		System.out.println(enpt);
		return enpt;
//		return jwt;
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature -> Message: {} ", e);
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token -> Message: {}", e);
		} catch (ExpiredJwtException e) {
			logger.error("Expired JWT token -> Message: {}", e);
		} catch (UnsupportedJwtException e) {
			logger.error("Unsupported JWT token -> Message: {}", e);
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty -> Message: {}", e);
		}

		return false;
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public String getJWTToken(String username, String guid, String uid) {
		// String secretKey = "mySecretKey";
		/*
		 * List<GrantedAuthority> grantedAuthorities = AuthorityUtils
		 * .commaSeparatedStringToAuthorityList("ROLE_USER");
		 * 
		 * String token = Jwts .builder() .setId("softtekJWT") .setSubject(username)
		 * .claim("authorities", grantedAuthorities.stream()
		 * .map(GrantedAuthority::getAuthority) .collect(Collectors.toList()))
		 * .setIssuedAt(new Date(System.currentTimeMillis())) .setExpiration(new
		 * Date(System.currentTimeMillis() + 600000))
		 * .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
		 */

		String jwt = Jwts.builder().setSubject((username)).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpiration * 1000))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).setId(guid).setIssuer(uid).compact(); // build jwt string
																										// which is url
																										// safe

		return EncrypterHelper.encrypt(jwt);
	}

}
