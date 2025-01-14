package com.zurad.java.resourceserver.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.zurad.java.resourceserver.exceptionhandling.CustomAccessDeniedHandler;
import com.zurad.java.resourceserver.filter.CsrfCookieFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class ProjectSecurityConfig {

    // these values needed for opaque tokens, which we're not using right now. just leaving this here for an example.
//    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-uri}")
//    String introspectionUri;
//
//    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-client-id}")
//    String clientId;
//
//    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-client-secret}")
//    String clientSecret;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new RoleConverter());

        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();

        http.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {
                @Override
                public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setExposedHeaders(Arrays.asList("Authorization"));
                    config.setMaxAge(3600L);
                    return config;
                }
            }))
            .csrf(csrfConfig -> csrfConfig.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                .ignoringRequestMatchers("/contact", "/register")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
            .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
            .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure()) // Only HTTP
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount").hasRole("USER")
                .requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/myLoans").authenticated()
                .requestMatchers("/myCards").hasRole("USER")
                .requestMatchers("/user").authenticated()
                .requestMatchers("/notices", "/contact", "/error", "/register").permitAll());

        http.oauth2ResourceServer(rsc -> rsc.jwt(jwtConfigurer ->
            jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter)));

        // opaque tokens are not being used right now. this code block is just here for an example
//        http.oauth2ResourceServer(rsc -> rsc.opaqueToken(otc ->
//            otc.authenticationConverter(new OpaqueRoleConverter())
//                .introspectionUri(this.introspectionUri)
//                .introspectionClientCredentials(this.clientId,this.clientSecret)));

        http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()));

        return http.build();
    }

    static class RoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

        @Override
        public Collection<GrantedAuthority> convert(Jwt source) {
            ArrayList<String> roles = (ArrayList<String>) source.getClaims().get("roles");
            if (roles == null || roles.isEmpty()) {
                return new ArrayList<>();
            }

            return roles.stream()
                .map(roleName -> "ROLE_" + roleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        }
    }

    // this role converter is currently not being used. just here for example.
    static class OpaqueRoleConverter implements OpaqueTokenAuthenticationConverter {
        /**
         * @param introspectedToken      the bearer token used to perform token introspection
         * @param authenticatedPrincipal the result of token introspection
         * @return
         */
        @Override
        public Authentication convert(String introspectedToken, OAuth2AuthenticatedPrincipal authenticatedPrincipal) {
            ArrayList<String> roles  = authenticatedPrincipal.getAttribute("scope");
            assert roles != null;
            Collection<GrantedAuthority> grantedAuthorities = roles
                .stream().map(roleName -> "ROLE_" + roleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

            return new UsernamePasswordAuthenticationToken(authenticatedPrincipal.getName(), null,
                grantedAuthorities);
        }
    }

}

