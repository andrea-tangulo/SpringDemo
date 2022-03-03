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

	public static void main(String[] args) {
		// TODO 
		CrudCitas obj = new CrudCitas();
		long now = System.currentTimeMillis();
		Date sqlDate = new Date(now);
		obj.insertarCitas(1, 5, sqlDate, 150);
	}

	public void insertarCitas(int idCliente, int idEmpleado, Date fechaCita, float montoTotal) {
		//inisicalizar las factorys y sesiones
		inicializarSesiones();
		try {
			miSession.beginTransaction();
			//crear un cliente y obtener el cliente con el id 
			Cliente miCliente = miSession.get(Cliente.class, idCliente);}
			
			//Creacion de la cita
			Cita miCita = miFactory.CreateCita(idCliente, idEmpleado, fechaCita, montoTotal);
			
			miSession.save(miCita);
			
			System.out.println(miCita);
			miSession.getTransaction().commit();
			System.out.println("¡Empleado Creado! Con id: " + miEmpleado.getId());
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
}