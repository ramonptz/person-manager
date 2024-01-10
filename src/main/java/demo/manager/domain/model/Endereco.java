package demo.manager.domain.model;

import demo.security.models.AuditoriaSimples;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
// @EqualsAndHashCode(callSuper=false)
public class Endereco/*  extends AuditoriaSimples */{
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
