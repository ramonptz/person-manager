package demo.manager.api.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PessoaSemEnderecoResponse {
	
	private Long id;
	private String nome;
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dataDeNascimento;
	//private List<EnderecoResponseSemPessoa> endereco;

}
