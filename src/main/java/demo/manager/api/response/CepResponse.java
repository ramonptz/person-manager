package demo.manager.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CepResponse {
	
	private  Long cep;
	@JsonProperty("uf")
	private String state;
	@JsonProperty("cidade")
	private String city;
	@JsonProperty("bairro")
	private String neighborhood;
	@JsonProperty("rua")
	private String street;
	
	

}

//{"cep":"28925732",
//"state":"RJ",
//"city":"Cabo Frio",
//"neighborhood":"Aquarius (Tamoios)",
//"street":"Rua Madre Teresa de Calcutá",
//"service":"correios-alt"}