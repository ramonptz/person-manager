package demo.manager.api.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ControllerAdvice
public class CepControllerAdvice {
	
	@ResponseBody
	@ExceptionHandler(CepNotFound.class)
	public ResponseEntity<MessageExceptionHandler> PessoaNotFound(CepNotFound pessoaNotFound){
		MessageExceptionHandler error = new MessageExceptionHandler(new Date(), HttpStatus.NOT_FOUND.value(), "Cep Não Encontrada");
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}
