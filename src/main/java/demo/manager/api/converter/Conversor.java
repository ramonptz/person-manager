package demo.manager.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class Conversor {

	private ModelMapper modelMapper;


	public <T, R> R converter(T objetoASerConvertido, Class<R> classeAlvo) {
        return modelMapper.map(objetoASerConvertido, classeAlvo);
    }

	public <T, R> Page<R> converterParaPage(Page<T> pageASerConvertida, Class<R> classeAlvo, Pageable pageable) {
        List<R> lista = pageASerConvertida.getContent()
                .stream()
                .map(item -> modelMapper.map(item, classeAlvo))
                .collect(Collectors.toList());

        return new PageImpl<>(lista, pageable, pageASerConvertida.getTotalElements());
	}

	public <T, R> List<R> converterParaList(List<T> pageASerConvertida, Class<R> classeAlvo) {
        List<R> lista = pageASerConvertida
                .stream()
                .map(item -> modelMapper.map(item, classeAlvo))
                .collect(Collectors.toList());
		return lista;
	}

	public void atualizaObjeto(Object objetoQueVaiAtualizar, Object objetoASerAtualizado){
		modelMapper.getConfiguration().setPropertyCondition(chave -> chave.getSource() != null);
		modelMapper.map(objetoQueVaiAtualizar, objetoASerAtualizado);
	}

	public <T> T atualizarObjeto(Object origem, T destino) {
        modelMapper.getConfiguration().setPropertyCondition(chave -> chave.getSource() != null);
        modelMapper.map(origem, destino);
        return destino;
    }

	
	// public EnderecoResponse toResponse(Endereco endereco) {
		
	// 	return modelMapper.map(endereco, EnderecoResponse.class);
	// }

	// public List<EnderecoResponse> toResponseCollection(List<Endereco> endereco) {
	// 	return endereco.stream().map(this::toResponse).collect(Collectors.toList());
	// }

	// public Endereco toEntity(EnderecoRequest enderecoRequest) {
	// 	return modelMapper.map(enderecoRequest, Endereco.class);
	// }

	// public Page<EnderecoResponse> converter(Page<Endereco> endereco) {
	// 	return endereco.map(this::toResponse);
	// }

}
