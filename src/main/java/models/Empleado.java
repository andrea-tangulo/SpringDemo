package models;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="empleado")
public class Empleado {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="TELEFONO")
	private String telefono;
	
	@Column(name="CORREO")
	private String correo;
	
	@Column(name="PUESTO")
	private String puesto;
	
	//@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	//@JoinColumn(name="id")
	//private Direccion idDireccion;
	
	@Column(name="ACTIVO")
	private boolean activo;
	
	@Column(name="LAST_UPDATE")
	private Date lastUpdate;
	
	@Column(name="PASS")
	private String pass;
	
	@OneToMany(mappedBy="empleado", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<Cita> cita;
	
	@OneToMany(mappedBy="empleado", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<Direccion> direcciones;
	
	public Empleado() {}

	public Empleado(String nombre, String telefono, String correo, String puesto, boolean activo,
			Date lastUpdate, String pass) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
		this.puesto = puesto;
		this.activo = activo;
		this.lastUpdate = lastUpdate;
		this.pass = pass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
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

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<Cita> getCita() {
		return cita;
	}

	public void setCita(List<Cita> cita) {
		this.cita = cita;
	}
	
	public List<Direccion> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(List<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	public void agregarCitas(Cita miCita) {
		if(miCita==null) new ArrayList<>();
		cita.add(miCita);
		miCita.setEmpleado(this);
	}
	
	public void agregarDireccion(Direccion miDireccion) {
		if(miDireccion==null) new ArrayList<>();
		direcciones.add(miDireccion);
		miDireccion.setEmpleado(this);
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", correo=" + correo
				+ ", puesto=" + puesto + ", activo=" + activo + ", lastUpdate="
				+ lastUpdate + "]";
	}
	
}
