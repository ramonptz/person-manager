package demo.manager.domain.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import demo.manager.api.exceptionhandler.CepNotFound;
import demo.manager.domain.model.Cep;
import demo.manager.domain.repository.CepRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CepService {
	
	private CepRepository cepRepository;
	// private RestTemplate restTemplate;
	
	public Cep criaOuAtualizaCep( Cep novoCep) {
		
		return cepRepository.save(novoCep);
	}

	public Cep verificaCepESalva (Long novoCep){

		ResponseEntity<Cep> enderecoComCep = validaCep(novoCep);

		criaOuAtualizaCep(enderecoComCep.getBody());
		return enderecoComCep.getBody();
	}

	public ResponseEntity<Cep> validaCep(Long numeroCep){
		if(numeroCep.toString().length() != 8){
			throw new CepNotFound();
		}
		RestTemplate restTemplate = new RestTemplate();

		try{
			ResponseEntity<Cep> enderecoComCep = restTemplate.getForEntity("https://brasilapi.com.br/api/cep/v1/" + numeroCep, Cep.class);
			return enderecoComCep;
		} catch (HttpClientErrorException.NotFound e){
			throw new CepNotFound();
		}

	}
	
}
