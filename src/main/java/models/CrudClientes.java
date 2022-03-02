package models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import factory.Factory;
public class CrudClientes {

	public static void main(String[] args) {
		// TODO 
		SessionFactory miSessionFactory = new Configuration().configure("hibernate.cfg.xml")
				  .addAnnotatedClass(Cliente.class)
				  .addAnnotatedClass(Cita.class)
				  .addAnnotatedClass(Empleado.class)
				  .addAnnotatedClass(Direccion.class)
				  .buildSessionFactory();
		Session miSesion = miSessionFactory.openSession();
		CrudClientes miCliente = new CrudClientes();
		miCliente.insertarClientes(miSessionFactory,miSesion);
	}
	
	public void insertarClientes(SessionFactory miSessionFactory, Session miSesion) {
		try {
			//crear los objetos con las clases de las tablas correspondientes
			//Date fecha=new Date(120,6,4);
			//Cliente miCliente = new Cliente("Alan", "6691345271", "al4nsopeña@gmail.com", fecha);
			Factory miFactory = new Factory();
			Cliente miCliente = miFactory.CreateCliente("Nelson", "6691345922", "e-nabeas@neoris.com");
			/*iniciar transacción*/
			miSesion.beginTransaction();
			/*insertar cliente*/
			miSesion.save(miCliente);
			System.out.println(miCliente);
			miSesion.getTransaction().commit();
			System.out.println("Registro del cliente insertado con id: " + miCliente.getId());
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			miSesion.close();
			miSessionFactory.close();
		}
	}
	
	public void insertarEmpleados() {
		
	}
}