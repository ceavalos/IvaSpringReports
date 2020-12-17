package innotec.com.sv.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="ventas")
public class Venta implements Serializable {


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
	
	@NotBlank(message = "El numero preimpreso no puede quedar vacio")
	private String numeroPreImpreso;
	
	private String nrc;
	
	@NotBlank(message = "El nombre del contribuyente no puede quedar vacio")
	private String nombre;
	
	private long v_int_no_sujetas;
	
	private long v_int_excentas;
	
	private long v_int_gravadas;
	
	private long v_int_debito_fiscal;
	
	private long total;
	
	public long getTotal() {
		this.total = this.v_int_no_sujetas+this.v_int_excentas+this.v_int_gravadas+this.v_int_debito_fiscal+this.iva_retencion;
		return total;
	}

	public void setTotal(long total) {
		this.total = this.v_int_no_sujetas+this.v_int_excentas+this.v_int_gravadas+this.v_int_debito_fiscal+this.iva_retencion;
		this.total = total;
	}

	private long iva_retencion;
	
	
	
	public long totalVentas() {
		
		return this.v_int_no_sujetas+this.v_int_excentas+this.v_int_gravadas+this.v_int_debito_fiscal+this.iva_retencion;
	}

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

	public String getNumeroPreImpreso() {
		return numeroPreImpreso;
	}

	public void setNumeroPreImpreso(String numeroPreImpreso) {
		this.numeroPreImpreso = numeroPreImpreso;
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getV_int_no_sujetas() {
		return v_int_no_sujetas;
	}

	public void setV_int_no_sujetas(long v_int_no_sujetas) {
		this.v_int_no_sujetas = v_int_no_sujetas;
	}

	public long getV_int_excentas() {
		return v_int_excentas;
	}

	public void setV_int_excentas(long v_int_excentas) {
		this.v_int_excentas = v_int_excentas;
	}

	public long getV_int_gravadas() {
		return v_int_gravadas;
	}

	public void setV_int_gravadas(long v_int_gravadas) {
		this.v_int_gravadas = v_int_gravadas;
	}

	public long getV_int_debito_fiscal() {
		return v_int_debito_fiscal;
	}

	public void setV_int_debito_fiscal(long v_int_debito_fiscal) {
		this.v_int_debito_fiscal = v_int_debito_fiscal;
	}

	

	public long getIva_retencion() {
		return iva_retencion;
	}

	public void setIva_retencion(long iva_retencion) {
		this.iva_retencion = iva_retencion;
	}
	
	
}
