package br.com.kanislupus.kashflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.kanislupus.kashflow.model.Usuario;



public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	boolean existsByEmail(String email);
	
	@Transactional(readOnly = true)
	Usuario findByEmail(String email);
	
	 
}
