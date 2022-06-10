package br.com.kanislupus.kashflow.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.kanislupus.kashflow.model.Lancamento;
import br.com.kanislupus.kashflow.model.dto.LancamentoNewDTO;
import br.com.kanislupus.kashflow.services.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoService service;

	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody LancamentoNewDTO objDto) {
		Lancamento obj = service.fromDTO(objDto);
		obj = service.salvar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Lancamento> removerPorId(@PathVariable Long id) {
		service.removerPorId(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Void> atualizarStatus(@PathVariable Long codigo) {
		service.atualizarStatus(codigo);
		return ResponseEntity.noContent().build();
	}

}
