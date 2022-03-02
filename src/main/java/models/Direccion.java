package models;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="direccion")
public class Direccion {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="ID_EMPLEADO")
	private Empleado empleado;
	
	@Column(name="DIRECCION")
	private String direccion;
	
	@Column(name="MUNICIPIO")
	private String municipio;
	
	@Column(name="ESTADO")
	private String estado;
	
	@Column(name="CODIGO_POSTAL")
	private String codigoPostal;
	
	@Column(name="LAST_UPDATE")
	private Date lastUpdate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Direccion [id=" + id + ", direccion=" + direccion + ", municipio=" + municipio + ", estado=" + estado
				+ ", codigoPostal=" + codigoPostal + ", lastUpdate=" + lastUpdate + "]";
	}

	public Direccion(Empleado empleado, String direccion, String municipio, String estado, String codigoPostal,
			Date lastUpdate) {
		this.empleado = empleado;
		this.direccion = direccion;
		this.municipio = municipio;
		this.estado = estado;
		this.codigoPostal = codigoPostal;
		this.lastUpdate = lastUpdate;
	}
	
	
	
}
