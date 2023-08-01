package demo.manager.api.response;

import lombok.Data;

@Data
public class EnderecoResponse {
	
	private Boolean enderecoPrincipal;
	private CepResponse cep;
	private Long numero;
	private String cidade;
	private PessoaResponseSemEndereco pessoa;

}
