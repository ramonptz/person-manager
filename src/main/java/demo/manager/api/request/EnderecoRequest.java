package demo.manager.api.request;

import javax.validation.constraints.NotNull;

import demo.manager.domain.model.Pessoa;
import lombok.Data;

@Data
public class EnderecoRequest {

	private Boolean enderecoPrincipal = false;
	@NotNull
	private String logradouro;
	@NotNull
	private String cep;
	@NotNull
	private Long numero;
	@NotNull
	private String cidade;
	private Pessoa pessoa;
	
}
