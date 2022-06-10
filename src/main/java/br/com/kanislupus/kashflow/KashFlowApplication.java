package br.com.kanislupus.kashflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.kanislupus.kashflow.model.Usuario;
import br.com.kanislupus.kashflow.repositories.UsuarioRepository;

@SpringBootApplication
public class KashFlowApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KashFlowApplication.class, args);
	}

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private BCryptPasswordEncoder pe;

	@Override
	public void run(String... args) throws Exception {

		Usuario user = new Usuario(null, "usuario", "usuario@hotmail.com", pe.encode("123"), "Kanis Lupus");
		usuarioRepository.save(user);
	}

}
