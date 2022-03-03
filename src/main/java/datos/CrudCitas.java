package datos;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import factory.*;
import models.*;

public class CrudCitas {
	SessionFactory miSessionFactory;
	Session miSession;
	Factory miFactory;
	long now;
	Date sqlDate;

	public static void main(String[] args) {
		// TODO 
		CrudCitas obj = new CrudCitas();
		Date fecha = obj.getDateNow();
		obj.insertarCitas(1, 5, fecha, 150);
	}

	public void insertarCitas(int idCliente, int idEmpleado, Date fechaCita, float montoTotal) {
		//inisicalizar las factorys y sesiones
		inicializarSesiones();
		try {
			miSession.beginTransaction();
			//crear un cliente y empleado y obtener el cliente con el id 
			Cliente miCliente = miSession.get(Cliente.class, idCliente);
			Empleado miEmpleado = miSession.get(Empleado.class, idEmpleado);
			
			/*Factory de la cita*/
			Cita miCita = miFactory.CreateCita(idCliente, idEmpleado, fechaCita, montoTotal);
			//System.out.println(miCita);
			if(miCita == null) {
				System.out.println("Ha habido un error creando la cita... ");
			} else {
				/*Agregando a clases relacionadas*/
				miCliente.agregarCitas(miCita);
				miEmpleado.agregarCitas(miCita);
				
				/*guardando la cita en la bbdd, se guarda lo demás tmb*/
				miSession.save(miCita);
				System.out.println(miCita);
				
				miSession.getTransaction().commit();
				System.out.println("¡Cita Creada!" + miCita);
			}			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			miSession.close();
			miSessionFactory.close();
		}
	}
	
	private void inicializarSesiones() {
		miSessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Cliente.class)
				.addAnnotatedClass(Cita.class)
				.addAnnotatedClass(Empleado.class)
				.addAnnotatedClass(Direccion.class)
				.buildSessionFactory();
		miSession = miSessionFactory.openSession();
		miFactory = new Factory();
	}
	
	private Date getDateNow() {
		now = System.currentTimeMillis();
		return (sqlDate = new Date(now));
	}
}