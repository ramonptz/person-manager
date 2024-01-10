package demo.security.models;

import java.time.LocalDate;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditoriaSimples {
	
	
	
	@LastModifiedBy
	@Column(name="USUARIO_ULT_ALTERACAO", nullable = true, length = 50)
	private String usuarioUltAlteracao;

	@LastModifiedDate
	@Column(name="DATA_ULT_ALTERACAO")
	//private Date dataUltAlteracao = new Date();
	private LocalDate dataUltAlteracao = LocalDate.now();

}
