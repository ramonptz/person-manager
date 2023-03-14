package demo.manager.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import demo.manager.api.request.PessoaAtualizaRequest;
import demo.manager.api.request.PessoaRequest;
import demo.manager.api.response.PessoaConsultaResponse;
import demo.manager.api.response.PessoaResponse;
import demo.manager.domain.model.Pessoa;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class PessoaConverter {

	private ModelMapper modelMapper;

	public PessoaResponse toResponse(Pessoa pessoa) {
		return modelMapper.map(pessoa, PessoaResponse.class);
	}

	public List<PessoaResponse> toResponseCollection(List<Pessoa> pessoa) {
		return pessoa.stream().map(this::toResponse).collect(Collectors.toList());
	}
	
	public List<Pessoa> toEntityCollection(List<PessoaRequest> pessoa) {
		return pessoa.stream().map(this::toEntity).collect(Collectors.toList());
	}

	public Pessoa toEntity(PessoaRequest pessoaRequest) {
		return modelMapper.map(pessoaRequest, Pessoa.class);
	}

	public Page<PessoaResponse> toPageResponse(Page<Pessoa> pessoa) {
		return pessoa.map(this::toResponse);
	}

	public Pessoa toEntityAtt(PessoaAtualizaRequest pessoaRequest) {
		return modelMapper.map(pessoaRequest, Pessoa.class);
	}
	
	public PessoaConsultaResponse toConsultaResponse(Pessoa pessoa) {
		return modelMapper.map(pessoa, PessoaConsultaResponse.class);
	}
	
	public Page<PessoaConsultaResponse> toPageConsultaResponse(Page<Pessoa> pessoa) {
		return pessoa.map(this::toConsultaResponse);
	}

}
