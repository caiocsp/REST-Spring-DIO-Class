package br.com.dio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.dio.auth.service.UserService;
import br.com.dio.config.jwt.JwtAuthenticationEntryPoint;
import br.com.dio.config.jwt.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//Configurações de autenticação
//É possível especificar o nível de autenticação para cada requisição (A partir da linha 53).


	@Autowired
	public UserService userService;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf().disable()
				.authorizeRequests().antMatchers("/auth/signin").permitAll()
				.antMatchers("/actuator/**").permitAll()
				.antMatchers("/produto/**").hasAnyAuthority("ADMIN")
				.antMatchers("/pedido/**").authenticated()
				.anyRequest().authenticated()
				.and()
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				//Stateless, não quero que armazene status de login do usuário evitando quebra de performance
				//Agora todos os endpoints exceto login, necessitam do Token retornado em nossa autenticação


		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	//@Autowired
	//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	//		auth.inMemoryAuthentication()
	//		.withUser("caiocsp")
	//		.password("{noop}123456")
	//		.roles("USER");
	//	}
}