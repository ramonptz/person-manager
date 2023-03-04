package demo.manager.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.manager.domain.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	//Faz uma busca no banco de dados e retorna nomes que contenham a variável no nome independente se é maiúscula ou minúscula
	Page<Pessoa> findByNomeIgnoreCaseContaining(String nome, Pageable paginacao);

}
