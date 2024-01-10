package demo.manager.domain.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import demo.security.models.AuditoriaSimples;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class Pessoa  extends AuditoriaSimples{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
