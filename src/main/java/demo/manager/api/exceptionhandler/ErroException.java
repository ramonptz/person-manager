package demo.manager.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ErroException {
	
	private Integer status;
	private LocalDateTime dataHora;
	private String titulo;
	private List<Campo> campo;
	
	
	@AllArgsConstructor
	@Data
	public static class Campo{
		
		private String nome;
		private String mensagem;
	}

}
