package innotec.com.sv.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "periodos")
public class Periodo implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "la descripcion no puede ser vacia.")
	@Size(min = 5, max = 20, message = "el tamaño tiene que estar entre 6 y 20")
	private String descripcion;
	
	@NotNull
	@Column(name = "fechainicio")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaInicio;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "fechafinal")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaFinal;
	
/*	@PrePersist
	public void prePersis() {
		this.fechaFinal = new Date();
		this.fechaInicio = new Date();
	}	*/
		
	@OneToMany( mappedBy = "periodo", cascade = CascadeType.ALL)
	private List<Declaracion> declaracion;
		
	
	public List<Declaracion> getDeclaracion() {
		return declaracion;
	}

	public void setDeclaracion(List<Declaracion> declaracion) {
		this.declaracion = declaracion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
