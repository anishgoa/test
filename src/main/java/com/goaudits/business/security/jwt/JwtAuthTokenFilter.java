package com.goaudits.business.security.jwt;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtProvider tokenProvider;


	private static final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);
	
    @Value("${goaconsole.app.jwtSecret}")
    private String jwtSecret;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {

			String jwt = getJwt(request);
			if (jwt != null && tokenProvider.validateJwtToken(jwt)) {
//				String username = tokenProvider.getUserNameFromJwtToken(jwt);
				
				//HttpSession session = request.getSession();
				//UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) session.getAttribute("auth");

			//	if (auth == null) {
//				Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
//				if(authentication1!=null) {
//				UserPrinciple userPrinciple = (UserPrinciple) authentication1.getPrincipal();
//				System.out.println(userPrinciple);
//				}
				
//			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//							userDetails, null, userDetails.getAuthorities());
//					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
		        UsernamePasswordAuthenticationToken authentication = getAuthentication(jwt);


//				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,null);
//				authentication.setAuthenticated(true);
//				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContext sc = SecurityContextHolder.getContext();
					sc.setAuthentication(authentication);
					//session.setAttribute("auth", authentication);
//				}else {
//					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//					SecurityContext sc = SecurityContextHolder.getContext();
//					sc.setAuthentication(auth);
//				}
			}

		} catch (Exception e) {
			logger.error("Can NOT set user authentication -> Message: {}", e);
		}

		filterChain.doFilter(request, response);
	}

	private String getJwt(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.replace("Bearer ", "");
		}

		return null;
	}
	
	  private UsernamePasswordAuthenticationToken getAuthentication(String token) {
	        if (token != null) {
	            // parse the token.
	            String user = tokenProvider.getUserNameFromJwtToken(token);
	            if (user != null) {
	                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
	            }
	            return null;
	        }
	        return null;
	    }
}
