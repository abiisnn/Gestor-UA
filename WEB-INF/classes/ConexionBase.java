import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

//javac -cp ,;../lib/mysqlcon.jar; ConexionBase.java
//java -cp ,;../lib/mysqlcon.jar; ConexionBase
public class ConexionBase{
	Connection conexion;
	boolean estado;
	String dbName, url, user, pass;


	public ConexionBase(){//Campos necesarios para comunicarse con la BD
		estado=false;
		dbName="ingenieria_de_software";
		url = "jdbc:mysql://localhost:3306/";
		user="root";
		pass="root";
	}

	public boolean establecerConexion(){ //Conexion a la BD
		//String url= "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=f:\\jdbc\\verduleros.MDB";
		try { 
			Class.forName("com.mysql.jdbc.Driver");          		
			conexion=DriverManager.getConnection(url+dbName, user, pass);
			estado=true;
		}
		catch (Exception ex1){System.out.println(ex1);}
		return estado;
	}

	public void operacionSQL(String query){ //Podemos insertar y eliminar con esta misma funcion
		//Hay que revisar que se le envia como query INSERT, DELETE, UPDATE
		try{
			Statement statement=conexion.prepareStatement(query);
			statement.executeUpdate(query);
		}
		catch (Exception ex2){System.out.println(ex2);}
	}

	public void cerrarConexion(){
		try{
			conexion.close();
			System.out.println("Se ha cerrado la conexion");
		}
		catch(Exception ex3){System.out.println(ex3);}
	}

	/*public static void main(String args[]){
		ConexionBase c=new ConexionBase();
		if(c.establecerConexion()){
			System.out.println("Conexion exitosa");
			c.cerrarConexion();
		}
			
		else{
			System.out.println("Error");
		}
	}*/
}