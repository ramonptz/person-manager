package demo.manager.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean enderecoPrincipal;
	private String logradouro;
	private String cep;
	private Long numero;
	private String cidade;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", enderecoPrincipal=" + enderecoPrincipal + ", logradouro=" + logradouro
				+ ", cep=" + cep + ", numero=" + numero + ", cidade=" + cidade + "]";
	}
	
}
