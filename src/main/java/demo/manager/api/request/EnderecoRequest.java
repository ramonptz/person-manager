package demo.manager.api.request;

import jakarta.validation.constraints.NotNull;

import demo.manager.domain.model.Cep;
import lombok.Data;

@Data
public class EnderecoRequest {

	private Boolean enderecoPrincipal = false;
	@NotNull
	private Long numero;
	private Cep cep;
	
	
	
	
}
