import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

//Compilar: javac -cp ,;../lib/mysqlcon.jar;../lib/servlet-api.jar; Controlador.java

public class Controlador extends HttpServlet
{	
	ConexionBase conex = new ConexionBase();
	/*
		Este método se ejecuta una única vez, al ser inicializado por primera vez 
		el servlet.Se suelen inicializar variables y ejecutar operaciones costosas
		en tiempo de ejecución (abrir ficheros, conectar con bases de datos, etc)
	*/
	public void init (ServletConfig config) throws ServletException 
	{
		// Llamada al método init() de la superclase (GenericServlet)
		// Así se asegura una correcta inicialización del servlet
		super.init(config);
		conex.establecerConexion();
	} // Fin del método init()

	/* 
		Este método es llamado por el servidor web al "apagarse"
		(al hacer shut down). Sirve para proporcionar una correcta
		desconexión de una base de datos, cerrar ficheros abiertos, etc.
	*/
	public void destroy () 
	{
		super.destroy();
		conex.cerrarConexion();
	} // Fin de destroy()
	
<<<<<<< HEAD
	public String generarQuery(String table, String[] values){//Construye el query para insertar registros
=======
	//Construye el query para insertar registros
	public String insertarQuery(String table, String[] values)
	{
>>>>>>> 1dd0e19aebf81730d0b093b81eae6542e01689cd
		return "insert into "+table+" values("+values[0]+", '"+values[1]+"','"+values[2]+"')";
	}//fin generarQuery

<<<<<<< HEAD
	// Método llamada mediante un HTTP GET
	public void doGet(HttpServletRequest peticion, HttpServletResponse respuesta)  throws ServletException, IOException{
=======
	//Construye el query para eliminar registros
	public String eliminarQuery(String table, String id)
	{
		return "delete from "+table+" where clave="+id;
	}

	// Método llamada mediante un HTTP POST
	public void doPost(HttpServletRequest peticion, HttpServletResponse respuesta)  throws ServletException, IOException
	{
>>>>>>> 1dd0e19aebf81730d0b093b81eae6542e01689cd
		String[] datos=new String[3];
		respuesta.setContentType("text/html");
		PrintWriter salida=respuesta.getWriter();
		try{
<<<<<<< HEAD
			int noCampos=Integer.parseInt(peticion.getParameter("noCampos"));//Necesitamos obtener el numero de campos que se insertaran

			for (int i=0; i<noCampos; i++){//Con ayuda de este for, vamos recorriendo cada registro
				datos[0]=peticion.getParameter("clave"+i);
				datos[1]=peticion.getParameter("tipo"+i);
				datos[2]=peticion.getParameter("bibliografia"+i);
				conex.insertar(generarQuery("bibliografia", datos));
			}//fin for
			
		}//fin try
		catch(Exception ex){salida.print("ERROR"+ex);}
		finally{salida.close();}
	}// fin metodo doPost


}//fin servlet Controlador
=======
			datos[0]=peticion.getParameter("clave");
			datos[1]=peticion.getParameter("tipo");
			datos[2]=peticion.getParameter("bibliografia");

			conex.operacionSQL(insertarQuery("bibliografia", datos));
		}
		catch(Exception ex){salida.print("ERROR"+ex);}
		finally{salida.close();}
	}
}
>>>>>>> 1dd0e19aebf81730d0b093b81eae6542e01689cd
