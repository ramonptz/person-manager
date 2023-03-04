package demo.manager.api.exceptionhandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class PessoaControllerAdvice{
	
	@ResponseBody
	@ExceptionHandler(PessoaNotFound.class)
	public ResponseEntity<MessageExceptionHandler> PessoaNotFound(PessoaNotFound pessoaNotFound){
		MessageExceptionHandler error = new MessageExceptionHandler(new Date(), HttpStatus.NOT_FOUND.value(), "Pessoa Não Encontrada");
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}
