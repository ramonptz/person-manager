package demo.manager.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import demo.manager.api.exceptionhandler.PessoaNotFound;
import demo.manager.api.request.EnderecoRequest;
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
	private CepService cepService;

	// Cria um novo endereço, caso seja o primeiro ele torna o endereço em principal
	// ou caso seja criado outro
	// endereço como principal o outro automaticamente deixa de ser principal
	public Endereco novoEndereco(Endereco novoEndereco, Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		pessoa.orElseThrow(() -> new PessoaNotFound());
		atualizaEnderecoPrincipal(novoEndereco, id);
		return enderecoRepository.save(novoEndereco);
	}

	// faz uma busca do endereço pelo id
	public Optional<Endereco> buscarPorId(Long enderecoId) {

		return enderecoRepository.findById(enderecoId);
	}

	// Salva e atualiza o endereço principal caso tenha necessidade
	public Endereco salvarEAtualizaEnderecoPrincipal(Endereco enderecoNovo, Long id) {
		atualizaEnderecoPrincipal(enderecoNovo, id);
		return enderecoRepository.save(enderecoNovo);

	}

	// atualiza o endereço para informar que um endereço existente passou a ser o
	// principal
	public void atualizaEnderecoPrincipal(Endereco endereco, Long id) {
		Pessoa pessoa = pessoaRepository.getReferenceById(id);
		if (pessoa.getEndereco().isEmpty()) {
			endereco.setEnderecoPrincipal(true);
		}
		if (endereco.getEnderecoPrincipal() == true)
			for (Endereco e : pessoa.getEndereco()) {
				e.setEnderecoPrincipal(false);
				endereco.setEnderecoPrincipal(true);
			}
		endereco.setPessoa(pessoa);
	}

	// Lista os endereços de uma pessoa
	public List<Endereco> listarPelaPessoa(Long pessoaId) {

		Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaId);
		pessoa.orElseThrow(() -> new PessoaNotFound());

		return pessoa.get().getEndereco();
	}

	// Atualiza os dados de um endereço
//	public Endereco atualizaEndereco(Endereco enderecoExistente, EnderecoRequest enderecoAtualizado) {
//		Endereco endereco = enderecoExistente;
//		if (enderecoAtualizado.getCep() == null) {
//			endereco.setCep(enderecoExistente.getCep());
//		} else {
//			endereco.setCep(enderecoAtualizado.getCep());
//		}
//
//		if (enderecoAtualizado.getCidade() == null) {
//			endereco.setCidade(enderecoExistente.getCidade());
//		} else {
//			endereco.setCidade(enderecoAtualizado.getCidade());
//		}
//		if (enderecoAtualizado.getLogradouro() == null) {
//			endereco.setLogradouro(enderecoExistente.getLogradouro());
//		} else {
//			endereco.setLogradouro(enderecoAtualizado.getLogradouro());
//		}
//		if (enderecoAtualizado.getNumero() == null) {
//			endereco.setNumero(enderecoExistente.getNumero());
//		} else {
//			endereco.setNumero(enderecoAtualizado.getNumero());
//		}
//		if (enderecoAtualizado.getEnderecoPrincipal() == null) {
//			endereco.setEnderecoPrincipal(enderecoExistente.getEnderecoPrincipal());
//		} else {
//			endereco.setEnderecoPrincipal(enderecoAtualizado.getEnderecoPrincipal());
//		}
//		return endereco;
//	}

	// Atualiza os dados de um endereço
	public Endereco atualizaEndereco(Endereco enderecoExistente, EnderecoRequest enderecoAtualizado) {
		Endereco endereco = enderecoExistente;
//		if (enderecoAtualizado.getCep() != null) {
//			endereco.setCep(enderecoAtualizado.getCep());
//		}
//
//		if (enderecoAtualizado.getCidade() != null) {
//			endereco.setCidade(enderecoAtualizado.getCidade());
//		}
//		if (enderecoAtualizado.getLogradouro() != null) {
//			endereco.setLogradouro(enderecoAtualizado.getLogradouro());
//		}
		if (enderecoAtualizado.getNumero() != null) {
			endereco.setNumero(enderecoAtualizado.getNumero());
		}
		if (enderecoAtualizado.getEnderecoPrincipal() != null) {
			endereco.setEnderecoPrincipal(enderecoAtualizado.getEnderecoPrincipal());
		}
		endereco.setCep(cepService.verificaCepESalva(enderecoAtualizado.getCep().getCep()));
		return endereco;
	}

}
