package br.com.kanislupus.kashflow.resources;

import java.math.BigDecimal;
import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.kanislupus.kashflow.model.Usuario;
import br.com.kanislupus.kashflow.model.dto.UsuarioNewDTO;
import br.com.kanislupus.kashflow.services.LancamentoService;
import br.com.kanislupus.kashflow.services.UsuarioService;



@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;
	
	@Autowired
	private LancamentoService lancamentoService;

	@PostMapping
	public ResponseEntity<Void> criar(@Valid @RequestBody UsuarioNewDTO objDto) {
		Usuario obj = service.fromDTO(objDto);
		obj = service.criar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
		Usuario obj = service.buscaPorId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/{id}/saldo")
	public ResponseEntity<?> obterSaldo(@PathVariable Long id) {
		BigDecimal saldo = lancamentoService.obterSaldoPorUsuario(id);
		return ResponseEntity.ok(saldo);
	}

}
