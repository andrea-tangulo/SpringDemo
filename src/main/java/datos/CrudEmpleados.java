package datos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import factory.*;
import models.*;

public class CrudEmpleados {
	SessionFactory miSessionFactory;
	Session miSession;
	Factory miFactory;

	public static void main(String[] args) {
		// TODO 
		CrudEmpleados obj = new CrudEmpleados();
		obj.insertarEmpleados("José", "5574936741", "josé.plopez@neoris.com", "Barbero");
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

	public void insertarEmpleados(String nombre, String telefono, String correo, String puesto) {
		inicializarSesiones();
		try {
			Empleado miEmpleado = miFactory.CreateEmpleado(nombre, telefono, correo, puesto);
			miSession = miSessionFactory.openSession();
			miSession.beginTransaction();
			miSession.save(miEmpleado);
			System.out.println(miEmpleado);
			miSession.getTransaction().commit();
			System.out.println("¡Empleado Creado! Con id: " + miEmpleado.getId());
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			miSession.close();
			miSessionFactory.close();
		}
	}
}