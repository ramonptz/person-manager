package demo.manager.api.controllers;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import demo.manager.api.converter.Conversor;
import demo.manager.api.exceptionhandler.PessoaNotFound;
import demo.manager.api.request.PessoaRequest;
import demo.manager.api.response.PessoaResponse;
import demo.manager.api.response.PessoaSemEnderecoResponse;
import demo.manager.domain.model.Pessoa;
import demo.manager.domain.services.PessoaService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/pessoa")
public class PessoaController {

	private Conversor conversor;
	private PessoaService pessoaService;
	// private ModelMapper model = new ModelMapper();
	//Lista pessoas sem mostrar o endereço, pode ser feita pesquisa pelo nome e retorna uma pageable
	@GetMapping()
	public Page<PessoaSemEnderecoResponse> listarSemEnderecos(@RequestParam(required = false) String nome,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 15) Pageable paginacao) {
		Page<Pessoa> pessoas = pessoaService.listar(nome, paginacao);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("ramon"));
		return conversor.converterParaPage(pessoas, PessoaSemEnderecoResponse.class, paginacao);

	}
	//Lista pessoas mostrando o endereço, pode ser feita pesquisa pelo nome e retorna uma pageable
	@GetMapping("/listar-com-endereco")
	public Page<PessoaResponse> listarComEndereco(@RequestParam(required = false) String nome,
			@PageableDefault(sort = "nome", direction = Direction.ASC, page = 0, size = 15) Pageable paginacao) {
		Page<Pessoa> pessoas = pessoaService.listar(nome, paginacao);
		return conversor.converterParaPage(pessoas, PessoaResponse.class, paginacao);

	}

	//Edita pessoa precisando passar o id da pessoa pela url e altera somente os campos que são passados
	@PutMapping("/edita")
	public ResponseEntity<PessoaResponse> edita(@RequestParam Long pessoaId,
			@RequestBody PessoaRequest pessoaEditada) {
		Optional<Pessoa> pessoaExistente = pessoaService.buscarPorId(pessoaId);
		pessoaExistente.orElseThrow(() -> new PessoaNotFound());
		
		// Pessoa pessoa = pessoaExistente.get();
		// conversor.mapper().getConfiguration().setPropertyCondition(context -> context.getSource() != null);
		// conversor.mapper().map(pessoaEditada, pessoa);

		// var pessoaAtualizada = conversor.atualizarObjeto(pessoaEditada, pessoaExistente.get());

		// pessoaService.salvar(pessoaExistente.get());

		
		Pessoa pessoa = pessoaService.editaPessoa(pessoaExistente.get(), pessoaEditada);


		return ResponseEntity.ok(conversor.converter(pessoa, PessoaResponse.class));

	}

	//Cria uma nova pessoa, nome e data de nascimento são validados para não serem nulos
	@PostMapping("/criar")
	@ResponseStatus(HttpStatus.CREATED)
	public PessoaResponse criarPessoa(@Valid @RequestBody PessoaRequest pessoaRequest) {
		Pessoa novoPessoa = pessoaService.salvar(conversor.converter(pessoaRequest, Pessoa.class));
		return conversor.converter(novoPessoa, PessoaResponse.class);

	}

	//Consulta uma pessoa passando o numero do id
	@GetMapping("/consultar")
	public ResponseEntity<PessoaSemEnderecoResponse> consultarPessoa(@RequestParam Long idPessoa) {
		Optional<Pessoa> pessoa = pessoaService.buscarPorId(idPessoa);

		pessoa.orElseThrow(() -> new PessoaNotFound());
		//return ResponseEntity.ok(pessoaConverter.toConsultaResponse(pessoa.get()));
		return ResponseEntity.ok(conversor.converter(pessoa.get(), PessoaSemEnderecoResponse.class));

	}
	
	//Cria novas pessoas passadas por umas lista(array), nome e data de nascimento são validados para não serem nulos
		@PostMapping("/criar-lista")
		@ResponseStatus(HttpStatus.CREATED)
		public List<PessoaResponse> criarListaDePessoa(@Valid @RequestBody List<PessoaRequest> pessoaRequest) {
			List<Pessoa> novoPessoa = pessoaService.salvarLista(conversor.converterParaList(pessoaRequest,Pessoa.class));
			return conversor.converterParaList(novoPessoa, PessoaResponse.class);
			
		}


}