package factory;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import models.Cita;
import models.Cliente;
import models.Direccion;
import models.Empleado;
//import models.Entity;

public class Factory{
	SessionFactory miFactory;
	Session miSesion;
	Cliente miCliente;
	Empleado miEmpleado;
	Cita miCita;
	Direccion miDireccion;
	long now;
	Date sqlDate;
	
	//Se puede devolver el list de las citas aqui...
	public boolean ExisteLaCita(int _IdCliente, int _IdEmpleado, Date Fecha) {
		//TODO evaluar si el cliente vuelve nulo. 
		boolean existeCita; 
		inicializarSesiones();
		miCliente = miSesion.get(Cliente.class, _IdCliente);
		
		//en realidad no se necesita el empleado, a menos que se quiera hacer con query 
		//miEmpleado = miSesion.get(Empleado.class, _IdEmpleado);
		List<Cita> misCitas = miCliente.getCita();
		System.out.println(misCitas);
		
		if(misCitas.isEmpty()) {
			existeCita=false;
		}else {
			existeCita=true;
			System.out.println("La cita ya existe!!!");
		}
		
		miSesion.close();
		miFactory.close();
		
		return existeCita;
	}
	
	public Empleado ExisteElEmpleado(int _IdEmpleado) {
		inicializarSesiones();
		miEmpleado = miSesion.get(Empleado.class, _IdEmpleado);
		if(miEmpleado==null) {
			System.out.println("El empleado proporcionado no existe!!!"); 
		}
		miSesion.close();
		miFactory.close();
		return miEmpleado;
	}
	
	public Cliente ExisteElCliente(int _IdCliente) { 
		inicializarSesiones();
		miCliente = miSesion.get(Cliente.class, _IdCliente);
		if(miCliente==null) {
			System.out.println("El cliente proporcionado no existe!!!"); 
		}
		miSesion.close();
		miFactory.close();
		return miCliente;
	}

	public Cita CreateCita(int _IdCliente, int _IdEmpleado, Date _Fecha, float _Monto) {
		inicializarSesiones();
		try {
			//empezar transaccion con la session
			miSesion.beginTransaction();
			
			//si el monto no es válido
			if(_Monto < 0) { return null; }
			
			if(ExisteLaCita(_IdCliente, _IdEmpleado, _Fecha)==true) { return null; }
			else {		
				miEmpleado = ExisteElEmpleado(_IdEmpleado);
				miCliente = ExisteElCliente(_IdCliente);
				if(miEmpleado!=null && miCliente!=null) {
					establecerFecha();
					miCita = new Cita(miCliente, miEmpleado,_Fecha, _Monto, sqlDate);
					/*miCita.idCliente = _IdCliente;
					cita.IdEmpleado = _IdEmpleado;
					cita.Fecha = _Fecha;
					cita.Monto = _Monto;*/
				}
				else { return null; }
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			miSesion.close();
			miFactory.close();
		}
		return miCita;
	}
	
	
	public Empleado CreateEmpleado(String Nombre, String Telefono, String Correo, 
			String puesto) {
		
		if(Nombre == "" || Nombre == null) { return null; }
		if(Telefono == "" || Telefono == null) { return null; }
		if(Correo == "" || Correo == null) { return null; }
		if(puesto == "" || puesto == null) { return null; }
		//if(Dirección == null) { return null; }
		//if(LastUpdate == null) { return null; }
		
		establecerFecha();
		miEmpleado = new Empleado(Nombre,Telefono,Correo,puesto,true,sqlDate);
		
		/*miEmpleado.Nombre = Nombre;
		miEmpleado.Telefono = Telefono;
		miEmpleado.Correo = Correo;
		miEmpleado.puesto = puesto;
		miEmpleado.Direccion = Direccion;
		miEmpleado.Activo = Activo;
		miEmpleado.LastUpdate = LastUpdate;*/
		return miEmpleado;
	}
	
	public Cliente CreateCliente(String Nombre, String Telefono, String Correo) {
		
		if(Nombre == "" || Nombre == null) { return null; }
		if(Telefono == "" || Telefono == null) { return null; }
		if(Correo == "" || Correo == null) { return null; }
		//if(LastUpdate == null) { return null; }
		
		establecerFecha();
		miCliente = new Cliente(Nombre, Telefono, Correo, sqlDate);
		
		/*cliente.Nombre = Nombre;
		cliente.Telefono = Telefono;
		cliente.Correo = Correo;
		cliente.LastUpdate = LastUpdate;*/
		
		return miCliente;
	}	
	
	public Direccion CreateDireccion(
			Empleado empleado,
			String Direccion, 
			String Municipio, 
			String Estado, 
			String CodigoPostal) 
	{
		//if(empleado == null) { return null; }
		int idEmpleado = empleado.getId();
		
		if(ExisteElEmpleado(idEmpleado) == null) return null;
		if(Direccion == "" || Direccion == null) { return null; }
		if(Municipio == null) { return null; }
		if(Estado == null) { return null; }
		if(CodigoPostal == null) { return null; }
		
		establecerFecha();
		miDireccion = new Direccion(empleado, Direccion, Municipio, Estado, CodigoPostal, sqlDate);
		
		/*direccion.Direccion = Direccion;
		direccion.Municipio = Municipio;
		direccion.Estado = Estado;
		direccion.CodigoPostal = CodigoPostal;*/
		
		return miDireccion;
	}
	
	private void inicializarSesiones() {
		miFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Cliente.class)
				.addAnnotatedClass(Cita.class)
				.addAnnotatedClass(Empleado.class)
				.addAnnotatedClass(Direccion.class)
				.buildSessionFactory();
		miSesion = miFactory.openSession();
	}
	
	private void establecerFecha() {
		now = System.currentTimeMillis();
		sqlDate = new Date(now);
	}
	
}