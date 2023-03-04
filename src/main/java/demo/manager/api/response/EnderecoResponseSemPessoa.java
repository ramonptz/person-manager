package demo.manager.api.response;

import demo.manager.domain.model.Pessoa;
import lombok.Data;

@Data
public class EnderecoResponseSemPessoa {
	
	private Boolean enderecoPrincipal;
	private String logradouro;
	private String cep;
	private Long numero;
	private String cidade;
	//private PessoaResponseSemEndereco pessoa;

}
