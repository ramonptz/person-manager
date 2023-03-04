package demo.manager.api.response;

import lombok.Data;

@Data
public class EnderecoResponse {
	
	private Boolean enderecoPrincipal;
	private String logradouro;
	private String cep;
	private Long numero;
	private String cidade;
	private PessoaResponseSemEndereco pessoa;

}
