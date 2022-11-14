package br.com.dio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//Configurações de autenticação
//É possível especificar a autenticação para cada requisição.

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http.csrf().disable() //desabilitando uma possibilidade de ataque sem autorização
         .authorizeRequests().anyRequest().authenticated()
         .and()
         .httpBasic();//Especificando o tipo de autenticação como básico
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("usuario")
		.password("{noop}senha") //{noop} é para seguir como um teste, sem criptografar
        /*.withUser("caio")
		.password("{noop}123456") */
		.roles("USER");
	}


}