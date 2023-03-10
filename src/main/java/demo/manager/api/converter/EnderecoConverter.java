package demo.manager.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import demo.manager.api.request.EnderecoRequest;
import demo.manager.api.response.EnderecoResponse;
import demo.manager.domain.model.Endereco;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EnderecoConverter {

	private ModelMapper modelMapper;

	public EnderecoResponse toResponse(Endereco endereco) {
		
		return modelMapper.map(endereco, EnderecoResponse.class);
	}

	public List<EnderecoResponse> toResponseCollection(List<Endereco> endereco) {
		return endereco.stream().map(this::toResponse).collect(Collectors.toList());
	}

	public Endereco toEntity(EnderecoRequest enderecoRequest) {
		return modelMapper.map(enderecoRequest, Endereco.class);
	}

	public Page<EnderecoResponse> converter(Page<Endereco> endereco) {
		return endereco.map(this::toResponse);
	}

}
