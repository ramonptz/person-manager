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
	private Long numero;
	
	@ManyToOne
	@JoinColumn(name = "cep")
	private Cep cep;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", enderecoPrincipal=" + enderecoPrincipal + ", cep=" + cep + ", numero=" + numero + "]";
	}
	
}
