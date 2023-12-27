package demo.manager.domain.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Cep {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cep;
	@Column(name = "uf")
	//@JsonProperty("uf")
	private String state;
	@Column(name = "cidade")
//	@JsonProperty("cidade")
	private String city;
	@Column(name = "bairro")
//	@JsonProperty("bairro")
	private String neighborhood;
	@Column(name = "rua")
//	@JsonProperty("rua")
	private String street;

	@OneToMany(mappedBy = "cep", cascade = CascadeType.ALL)
	private List<Endereco> endereco;
}


