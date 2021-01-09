package comspringtoolsappproyecto.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Clientes implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message="Debe ingresar el nombre del cliente")
	@Column(length=45, nullable=false)
	private String nombre;
	@NotEmpty(message="Debe ingresar el apellido del cliente")
	@Column(length=45, nullable=false)
	private String apellidos;
	@NotEmpty(message="Debe ingresar el documento del cliente")
	@Column(length=20, nullable=false)
	private String documento;
	@NotEmpty(message="Debe ingresar el telefono del cliente")
	@Column(length=20, nullable=false)
	private String telefono;
	@NotEmpty(message="Debe ingresar la direccion del cliente")
	@Column(length=60, nullable=false)
	private String direccion;
	@OneToMany(mappedBy="cliente", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Ventas> ventas;
	private static final long serialVersionUID = 1L;
	public Clientes() {
		ventas=new ArrayList<Ventas>();
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
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public List<Ventas> getVentas() {
		return ventas;
	}
	public void setVentas(List<Ventas> ventas) {
		this.ventas = ventas;
	}
	public void añadirFactura(Ventas venta) {
		ventas.add(venta);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nombre + " "+ apellidos;
	}
	
	

}
