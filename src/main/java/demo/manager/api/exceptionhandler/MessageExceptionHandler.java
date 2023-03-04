package demo.manager.api.exceptionhandler;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageExceptionHandler {
	
	private Date timeStamp;
	private Integer status;
	private String Message;

}
