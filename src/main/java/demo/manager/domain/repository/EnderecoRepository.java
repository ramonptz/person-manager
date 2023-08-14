package demo.manager.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.manager.domain.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    List<Endereco> findByPessoaNomeIgnoreCaseContainingAndCepStreetIgnoreCaseContaining(String nome, String rua);

    // @Query(value = "SELECT e FROM Endereco e JOIN e.pessoa p JOIN e.cep c WHERE (:nome IS NULL OR p.nome LIKE %:nome%) AND (:rua IS NULL OR c.street LIKE %:rua%)" )
    // Page<Endereco> testando123(String nome, String rua, Pageable paginacao);

}
