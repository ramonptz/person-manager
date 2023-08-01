package demo.manager.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.manager.domain.model.Cep;

@Repository
public interface CepRepository extends JpaRepository<Cep, Long> {

}
