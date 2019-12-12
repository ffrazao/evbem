package br.gov.df.emater.autorizador.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

@Component
public class TokenEnhancerConfig implements TokenEnhancer {

	@Autowired
	private DataSource dataSource;

	public TokenEnhancerConfig() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public OAuth2AccessToken enhance(final OAuth2AccessToken accessToken, final OAuth2Authentication authentication) {
		final DefaultOAuth2AccessToken tempResult = (DefaultOAuth2AccessToken) accessToken;

		Map<String, Object> details = new HashMap<>();
		final Object userDetails = authentication.getUserAuthentication().getDetails();
		if (userDetails != null) {
			details = (Map<String, Object>) userDetails;
		}

		try (Connection con = this.dataSource.getConnection()) {
			final StringBuilder sql = new StringBuilder();
			sql.append("SELECT DISTINCT").append("\n");
			sql.append("    id, nome, email, foto, pessoa_id").append("\n");
			sql.append("FROM").append("\n");
			sql.append("    sistema.usuario").append("\n");
			sql.append("WHERE").append("\n");
			sql.append("    login = ?").append("\n");
			try (PreparedStatement ps = con.prepareStatement(sql.toString())) {
				ps.setString(1, authentication.getName());
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						details.put("id", rs.getInt("id"));
						details.put("nome", rs.getString("nome"));
						details.put("email", rs.getString("email"));
						details.put("foto", rs.getBytes("foto"));
						details.put("pessoaId", rs.getInt("pessoa_id"));
					}
				}
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		tempResult.setAdditionalInformation(details);

		return tempResult;
	}

}
