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
		
		obj.insertarEmpleados("Pedro", "8976452901", "pedroLopez@gmail.com", "Gerente", "jG83l?1m", 
				"calle av. del mar 1200", "mazatlan", "sinaloa", "82120");
	}

	public void insertarEmpleados(String nombre, String telefono, String correo, String puesto, 
			String pass, String direccion, String municipio, String estado, String codigoPostal) {
		inicializarSesiones();
		try {
			miSession.beginTransaction();
			Empleado miEmpleado = miFactory.CreateEmpleado(nombre, telefono, correo, puesto, pass);
			if (miEmpleado == null) System.out.println("Error creando al empleado!!");
			else {
				miSession.save(miEmpleado); //insert
				Direccion miDireccion = miFactory.CreateDireccion(miEmpleado, direccion, municipio, 
						estado, codigoPostal);
				if (miDireccion == null) System.out.println("Error creando la direccion..."); 
				else{
					miEmpleado.agregarDireccion(miDireccion);
					miSession.save(miDireccion);
					miSession.getTransaction().commit();
					System.out.println("¡Empleado Creado! " + miEmpleado);
					System.out.println("Direccion relacionada: " + miDireccion);
				}
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
}