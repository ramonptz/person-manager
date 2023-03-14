package demo.manager.api.controller;

import java.util.List;
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

	//Lista pessoas sem mostrar o endereço, pode ser feita pesquisa pelo nome e retorna uma pageable
	@GetMapping()
	public Page<PessoaConsultaResponse> listarSemEnderecos(@RequestParam(required = false) String nome,
			@PageableDefault(sort = "nome", direction = Direction.ASC, page = 0, size = 15) Pageable paginacao) {
		Page<Pessoa> pessoas = pessoaService.listar(nome, paginacao);
		return pessoaConverter.toPageConsultaResponse(pessoas);

	}
	//Lista pessoas mostrando o endereço, pode ser feita pesquisa pelo nome e retorna uma pageable
	@GetMapping("/listar-com-endereco")
	public Page<PessoaResponse> listarComEndereco(@RequestParam(required = false) String nome,
			@PageableDefault(sort = "nome", direction = Direction.ASC, page = 0, size = 15) Pageable paginacao) {
		Page<Pessoa> pessoas = pessoaService.listar(nome, paginacao);
		return pessoaConverter.toPageResponse(pessoas);

	}

	//Edita pessoa precisando passar o id da pessoa pela url e altera somente os campos que são passados
	@PutMapping("/edita/{pessoaId}")
	public ResponseEntity<PessoaResponse> edita(@PathVariable Long pessoaId,
			@RequestBody PessoaRequest pessoaEditada) {
		Optional<Pessoa> pessoaExistente = pessoaService.buscarPorId(pessoaId);
		pessoaExistente.orElseThrow(() -> new PessoaNotFound());

		Pessoa pessoa = pessoaService.editaPessoa(pessoaExistente.get(), pessoaEditada);

		//pessoaService.salvar(pessoa);

		return ResponseEntity.ok(pessoaConverter.toResponse(pessoa));

	}

	//Cria uma nova pessoa, nome e data de nascimento são validados para não serem nulos
	@PostMapping("/criar")
	@ResponseStatus(HttpStatus.CREATED)
	public PessoaResponse criarPessoa(@Valid @RequestBody PessoaRequest pessoaRequest) {
		Pessoa novoPessoa = pessoaService.salvar(pessoaConverter.toEntity(pessoaRequest));
		return pessoaConverter.toResponse(novoPessoa);

	}

	//Consulta uma pessoa passando o numero do id
	@GetMapping("/consultar/{idPessoa}")
	public ResponseEntity<PessoaConsultaResponse> consultarPessoa(@PathVariable Long idPessoa) {
		Optional<Pessoa> pessoa = pessoaService.buscarPorId(idPessoa);

		pessoa.orElseThrow(() -> new PessoaNotFound());
		return ResponseEntity.ok(pessoaConverter.toConsultaResponse(pessoa.get()));

	}
	
	//Cria uma nova pessoa, nome e data de nascimento são validados para não serem nulos
		@PostMapping("/criar-lista")
		@ResponseStatus(HttpStatus.CREATED)
		public List<PessoaResponse> criarListaDePessoa(@Valid @RequestBody List<PessoaRequest> pessoaRequest) {
			List<Pessoa> novoPessoa = pessoaService.salvarLista(pessoaConverter.toEntityCollection(pessoaRequest));
			return pessoaConverter.toResponseCollection(novoPessoa);

		}


}