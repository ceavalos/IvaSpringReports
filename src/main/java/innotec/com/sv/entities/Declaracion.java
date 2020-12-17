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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="declaraciones")
public class Declaracion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size( max = 60, message = "el tamaño tiene que ser menor a 60 caracteres")
	private String descripcion;
	
	@Temporal(TemporalType.DATE)
	//@NotBlank
	@Column(name = "create_at")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createAt;	
	
	@PrePersist
	public void prePersis() {
		this.createAt = new Date();
	}
	
    @ManyToOne
    @NotNull
	private Empresa empresa;

    @ManyToOne
    @NotNull
    private Periodo periodo;
    
    
    @OneToMany( mappedBy = "declaracion", cascade = CascadeType.ALL)     
    private List<Compra> compra;
    
    @OneToMany( mappedBy = "declaracion", cascade = CascadeType.ALL)     
    private List<Venta> venta;
    
	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	} 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public List<Compra> getCompra() {
		return compra;
	}

	public void setCompra(List<Compra> compra) {
		this.compra = compra;
	}
	
	
	
	public List<Venta> getVenta() {
		return venta;
	}

	public void setVenta(List<Venta> venta) {
		this.venta = venta;
	}

	public long getTotalCompras() {
		
		long total = 0;
		
		for (int i=0; i< this.compra.size(); i++ ) {
			total += this.compra.get(i).getTotal_compras();
		}
		return total;
	}
	

	public long getTotalVentas() {
		
		long total = 0;
		
		for (int i=0; i< this.venta.size(); i++ ) {
			total += this.venta.get(i).totalVentas();
		}
		return total;
	}
	
}
