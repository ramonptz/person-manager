package demo.manager.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.manager.domain.model.Cep;


public interface CepRepository extends JpaRepository<Cep, Long> {

    // List<CepResponse> findByCity(String city, Class<CepResponse> type);

}
