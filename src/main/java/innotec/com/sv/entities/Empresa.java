package innotec.com.sv.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "empresas")
public class Empresa implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@NotBlank(message = "no puede estar vacio")
	private String nombre;

	@NotBlank(message = "no puede estar vacio")
	@Size(min = 10, max = 30, message = "el tamaño tiene que estar entre 10 y 20")
	private String nit;

	@NotBlank(message = "no puede estar vacio")
	@Size(min = 6, max = 20, message = "el tamaño tiene que estar entre 6 y 20")
	private String nrc;

	@NotBlank(message = "no puede estar vacio")
	@Size(min = 10, max = 60, message = "el tamaño tiene que estar entre 10 y 60")
	private String representante;

	@NotBlank(message = "no puede estar vacio")
	@Size(min = 10, max = 60, message = "el tamaño tiene que estar entre 10 y 60")
	private String direccion;

	@Size(min = 8, max = 20, message = "el tamaño tiene que estar entre 8 y 20")
	private String telefono;

	@Size(min = 4, max = 20, message = "el tamaño tiene que estar entre 4 y 20")
	@Email
	@NotBlank
	private String correo;

	@Temporal(TemporalType.DATE)
	//@NotBlank
	@Column(name = "create_at")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createAt;

	private String usuario;
	
	@OneToMany( mappedBy = "empresa", cascade = CascadeType.ALL)
	private List<Declaracion> declaracion;
	
	

	public Empresa() {
		declaracion = new ArrayList<Declaracion>();
	}

	public List<Declaracion> getDeclaracion() {
		return declaracion;
	}

	public void setDeclaracion(List<Declaracion> declaracion) {
		this.declaracion = declaracion;
	}

	@PrePersist
	public void prepersist() {
		this.createAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void addDeclaracion(Declaracion declaracion) {
		this.declaracion.add(declaracion);
	}

	private static final long serialVersionUID = 1L;

}
