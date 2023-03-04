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

import demo.manager.api.converter.EnderecoConverter;
import demo.manager.api.exceptionhandler.EnderecoNotFound;
import demo.manager.api.request.EnderecoRequest;
import demo.manager.api.response.EnderecoResponse;
import demo.manager.domain.model.Endereco;
import demo.manager.domain.services.EnderecoService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/endereco")
public class EnderecoController {
	
	private EnderecoService enderecoService;
	private EnderecoConverter enderecoConverter;

	@PostMapping("/criar/{idPessoa}")
	@ResponseStatus(HttpStatus.CREATED)
	public EnderecoResponse criarEndereco(@Valid @RequestBody EnderecoRequest enderecoRequest, @PathVariable Long idPessoa) {
		Endereco novoEndereco = enderecoService.novoEndereco((enderecoConverter.toEntity(enderecoRequest)),idPessoa);
		return enderecoConverter.toResponse(novoEndereco);

	}
	
	@PutMapping("atualizar/{enderecoId}")
	public ResponseEntity<EnderecoResponse> atualizar(@PathVariable Long enderecoId,
			@RequestBody EnderecoRequest enderecoAtualizado){
		Optional<Endereco> enderecoExistente = enderecoService.buscarPorId(enderecoId);
//		if(!enderecoExistente.isPresent()) {
//		return ResponseEntity.notFound().build();
//		}
		enderecoExistente.orElseThrow(()-> new EnderecoNotFound());
		
		Endereco endereco = enderecoExistente.get();
		if (enderecoAtualizado.getCep()==null) {
			endereco.setCep(enderecoExistente.get().getCep());
		} else {
			endereco.setCep(enderecoAtualizado.getCep());
		}
		
		if (enderecoAtualizado.getCidade() == null) {
			endereco.setCidade(enderecoExistente.get().getCidade());
		} else {
			endereco.setCidade(enderecoAtualizado.getCidade());
		}
		if (enderecoAtualizado.getLogradouro() == null) {
			endereco.setLogradouro(enderecoExistente.get().getLogradouro());
		} else {
			endereco.setLogradouro(enderecoAtualizado.getLogradouro());
		}
		if(enderecoAtualizado.getNumero() == null) {
			endereco.setNumero(enderecoExistente.get().getNumero());
		} else {
			endereco.setNumero(enderecoAtualizado.getNumero());
		}
		if(enderecoAtualizado.getEnderecoPrincipal() == null) {
			endereco.setEnderecoPrincipal(enderecoExistente.get().getEnderecoPrincipal());
		} else {
			endereco.setEnderecoPrincipal(enderecoAtualizado.getEnderecoPrincipal());
		}

		enderecoService.salvarEAtualizaEnderecoPrincipal(endereco,enderecoExistente.get().getPessoa().getId());
		
		
		return ResponseEntity.ok(enderecoConverter.toResponse(endereco));
	}
	
	@GetMapping("/listarEnderecos/{pessoaId}")
	public List<EnderecoResponse> listar(@PathVariable Long pessoaId) {
		List<Endereco> endereco = enderecoService.listarPelaPessoa(pessoaId);
		return enderecoConverter.toResponseCollection(endereco);
	}

}
