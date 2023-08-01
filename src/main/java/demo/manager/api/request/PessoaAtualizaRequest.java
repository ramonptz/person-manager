package demo.manager.api.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import demo.manager.domain.model.Endereco;
import lombok.Data;

@Data
public class PessoaAtualizaRequest {
	private String nome;
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dataDeNascimento;
	
	private Endereco endereco;

}
