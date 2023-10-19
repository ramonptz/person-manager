package demo.manager.api.response;

import lombok.Data;

@Data
public class EnderecoResponseSemPessoa {
	
	private CepResponse cep;
	//private String logradouro;
	private Long numero;
	private Boolean enderecoPrincipal;
	//private PessoaResponseSemEndereco pessoa;

}
