package demo.manager.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import demo.manager.api.converter.EnderecoConverter;
import demo.manager.api.exceptionhandler.EnderecoNotFound;
import demo.manager.api.request.EnderecoRequest;
import demo.manager.api.response.EnderecoResponse;
import demo.manager.domain.model.Cep;
import demo.manager.domain.model.Endereco;
import demo.manager.domain.services.CepService;
import demo.manager.domain.services.EnderecoService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/endereco")
public class EnderecoController {

	private EnderecoService enderecoService;
	private EnderecoConverter enderecoConverter;
	private CepService cepService;

	// Cria novo endereço vinculado a pessoa que tem seu id passado na URL
	// o primeiro endereço sempre será o principal até que outro seja criado ou
	// atualizado e passado como principal
	@PostMapping("/criar/{idPessoa}")
	@ResponseStatus(HttpStatus.CREATED)
	public EnderecoResponse criarEndereco(@Valid @RequestBody EnderecoRequest enderecoRequest,@PathVariable Long idPessoa) {

		cepService.verificaCepESalva(enderecoRequest.getNumeroCep());
		enderecoRequest.setCep(cepService.verificaCepESalva(enderecoRequest.getNumeroCep()));
		cepService.criaOuAtualizaCep(enderecoRequest.getCep());
		
		Endereco novoEndereco = enderecoService.novoEndereco((enderecoConverter.toEntity(enderecoRequest)), idPessoa);
		return enderecoConverter.toResponse(novoEndereco);

	}

	// Atualiza o enderço e pode também ser feita a alteração de endereço principal
	// através desse método
	@PutMapping("atualizar/{enderecoId}")
	public ResponseEntity<EnderecoResponse> atualizar(@PathVariable Long enderecoId,
			@RequestBody EnderecoRequest enderecoAtualizado) {
		Optional<Endereco> enderecoExistente = enderecoService.buscarPorId(enderecoId);
		enderecoExistente.orElseThrow(() -> new EnderecoNotFound());

		Endereco endereco = enderecoService.atualizaEndereco(enderecoExistente.get(), enderecoAtualizado);

		enderecoService.salvarEAtualizaEnderecoPrincipal(endereco, enderecoExistente.get().getPessoa().getId());

		return ResponseEntity.ok(enderecoConverter.toResponse(endereco));
	}

	// Retorna uma lista com os endereços da pessoa que tem seu id passado na URL
	@GetMapping("/listarEnderecos/{pessoaId}")
	public List<EnderecoResponse> listar(@PathVariable Long pessoaId) {
		List<Endereco> endereco = enderecoService.listarPelaPessoa(pessoaId);
		return enderecoConverter.toResponseCollection(endereco);
	}

}
