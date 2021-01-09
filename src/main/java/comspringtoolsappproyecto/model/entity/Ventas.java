package comspringtoolsappproyecto.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Ventas implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@ManyToOne(fetch=FetchType.LAZY)
	private Clientes cliente;
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="venta_id")
	private List<Detalle_Venta> detalle;
	
	public Ventas() {
		detalle=new ArrayList<Detalle_Venta>();
	}
	@PrePersist
	public void prePersist() {
		fecha=new Date();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Clientes getCliente() {
		return cliente;
	}
	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}
	public List<Detalle_Venta> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<Detalle_Venta> detalle) {
		this.detalle = detalle;
	}

	public void AñadirDetalle(Detalle_Venta ventadetalle) {
		detalle.add(ventadetalle);
	}
	public float getTotal() {
		float total=0;
		for(Detalle_Venta deta:detalle) {
			total+=deta.CalcularTotal();
		}
		return total;
		
		
	}
	private static final long serialVersionUID = 1L;

}
