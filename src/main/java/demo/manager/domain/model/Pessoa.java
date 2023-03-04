package demo.manager.domain.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String nome;
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dataDeNascimento;
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)//(targetEntity = Endereco.class)
	//@JoinColumn(name="id")
	private List<Endereco> endereco;
	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", dataDeNascimento=" + dataDeNascimento + "]";
	}
	
}
