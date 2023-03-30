package demo.test.app.controller;

import demo.test.app.config.auth.JwtTokenService;
import demo.test.app.dto.UserInputDto;
import demo.test.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author js
 */
@RestController
@RequestMapping("/api/auth/")
public class Auth {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenService jwtTokenUtil;
	
	@Autowired
	private UserService userDetailsService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserInputDto authenticationRequest) throws Exception {
		
		final Authentication auth = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		return ResponseEntity.ok(jwtTokenUtil.generateToken(auth));
	}
	
	private Authentication authenticate(String username, String password) throws Exception {
		try {
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
