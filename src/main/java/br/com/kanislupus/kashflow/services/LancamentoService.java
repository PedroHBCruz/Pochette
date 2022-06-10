package br.com.kanislupus.kashflow.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.kanislupus.kashflow.model.Lancamento;
import br.com.kanislupus.kashflow.model.Usuario;
import br.com.kanislupus.kashflow.model.dto.LancamentoDTO;
import br.com.kanislupus.kashflow.model.dto.LancamentoNewDTO;
import br.com.kanislupus.kashflow.model.enums.StatusLancamento;
import br.com.kanislupus.kashflow.model.enums.TipoLancamento;
import br.com.kanislupus.kashflow.model.enums.TipoPagamento;
import br.com.kanislupus.kashflow.repositories.LancamentoRepository;
import br.com.kanislupus.kashflow.services.exceptions.ObjectNotFoundException;
import br.com.kanislupus.kashflow.services.exceptions.RegraNegocioException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository repository;
	@Autowired
	private UsuarioService usuarioService;

	public Lancamento salvar(Lancamento lancamento) {
		lancamento.setStatus(StatusLancamento.PENDENTE);
		return repository.save(lancamento);
	}

	@Transactional
	public void atualizarStatus(Long id) {
		Lancamento obj = repository.findById(id).get();
		obj.setStatus(StatusLancamento.EFETIVADO);
		repository.save(obj);

	}

	@Transactional
	public void removerPorId(Long id) {
		obterPorId(id);
		repository.deleteById(id);

	}

	public Lancamento obterPorId(long id) {
		Optional<Lancamento> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Lançamento não encontrado."));
	}

	public BigDecimal obterSaldoPorUsuario(Long id) {
		BigDecimal receitas = repository.obterSaldoPorTipoLancamentoEUsuarioEStatus(id, 2, 2);
		BigDecimal despesas = repository.obterSaldoPorTipoLancamentoEUsuarioEStatus(id, 1, 2);

		if (receitas == null) {
			receitas = BigDecimal.ZERO;
		}
		if (despesas == null) {
			despesas = BigDecimal.ZERO;
		}
		return receitas.subtract(despesas);
	}

	public Lancamento fromDTO(LancamentoDTO dto) {
		Lancamento lancamento = new Lancamento();
		lancamento.setId(dto.getId());
		lancamento.setDescricao(dto.getDescricao());
		lancamento.setValor(dto.getValor());

		try {
			Usuario usuario = usuarioService.buscaPorId(dto.getUsuario());
			lancamento.setUsuario(usuario);
		} catch (RegraNegocioException e) {
			throw new RegraNegocioException("Usuário não encontrado para o Id informado.");
		}

		if (dto.getTipo() != null) {
			lancamento.setTipo(TipoLancamento.valueOf(dto.getTipo()));
		}
		if (dto.getStatus() != null) {
			lancamento.setStatus(StatusLancamento.valueOf(dto.getStatus()));
		}
		if (dto.getTipoPagamento() != null) {
			lancamento.setTipoPagamento(TipoPagamento.valueOf(dto.getTipoPagamento()));
		}

		lancamento.getDataCadastro();

		return lancamento;
	}

	public Lancamento fromDTO(LancamentoNewDTO dto) {
		Lancamento lancamento = new Lancamento();
		lancamento.setDescricao(dto.getDescricao());
		lancamento.setValor(dto.getValor());

		try {
			Usuario usuario = usuarioService.buscaPorId(dto.getUsuario());
			lancamento.setUsuario(usuario);
		} catch (RegraNegocioException e) {
			throw new RegraNegocioException("Usuário não encontrado para o Id informado.");
		}

		if (dto.getTipo() != null) {
			lancamento.setTipo(TipoLancamento.toEnum(dto.getTipo()));
		}
		if (dto.getStatus() != null) {
			lancamento.setStatus(StatusLancamento.toEnum(dto.getStatus()));
		}

		if (dto.getTipoPagamento() != null) {
			lancamento.setTipoPagamento(TipoPagamento.toEnum(dto.getTipoPagamento()));
		}

		lancamento.setDataCadastro(LocalDate.now());

		return lancamento;
	}
}
