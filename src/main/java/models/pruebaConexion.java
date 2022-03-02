package models;

import java.sql.Connection;
import java.sql.DriverManager;

public class pruebaConexion {
	public static void main(String[] args) {
		
		//String jdbcUrl="jdbc:mysql://localhost:3306/estetica2?useSSL=false";
		//String user="root";
		String user="halcon_admin";
		String pass="4aQhbhN@";
		String jdbcUrl="jdbc:sqlserver://halconesdelcodigo.database.windows.net;"
				+ "database=estetica;" 
				+ "user=" + user + ";" 
				+ "password=" + pass + ";"
				+ "encryption=true;"
				+ "trustServerCertificate=true;"
				+ "loginTimeout=30;";
		
		try {
			System.out.println("Intentando conectar con BBDD " + jdbcUrl);
			Connection miConexion = DriverManager.getConnection(jdbcUrl);
			System.out.println("Conexion exitosa!" + miConexion.getClientInfo());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
