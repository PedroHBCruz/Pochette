package br.com.kanislupus.kashflow.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import br.com.kanislupus.kashflow.model.Usuario;
import br.com.kanislupus.kashflow.model.dto.UsuarioDTO;
import br.com.kanislupus.kashflow.model.dto.UsuarioNewDTO;
import br.com.kanislupus.kashflow.repositories.UsuarioRepository;
import br.com.kanislupus.kashflow.security.UserSS;
import br.com.kanislupus.kashflow.services.exceptions.AuthorizationException;
import br.com.kanislupus.kashflow.services.exceptions.ObjectNotFoundException;
import br.com.kanislupus.kashflow.services.exceptions.RegraNegocioException;



@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public Usuario criar(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	public Usuario buscaPorId(Long id) {
		UserSS user = UserService.authenticated();
		if (user==null || !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				" Objeto não encontrado ! id:" + id + "tipo:" + Usuario.class.getName()));
	}

	public void obterSaldo(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		if (!obj.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
	}

	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if (existe) {
			throw new RegraNegocioException("Já existe um usuário cadastrado com este email.");
		}
	}

	public Usuario fromDTO(UsuarioNewDTO objDto) {
		return new Usuario(null, objDto.getNome(), objDto.getEmail(), passwordEncoder.encode(objDto.getSenha()), objDto.getNomeEmpresa());
	}

	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(null, objDto.getNome(), objDto.getEmail(), null, objDto.getNomeEmpresa());
	}
}
