package demo.manager.api.response;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PessoaConsultaResponse {
	
	private String nome;
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dataDeNascimento;
	//private List<EnderecoResponseSemPessoa> endereco;

}
