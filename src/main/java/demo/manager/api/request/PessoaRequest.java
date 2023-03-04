package demo.manager.api.request;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import demo.manager.domain.model.Endereco;
import lombok.Data;

@Data
public class PessoaRequest {
	@NotNull
	private String nome;
	@NotNull
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dataDeNascimento;
	
	private Endereco endereco;

}
