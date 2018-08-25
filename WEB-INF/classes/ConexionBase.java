import java.sql.*;
import java.util.*;

//javac -cp ,;../lib/mysqlcon.jar; ConexionBase.java
//java -cp ,;../lib/mysqlcon.jar; ConexionBase
public class ConexionBase{
	Connection conexion;
	String dbName, url, user, pass;


	public ConexionBase(){//Campos necesarios para comunicarse con la BD
		dbName="ingenieria_de_software";
		url = "jdbc:mysql://localhost:3306/";
		user="root";
		pass="root";
	}

	public void establecerConexion(){ //Conexion a la BD
		//String url= "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=f:\\jdbc\\verduleros.MDB";
		try { 
			Class.forName("com.mysql.jdbc.Driver");          		
			conexion=DriverManager.getConnection(url+dbName, user, pass);
			//System.out.println("Conexion exitosa");
		}
		catch (Exception ex1){System.out.println("Error al establecer conexion con la base de datos. "+ex1);}
	}

	public void operacionSQL(String query){ //Podemos insertar y eliminar con esta misma funcion
		//Hay que revisar que se le envia como query INSERT, DELETE, UPDATE en Controlador.java
		try{
			Statement statement=conexion.prepareStatement(query);
			statement.executeUpdate(query);
		}
		catch (Exception ex2){System.out.println(ex2);}
	}

	public void cerrarConexion(){
		try{
			conexion.close();
			//System.out.println("Se ha cerrado la conexion");
		}
		catch(Exception ex3){System.out.println(ex3);}
	}

	/*public static void main(String args[]){
		ConexionBase c=new ConexionBase();
		c.establecerConexion();
	}*/
}