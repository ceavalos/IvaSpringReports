package innotec.com.sv.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="compras")
public class Compra implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@NotNull	
	Declaracion declaracion;
	
	@Temporal(TemporalType.DATE)
	@NotNull(message = "La fecha de emision del documento no puede estar vacia")
	@Column(name = "fecha_emision")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaEmision;
	 
	@NotBlank(message = "debe indicar el numero de documento")
	@Column(unique = true)
	private String numero_documento;
		
	private String nrc;
	
	private String nit_dui;
	
	@NotBlank(message = "El nombre del proveedor no puede estar vacio")
	private String nombre_proveedor;
	

	private long comp_exc_interna;

	private long comp_exc_importa;

    private long comp_grv_interna;

	private long comp_grv_importa;

	private long fovial;

	private long iva_crefiscal;

	private long compras_excluidas;

	private long total_compras;

	private long percepcion;
	
	public long totalCompras() {
		return comp_exc_interna + comp_exc_importa + comp_grv_interna + 
			   comp_grv_importa + fovial           + iva_crefiscal +
			   compras_excluidas;
	}
	
	/*@PrePersist
	public void prePersis() {
		this.comp_exc_interna =0;
        this.comp_exc_importa =0;
        this.comp_grv_interna =0;
        this.comp_grv_importa =0;
        this.fovial           =0;
        this.iva_crefiscal    =0;
        this.compras_excluidas=0;
		
	}*/
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Declaracion getDeclaracion() {
		return declaracion;
	}

	public void setDeclaracion(Declaracion declaracion) {
		this.declaracion = declaracion;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getNumero_documento() {
		return numero_documento;
	}

	public void setNumero_documento(String numero_documento) {
		this.numero_documento = numero_documento;
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public String getNit_dui() {
		return nit_dui;
	}

	public void setNit_dui(String nit_dui) {
		this.nit_dui = nit_dui;
	}

	public String getNombre_proveedor() {
		return nombre_proveedor;
	}

	public void setNombre_proveedor(String nombre_proveedor) {
		this.nombre_proveedor = nombre_proveedor;
	}

	public long getComp_exc_interna() {
		return comp_exc_interna;
	}

	public void setComp_exc_interna(long comp_exc_interna) {
		this.comp_exc_interna = comp_exc_interna;
	}

	public long getComp_exc_importa() {
		return comp_exc_importa;
	}

	public void setComp_exc_importa(long comp_exc_importa) {
		this.comp_exc_importa = comp_exc_importa;
	}

	public long getComp_grv_interna() {
		return comp_grv_interna;
	}

	public void setComp_grv_interna(long comp_grv_interna) {
		this.comp_grv_interna = comp_grv_interna;
	}

	public long getComp_grv_importa() {
		return comp_grv_importa;
	}

	public void setComp_grv_importa(long comp_grv_importa) {
		this.comp_grv_importa = comp_grv_importa;
	}

	public long getFovial() {
		return fovial;
	}

	public void setFovial(long fovial) {
		this.fovial = fovial;
	}

	public long getIva_crefiscal() {
		return iva_crefiscal;
	}

	public void setIva_crefiscal(long iva_crefiscal) {
		this.iva_crefiscal = iva_crefiscal;
	}

	public long getCompras_excluidas() {
		return compras_excluidas;
	}

	public void setCompras_excluidas(long compras_excluidas) {
		this.compras_excluidas = compras_excluidas;
	}

	public long getPercepcion() {
		return percepcion;
	}

	public void setPercepcion(long percepcion) {
		this.percepcion = percepcion;
	}

	public long getTotal_compras() {
		this.total_compras = 
			this.comp_exc_interna +
	        this.comp_exc_importa +
	        this.comp_grv_interna +
	        this.comp_grv_importa +
	        this.fovial           +
	        this.iva_crefiscal +
	        this.compras_excluidas;
		//
		return total_compras;
	}

	public void setTotal_compras(long total_compras) {
		this.total_compras = total_compras;
	}

	
	
}
