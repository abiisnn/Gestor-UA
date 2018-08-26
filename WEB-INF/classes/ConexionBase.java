import java.sql.*;
import java.util.*;

//javac -cp ,;../lib/mysqlcon.jar; ConexionBase.java

public class ConexionBase
{
	Connection conexion;
	String dbName, url, user, pass;


	public ConexionBase()//Campos necesarios para comunicarse con la BD
	{
		dbName="ingenieria_de_software";
		url = "jdbc:mysql://localhost:3306/";//Si existen problemas, cambiar el no. de puerto entre 3307 y 3306
		user="root";
		pass="root";
	}

	public void establecerConexion()//Conexion a la BD
	{ 
		try 
		{ 
			Class.forName("com.mysql.jdbc.Driver");          		
			conexion=DriverManager.getConnection(url+dbName, user, pass);
			//System.out.println("Conexion exitosa");
		}
		catch (Exception ex1){
			System.out.println("Error al establecer conexion con la base de datos. "+ex1);
		}
	}

	public void insertar(String query)//Podemos insertar en la BD con esta funcion
	{ 
		try
		{
			Statement statement=conexion.prepareStatement(query);
			statement.executeUpdate(query);
		}
		catch (Exception ex2){
			System.out.println("Error al insertar bibliografias en la base de datos. "+ex2);
		}
	}

	public int consultarClave(String query)//Obtener la ultima clave
	{
		int n=0;
		try
		{
			//Instrucciones para consultar los registros existentes
			Statement consulta=conexion.createStatement();
			ResultSet result=consulta.executeQuery(query);
		
			if(result.next()) //Si hay resultados obtengo el valor.
			{  
        		n= result.getInt(1);
     		}
     		// libero recursos
     		result.close();
		}
		catch (Exception ex3)
		{
			System.out.println("Error al consultar claves en la base de datos. "+ex3);
		}
     	return n+1;//Le sumamos 1 pues es el valor que sigue y que ocuparemos
	}

	public void cerrarConexion()
	{
		try
		{
			conexion.close();
			//System.out.println("Se ha cerrado la conexion");
		}
		catch(Exception ex4){
			System.out.println("Error al cerrar la conexion de la base de datos. "+ex4);
		}
	}

	/*public static void main(String args[])
	{
		ConexionBase c=new ConexionBase();
		c.establecerConexion();
	}*/
}