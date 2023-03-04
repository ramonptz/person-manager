package demo.manager.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import demo.manager.api.exceptionhandler.PessoaNotFound;
import demo.manager.domain.model.Endereco;
import demo.manager.domain.model.Pessoa;
import demo.manager.domain.repository.EnderecoRepository;
import demo.manager.domain.repository.PessoaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EnderecoService {
	
	private EnderecoRepository enderecoRepository;
	private PessoaRepository pessoaRepository;
	
	public Endereco novoEndereco(Endereco novoEndereco, Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		pessoa.orElseThrow(()-> new PessoaNotFound());
		atualizaEnderecoPrincipal(novoEndereco, id);
		return enderecoRepository.save(novoEndereco);
	}

	public Optional<Endereco> buscarPorId(Long enderecoId) {
		
		return enderecoRepository.findById(enderecoId);
	}

	public Endereco salvarEAtualizaEnderecoPrincipal(Endereco enderecoNovo,Long id) {
		atualizaEnderecoPrincipal(enderecoNovo, id);
		return enderecoRepository.save(enderecoNovo);
		
	}
	
	public void atualizaEnderecoPrincipal(Endereco endereco, Long id) {
		Pessoa pessoa = pessoaRepository.getReferenceById(id);
		if(pessoa.getEndereco().isEmpty()) {
			endereco.setEnderecoPrincipal(true);
		}
		if(endereco.getEnderecoPrincipal() == true)
		for(Endereco e: pessoa.getEndereco()) {
			e.setEnderecoPrincipal(false); 
			endereco.setEnderecoPrincipal(true);
		}
		endereco.setPessoa(pessoa);
	}

	public List<Endereco> listarPelaPessoa(Long pessoaId) {
		
		Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaId);
		pessoa.orElseThrow(()-> new PessoaNotFound());
		
		return pessoa.get().getEndereco();
	}
	
}

