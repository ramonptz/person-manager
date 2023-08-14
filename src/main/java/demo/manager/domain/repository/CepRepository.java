package demo.manager.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.manager.api.response.CepResponse;
import demo.manager.domain.model.Cep;
import java.util.List;


@Repository
public interface CepRepository extends JpaRepository<Cep, Long> {

    // List<CepResponse> findByCity(String city, Class<CepResponse> type);

}
