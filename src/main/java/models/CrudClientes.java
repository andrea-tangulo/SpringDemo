package models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import factory.Factory;
public class CrudClientes {
	SessionFactory miSessionFactory;
	Session miSession;
	Factory miFactory;

	public static void main(String[] args) {
		// TODO 
		CrudClientes obj = new CrudClientes();
		obj.insertarClientes("Frida", "6695478362", "e-femartinez@neoris.com");
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
	
	public void insertarClientes(String nombre, String telefono, String email) {
		inicializarSesiones();
		try {
			//crear los objetos con las clases de las tablas correspondientes
			//Date fecha=new Date(120,6,4);
			//Cliente miCliente = new Cliente("Alan", "6691345271", "al4nsopeña@gmail.com", fecha);
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