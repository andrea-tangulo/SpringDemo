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
	
	SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml")
			  .addAnnotatedClass(Cliente.class)
			  .addAnnotatedClass(Cita.class)
			  .addAnnotatedClass(Empleado.class)
			  .addAnnotatedClass(Direccion.class)
			  .buildSessionFactory();
	Session miSesion = miFactory.openSession();
	
	Cliente miCliente;
	Empleado miEmpleado;
	Cita miCita;
	Direccion miDireccion;
	long now;
	Date sqlDate;
	
	//Se puede devolver el list de las citas aqui...
	public boolean ExisteLaCita(int _IdCliente, int _IdEmpleado, Date Fecha) {
		//TODO evaluar si el cliente vuelve nulo. 
		miCliente = miSesion.get(Cliente.class, _IdCliente);
		
		//en realidad no se necesita el empleado, a menos que se quiera hacer con query 
		//miEmpleado = miSesion.get(Empleado.class, _IdEmpleado);
		List<Cita> misCitas = miCliente.getCita();
		
		if(misCitas!=null) {
			return true;
		}
		return false;
	}
	
	public Empleado ExisteElEmpleado(int _IdEmpleado) {
		miEmpleado = miSesion.get(Empleado.class, _IdEmpleado);
		if(miEmpleado!=null) return miEmpleado;
		else return null;
	}
	
	public Cliente ExisteElCliente(int _IdCliente) { 
		miCliente = miSesion.get(Cliente.class, _IdCliente);
		if(miCliente!=null) return miCliente;
		else return null;
	}

	public Cita CreateCita(int _IdCliente, int _IdEmpleado, Date _Fecha, float _Monto) {
		//empezar transaccion con la session
		miSesion.beginTransaction();
		
		//si el monto no es válido
		if(_Monto < 0) { return null; }
		
		if(ExisteLaCita(_IdCliente, _IdEmpleado, _Fecha)) { return null; }
		else {		
			miEmpleado = ExisteElEmpleado(_IdEmpleado);
			miCliente = ExisteElCliente(_IdCliente);
			if(miEmpleado!=null && miCliente!=null) {
				now = System.currentTimeMillis();
				sqlDate = new Date(now);
				miCita = new Cita(miCliente, miEmpleado,_Fecha, _Monto, sqlDate);
				/*miCita.idCliente = _IdCliente;
				cita.IdEmpleado = _IdEmpleado;
				cita.Fecha = _Fecha;
				cita.Monto = _Monto;*/
			}
			else { return null; }
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
		
		now = System.currentTimeMillis();
		sqlDate = new Date(now);
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
		
		now = System.currentTimeMillis();
		sqlDate = new Date(now);
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
		
		now = System.currentTimeMillis();
		sqlDate = new Date(now);
		miDireccion = new Direccion(empleado, Direccion, Municipio, Estado, CodigoPostal, sqlDate);
		
		/*direccion.Direccion = Direccion;
		direccion.Municipio = Municipio;
		direccion.Estado = Estado;
		direccion.CodigoPostal = CodigoPostal;*/
		
		return miDireccion;
	}	
	
}