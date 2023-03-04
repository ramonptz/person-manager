package demo.manager.domain.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import demo.manager.domain.model.Pessoa;
import demo.manager.domain.repository.PessoaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PessoaService {

	private PessoaRepository pessoaRepository;
	
	public Page<Pessoa> listar(String nome, Pageable paginacao) {
		if (nome == null) {
			return (Page<Pessoa>)pessoaRepository.findAll(paginacao);
		} else {
			return (Page<Pessoa>)pessoaRepository.findByNomeIgnoreCaseContaining(nome, paginacao);
		}
	}
	
	public Pessoa salvar(Pessoa novaPessoa) {
		return pessoaRepository.save(novaPessoa);
	}
	
	public ResponseEntity<Pessoa> Atualizar(Long id, Pessoa pessoaAtualizada) {
		if(!pessoaRepository.existsById(id)){
			return ResponseEntity.notFound().build();
		}
		
		pessoaRepository.save(pessoaAtualizada);
		
		return ResponseEntity.ok(pessoaAtualizada);

		
	}

	public Optional<Pessoa> buscarPorId(Long idPessoa) {
		
		return pessoaRepository.findById(idPessoa);
	}
	
//	public void atualizaPessoa(Long pessoaId, PessoaRequest pessoaAtualizada) {
//		
//		
//	}

//	public Pessoa buscarPorId(Long pessoaId) {
//		
//		return pessoaRepository.findById(pessoaId).orElseThrow() -> new EntidadeNaoEncontradaException("Pessoa não entrada");
//	}


}
