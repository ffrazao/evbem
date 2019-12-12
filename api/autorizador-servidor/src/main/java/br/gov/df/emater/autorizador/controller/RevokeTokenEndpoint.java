package br.gov.df.emater.autorizador.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FrameworkEndpoint
public class RevokeTokenEndpoint {

	@Autowired
	private TokenStore tokenStore;

	@RequestMapping(method = RequestMethod.DELETE, value = "/oauth/token")
	@ResponseBody
	public void revokeToken(final HttpServletRequest request) {
		final String authHeader = request.getHeader("Authorization");
		if (authHeader != null) {
			final String tokenValue = authHeader.replace("Bearer", "").trim();
			final OAuth2AccessToken accessToken = this.tokenStore.readAccessToken(tokenValue);
			this.tokenStore.removeAccessToken(accessToken);
		}
	}
}
