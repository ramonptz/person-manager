package demo.manager.api.exceptionhandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class EnderecoControllerAdvice {

	@ResponseBody
	@ExceptionHandler(EnderecoNotFound.class)
	public ResponseEntity<MessageExceptionHandler> EnderecoNotFound(EnderecoNotFound enderecoNotFound){
		MessageExceptionHandler error = new MessageExceptionHandler(new Date(), HttpStatus.NOT_FOUND.value(), "Endereço Não Encontrado");
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
}
