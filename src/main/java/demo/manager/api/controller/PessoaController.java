package demo.manager.api.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import demo.manager.api.converter.PessoaConverter;
import demo.manager.api.exceptionhandler.PessoaNotFound;
import demo.manager.api.request.PessoaRequest;
import demo.manager.api.response.PessoaConsultaResponse;
import demo.manager.api.response.PessoaResponse;
import demo.manager.domain.model.Pessoa;
import demo.manager.domain.repository.PessoaRepository;
import demo.manager.domain.services.PessoaService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/pessoa")
public class PessoaController {

	private PessoaConverter pessoaConverter;
	private PessoaService pessoaService;
	private PessoaRepository pessoaRepository;

	@GetMapping()
	public Page<PessoaResponse> listar(@RequestParam(required = false) String nome,
			@PageableDefault(sort = "nome", direction = Direction.ASC, page = 0, size = 15) Pageable paginacao) {
		Page<Pessoa> pessoas = pessoaService.listar(nome, paginacao);
		return pessoaConverter.converter(pessoas);

	}

	@PutMapping("/atualizar/{pessoaId}")
	public ResponseEntity<PessoaResponse> atualizar(@PathVariable Long pessoaId,
			@RequestBody PessoaRequest pessoaAtualizada) {
		Optional<Pessoa> pessoaExistente = pessoaService.buscarPorId(pessoaId);
//		if (!pessoaExistente.isPresent()) {
//			return ResponseEntity.notFound().build();
//		}
		pessoaExistente.orElseThrow(()-> new PessoaNotFound());

		Pessoa pessoa = pessoaExistente.get();
		if (pessoaAtualizada.getNome() == null) {
			pessoa.setNome(pessoaExistente.get().getNome());
		} else {
			pessoa.setNome(pessoaAtualizada.getNome());
		}

		if (pessoaAtualizada.getDataDeNascimento() == null) {
			pessoa.setDataDeNascimento(pessoaExistente.get().getDataDeNascimento());
		} else {
			pessoa.setDataDeNascimento(pessoaAtualizada.getDataDeNascimento());
		}

		pessoaService.salvar(pessoa);

		return ResponseEntity.ok(pessoaConverter.toResponse(pessoa));

	}

	@PostMapping("/criar")
	@ResponseStatus(HttpStatus.CREATED)
	public PessoaResponse criarPessoa(@Valid @RequestBody PessoaRequest pessoaRequest) {
		Pessoa novoPessoa = pessoaService.salvar(pessoaConverter.toEntity(pessoaRequest));
		return pessoaConverter.toResponse(novoPessoa);

	}

	@GetMapping("/consultar/{idPessoa}")
	public ResponseEntity<PessoaConsultaResponse> consultarPessoa(@PathVariable Long idPessoa) {
		Optional<Pessoa> pessoa = pessoaService.buscarPorId(idPessoa);

		pessoa.orElseThrow(()-> new PessoaNotFound());
		return ResponseEntity.ok(pessoaConverter.toConsultaResponse(pessoa.get()));
		
//		if (pessoa.isPresent()) {
//			return ResponseEntity.ok(pessoaConverter.toConsultaResponse(pessoa.get()));
//		}
//
//		return ResponseEntity.notFound().build();
	}

}