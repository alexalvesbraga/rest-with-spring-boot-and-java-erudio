package br.com.erudio.service;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.erudio.exceptions.ForbiddenException;
import br.com.erudio.model.data.vo.v1.security.AccountCredentialVO;
import br.com.erudio.model.data.vo.v1.security.TokenVO;
import br.com.erudio.repository.UserRepository;
import br.com.erudio.security.jwt.JwtTokenProvider;

@Service
public class AuthServices {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UserRepository repository;
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity signin(AccountCredentialVO data) {
		try {
			var username = data.getUserName();
			var password = data.getPassword();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));			
			var user = repository.findByUserName(username);			
			var tokenResponse = new TokenVO();
			if (user != null) {
				tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
			} else {
				throw new ForbiddenException("Usuário ou Senha Inválidos");
			}
			return ResponseEntity.ok(tokenResponse);
		} catch (Exception e) {
			throw new ForbiddenException("Usuário ou Senha Inválidos");
		}
	}
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity refreshToken(String username, String refreshToken) {
		var user = repository.findByUserName(username);		
		var tokenResponse = new TokenVO();
		if (user != null) {
			tokenResponse = tokenProvider.refreshToken(refreshToken);
		} else {
			throw new ForbiddenException("Usuário ou Senha Inválidos");
		}
		return ResponseEntity.ok(tokenResponse);
	}
}