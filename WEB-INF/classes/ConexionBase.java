import java.sql.*;
import java.util.*;
//javac -cp ,;../lib/mysqlcon.jar; ConexionBase.java
//java -cp ,;../lib/mysqlcon.jar; ConexionBase
public class ConexionBase
{
	Connection conexion;
	String dbName, url, user, pass;
	// Campos necesarios para comunicarse con la BD
	public ConexionBase()
	{
		dbName = "ingenieria_de_software";
		url = "jdbc:mysql://localhost:3306/";
		user = "root";
		pass = "root";
	}
	// Conexion a la BD
	public void establecerConexion()
	{ 
		try { 
			Class.forName("com.mysql.jdbc.Driver");          		
			conexion = DriverManager.getConnection(url+dbName, user, pass);
		}
		catch (Exception ex1)
		{
			System.out.println("Error al establecer conexion con la base de datos. "+ex1);
		}
	}
	// Podemos insertar en la BD con esta funcion
	public void insertar(String query) 
	{
		try
		{
			Statement statement=conexion.prepareStatement(query);
			statement.executeUpdate(query);
		}
		catch (Exception ex2)
		{
			System.out.println("Error al insertar bibliografias en la base de datos. "+ex2);
		}
	}
	// Obtener la ultima clave
	public int consultarClave(String query)
	{
		int n=0;
		try
		{
			// Instrucciones para consultar los registros existentes
			Statement consulta=conexion.createStatement();
			ResultSet result=consulta.executeQuery(query);
		
			if(result.next()) //Si hay resultados obtengo el valor. 
			{ 
        		n= result.getInt(1);
     		}
     		// Libero recursos
     		result.close();
		}
		catch (Exception ex3)
		{
			System.out.println("Error al consultar claves en la base de datos. "+ex3);
		}
		// Le sumamos 1 pues es el valor que sigue y que ocuparemos
     	return n+1; 
	}

	public void cerrarConexion()
	{
		try
		{
			conexion.close();
			//System.out.println("Se ha cerrado la conexion");
		}
		catch(Exception ex4)
		{
			System.out.println("Error al cerrar la conexion de la base de datos. "+ex4);
		}
	}
}