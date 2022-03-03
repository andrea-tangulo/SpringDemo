package datos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import factory.*;
import models.*;

public class CrudClientes {
	SessionFactory miSessionFactory;
	Session miSession;
	Factory miFactory;

	public static void main(String[] args) {
		// TODO 
		CrudClientes obj = new CrudClientes();
		obj.insertarClientes("Frida", "6695478362", "e-femartinez@neoris.com");
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
	
	public void insertarClientes(String nombre, String telefono, String email) {
		inicializarSesiones();
		try {
			//crear los objetos con las clases de las tablas correspondientes
			Cliente miCliente = miFactory.CreateCliente(nombre, telefono, email);
			/*iniciar transacción*/
			miSession.beginTransaction();
			/*insertar cliente*/
			miSession.save(miCliente);
			System.out.println(miCliente);
			miSession.getTransaction().commit();
			System.out.println("¡Cliente insertado! Con id: " + miCliente.getId());
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			miSession.close();
			miSessionFactory.close();
		}
	}
}