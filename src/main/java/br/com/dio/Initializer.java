package br.com.dio;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.dio.auth.entity.Role;
import br.com.dio.auth.entity.User;
import br.com.dio.auth.service.RoleService;
import br.com.dio.auth.service.UserService;

@Service
public class Initializer {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@PostConstruct
	public void criaUsuariosEPermissoes() {
		Role roleAdmin = new Role();
		//Definindo o nível de permissão do user teste
		roleAdmin.setName("ADMIN");

		roleService.save(roleAdmin);

		User user = new User();
		user.setAtivo(true);
		user.setEmail("teste@teste.com");
		user.setNome("Caio Cesar");
		user.setSenha(new BCryptPasswordEncoder().encode("123456"));
		user.setUsername("caiocsp");
		user.setRoles(Arrays.asList(roleAdmin));

		userService.save(user);

	}
}