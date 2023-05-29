package com.sdd.sddpartner.config;

import java.util.Arrays;

import com.sdd.sddpartner.common.security.CustomAccessDeniedHandler;

import com.sdd.sddpartner.common.security.RestAuthenticationEntryPoint;
import com.sdd.sddpartner.common.security.jwt.filter.JwtAuthenticationFilter;
import com.sdd.sddpartner.common.security.jwt.filter.JwtRequestFilter;
import com.sdd.sddpartner.common.security.jwt.provider.JwtTokenProvider;
import com.sdd.sddpartner.prop.ShopProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@RequiredArgsConstructor
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

//	private final JwtTokenProvider jwtTokenProvider;

	@Autowired
	private ShopProperties shopProperties;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("security config ...");

		http.formLogin().disable().httpBasic().disable();
		http.csrf().disable();
		http.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterAt(new JwtAuthenticationFilter(authenticationManager(), shopProperties), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JwtRequestFilter(shopProperties), UsernamePasswordAuthenticationFilter.class);
		http.cors();

//		http.addFilterAt(new JwtAuthenticationFilter(authenticationManager(), jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
//		.addFilterBefore(new JwtRequestFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
//        .sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeRequests()
		.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
		.antMatchers("/").permitAll()
		.antMatchers("/codes/**").access("permitAll")
		.antMatchers("/users/**").access("permitAll")
		.antMatchers("/employee/**").permitAll()
		.antMatchers("/attendance/**").access("permitAll")
		.antMatchers("/dayOffs/**").access("permitAll")
		.antMatchers("/dayOffDistinctions/**").access("permitAll")
		.antMatchers("/salary/**").access("permitAll")
		.antMatchers("/codegroups/**").access("hasRole('ADMIN')")
		.antMatchers("/codedetails/**").access("hasRole('ADMIN')")
		.antMatchers("/boards/**").access("request.method == 'GET' ? permitAll : hasAnyRole('MEMBER', 'ADMIN')")
		.antMatchers("/notices/**").access("request.method == 'GET' ? permitAll : hasRole('ADMIN')")
		.antMatchers("/items/**").access("request.method == 'GET' ? permitAll : hasRole('ADMIN')")
		.antMatchers("/coins/**").access("hasRole('MEMBER')")
		.antMatchers("/useritems/**").access("hasAnyRole('MEMBER', 'ADMIN')")
		.antMatchers("/pds/**").access("request.method == 'GET' ? permitAll : hasRole('ADMIN')")
		.anyRequest().authenticated();
		
		http.exceptionHandling()
				.authenticationEntryPoint(new RestAuthenticationEntryPoint())
				.accessDeniedHandler(accessDeniedHandler());
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("user01").password(passwordEncoder().encode("1234")).roles("MEMBER")
				.and()
				.withUser("user02").password("{noop}1234").roles("MEMBER", "ADMIN")
				.and()
				.withUser("admin").password("{noop}1234").roles("ADMIN");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/*@Bean
	public UserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}
	*/
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    
	    CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:3030/");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        config.setExposedHeaders(Arrays.asList("Authorization","Content-Disposition"));
        
	    source.registerCorsConfiguration("/**", config);
	    
	    return source;
	}
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
	
}
