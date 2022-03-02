package models;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="cita")
public class Cita {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="ID_CLIENTE")
	private Cliente cliente;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="ID_EMPLEADO")
	private Empleado empleado;
	
	@Column(name="FECHA_CITA")
	private Date fecha;
	
	@Column(name="MONTO")
	private float monto;
	
	@Column(name="LAST_UPDATE")
	private Date lastUpdate;
	
	public Cita() {
		
	}

	public Cita(Cliente cliente, Empleado empleado, Date fecha, float monto, Date lastUpdate) {
		this.cliente = cliente;
		this.empleado = empleado;
		this.fecha = fecha;
		this.monto = monto;
		this.lastUpdate = lastUpdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Cita [id=" + id + ", cliente=" + cliente + ", empleado=" + empleado + ", fecha=" + fecha + ", monto="
				+ monto + ", lastUpdate=" + lastUpdate + "]";
	}
	
}