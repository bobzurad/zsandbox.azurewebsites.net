package com.zurad.java.authserver.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig {

	// default configuration taken from: https://docs.spring.io/spring-authorization-server/reference/1.3/getting-started.html#defining-required-components

    @Bean 
	@Order(1)
	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

		http
			.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
			.oidc(Customizer.withDefaults());	// Enable OpenID Connect 1.0

	    http
			// Redirect to the login page when not authenticated from the
			// authorization endpoint
			.exceptionHandling((exceptions) -> exceptions
				.defaultAuthenticationEntryPointFor(
					new LoginUrlAuthenticationEntryPoint("/login"),
					new MediaTypeRequestMatcher(MediaType.TEXT_HTML)))
			// Accept access tokens for User Info and/or Client Registration
			.oauth2ResourceServer((resourceServer) -> resourceServer
				.jwt(Customizer.withDefaults()));

		return http.build();
	}

	@Bean 
	@Order(2)
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorize) -> authorize
				.anyRequest().authenticated())
			// Form login handles the redirect to the login page from the
			// authorization server filter chain
			.formLogin(Customizer.withDefaults());

		return http.build();
	}

	// default UserDetailsService commented out because we're using EazyBankUserDetailsService instead
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails userDetails = User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("password")
//				.roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(userDetails);
//	}

	// default RegisteredClientRepository commented out because we're going to make our own (see below)
//	@Bean
//	public RegisteredClientRepository registeredClientRepository() {
//		RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
//				.clientId("oidc-client")
//				.clientSecret("{noop}secret")
//				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)  /// note you can set multiple Grant Types...
//				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//				.redirectUri("http://127.0.0.1:8080/login/oauth2/code/oidc-client")
//				.postLogoutRedirectUri("http://127.0.0.1:8080/")    // TODO: add postLogoutRedirectUri to our RegisteredClients (below) when we have a web/client app?
//				.scope(OidcScopes.OPENID)
//				.scope(OidcScopes.PROFILE)
//				.clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build()) // TODO: lookup what this does
//				.build();
//
//		return new InMemoryRegisteredClientRepository(oidcClient);
//	}

	@Bean
	public RegisteredClientRepository registeredClientRepository() {

		// RegisteredClient for client_credentials grant type
		RegisteredClient clientCredentialsClient = RegisteredClient.withId(UUID.randomUUID().toString())
			.clientId("eazybankapi")
			.clientSecret("{noop}VxubZgAXyyTq9lGjj3qGvWNsHtE4SqTq") // some random string
			.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
			.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)  // refresh_token should NOT be used with a service account that uses client_credentials
			.scopes(scopeConfig -> scopeConfig.addAll(List.of(OidcScopes.OPENID, "ADMIN", "USER")))
			.tokenSettings(TokenSettings.builder()
				.accessTokenTimeToLive(Duration.ofMinutes(10))
				.accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
				.build())
			.build();

		// RegisteredClient for client_credentials with opaque tokens (not being used right now)
		RegisteredClient introspectClient = RegisteredClient.withId(UUID.randomUUID().toString())
			.clientId("eazybankintrospect")
			.clientSecret("{noop}c1BK9Bg2REeydBbvUoUeKCbD2bvJzXGj")
			.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
			.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
			.scopes(scopeConfig -> scopeConfig.add(OidcScopes.OPENID))
			.tokenSettings(TokenSettings.builder()
				.accessTokenTimeToLive(Duration.ofMinutes(10))
				.accessTokenFormat(OAuth2TokenFormat.REFERENCE)
				.build())
			.build();

		// RegisteredClient for authorization_code grant type
		RegisteredClient authCodeClient = RegisteredClient.withId(UUID.randomUUID().toString())
			.clientId("eazybankclient")
			.clientSecret("{noop}Qw3rTy6UjMnB9zXcV2pL0sKjHn5TxQqB") // some random string
			.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
			.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
			.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
			.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
			.redirectUri("https://oauth.pstmn.io/v1/callback")
			.scopes(scopeConfig -> scopeConfig.addAll(List.of(OidcScopes.OPENID, OidcScopes.EMAIL)))
			.tokenSettings(TokenSettings.builder()
				.accessTokenTimeToLive(Duration.ofMinutes(10))
				.refreshTokenTimeToLive(Duration.ofHours(8))
				.reuseRefreshTokens(false)
				.accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
				.build())
			.build();

		// RegisteredClient for authorization_code with pkce grant type
		RegisteredClient pkceClient = RegisteredClient.withId(UUID.randomUUID().toString())
			.clientId("eazypublicclient")
			.clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
			.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
			.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
			.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
			.redirectUri("https://oauth.pstmn.io/v1/callback")
			.scopes(scopeConfig -> scopeConfig.addAll(List.of(OidcScopes.OPENID, OidcScopes.EMAIL)))
			.clientSettings(ClientSettings.builder().requireProofKey(true).build())
			.tokenSettings(TokenSettings.builder()
				.accessTokenTimeToLive(Duration.ofMinutes(10))
				.refreshTokenTimeToLive(Duration.ofHours(8))
				.reuseRefreshTokens(false)
				.accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
				.build())
			.build();

		// TODO: if we want to store the registered client settings in the database, instead of in-memory, then we must use
		//          JdbcRegisteredClientRepository instead of InMemoryRegisteredClientRepository, or implement our own RegisteredClientRepository
		return new InMemoryRegisteredClientRepository(
			clientCredentialsClient,
			authCodeClient,
			pkceClient,
			introspectClient
		);
	}

	@Bean 
	public JWKSource<SecurityContext> jwkSource() {
		KeyPair keyPair = generateRsaKey();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		RSAKey rsaKey = new RSAKey.Builder(publicKey)
				.privateKey(privateKey)
				.keyID(UUID.randomUUID().toString())
				.build();
		JWKSet jwkSet = new JWKSet(rsaKey);
		return new ImmutableJWKSet<>(jwkSet);
	}

	private static KeyPair generateRsaKey() { 
		KeyPair keyPair;
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			keyPair = keyPairGenerator.generateKeyPair();
		}
		catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
		return keyPair;
	}

	@Bean 
	public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
		return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
	}

	@Bean 
	public AuthorizationServerSettings authorizationServerSettings() {
		return AuthorizationServerSettings.builder().build();
	}

	@Bean
	public OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer() {
		return (context) -> {
			if (context.getTokenType().equals(OAuth2TokenType.ACCESS_TOKEN)) {
				context.getClaims().claims((claims) -> {
					if (context.getAuthorizationGrantType().equals(AuthorizationGrantType.CLIENT_CREDENTIALS)) {
						// for client_credentials grant type, copy "scope" into "roles"
						Set<String> scopes = context.getClaims().build().getClaim("scope");
						claims.put("roles", scopes);
					} else if (context.getAuthorizationGrantType().equals(AuthorizationGrantType.AUTHORIZATION_CODE)) {
						// for authorization_code grant type, copy the user's authorities into "roles"
						Set<String> authorities = AuthorityUtils
							.authorityListToSet(context.getPrincipal().getAuthorities())
							.stream()
							.map(c -> c.replaceFirst("^ROLE_", "")) // remove ROLE_ prefix, if it exists
							.collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
						claims.put("roles", authorities);
					}
				});
			}
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	/**
	 * HaveIBeenPwnedRestApiPasswordChecker requires Spring Security 6.3 or later.
	 * This bean is completely optional, and not currently being used.
     */
	@Bean
	public CompromisedPasswordChecker compromisedPasswordChecker() {
		return new HaveIBeenPwnedRestApiPasswordChecker();
	}
}
