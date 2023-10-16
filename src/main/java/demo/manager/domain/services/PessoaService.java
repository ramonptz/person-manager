package demo.manager.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import demo.manager.api.converter.Conversor;
import demo.manager.api.request.PessoaRequest;
import demo.manager.domain.model.Pessoa;
import demo.manager.domain.repository.PessoaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PessoaService {

	private PessoaRepository pessoaRepository;
	private Conversor conversor;

	//Retorna uma lista paginada de pessoas
	public Page<Pessoa> listar(String nome, Pageable paginacao) {
		if (nome == null) {
			return (Page<Pessoa>) pessoaRepository.findAll(paginacao);
		} else {
			return (Page<Pessoa>) pessoaRepository.findByNomeIgnoreCaseContaining(nome, paginacao);
		}
	}

	//Salva a pessoa no banco de dados
	public Pessoa salvar(Pessoa novaPessoa) {
		return pessoaRepository.save(novaPessoa);
	}
	
	//Salva a lista de pessoas no banco de dados
		public List<Pessoa> salvarLista(List<Pessoa> novaPessoa) {
			for(Pessoa pessoa : novaPessoa) {
			pessoaRepository.save(pessoa);
			}
			return novaPessoa;
		}

	//Busca a pessoa pelo id
	public Optional<Pessoa> buscarPorId(Long idPessoa) {

		return pessoaRepository.findById(idPessoa);
	}

	//Edita as informações da pessoa atualizando apenas os campos que são passados
//	public Pessoa editaPessoa(Pessoa pessoaExistente, PessoaRequest pessoaEditada) {
//		Pessoa pessoa = pessoaExistente;
//		if (pessoaEditada.getNome() == null) {
//			pessoa.setNome(pessoaExistente.getNome());
//		} else {
//			pessoa.setNome(pessoaEditada.getNome());
//		}
//
//		if (pessoaEditada.getDataDeNascimento() == null) {
//			pessoa.setDataDeNascimento(pessoaExistente.getDataDeNascimento());
//		} else {
//			pessoa.setDataDeNascimento(pessoaEditada.getDataDeNascimento());
//		}
//		return salvar(pessoa);
//	}
	
	//Edita as informações da pessoa atualizando apenas os campos que são passados
	public Pessoa editaPessoa(Pessoa pessoaExistente, PessoaRequest pessoaEditada) {
		Pessoa pessoa = pessoaExistente;
		// if (pessoaEditada.getNome() != null) {
		// 	pessoa.setNome(pessoaEditada.getNome());
		// }

		// if (pessoaEditada.getDataDeNascimento() != null) {
		// 	pessoa.setDataDeNascimento(pessoaEditada.getDataDeNascimento());
		// }
		
		conversor.atualizarObjeto(pessoaEditada, pessoa);

		return salvar(pessoa);
	}

}
