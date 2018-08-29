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

	// Construye el query para insertar registros
	public String generarQuery(String table, String[] values)
	{
		return "insert into "+ table +" values(" + values[0]+", '"+ values[1]+ "','" + values[2] + "')";
	} // Fin generarQuery

	// Método llamada mediante un HTTP GET
	public void doPost(HttpServletRequest peticion, HttpServletResponse respuesta)  throws ServletException, IOException
	{
		// Arreglo que nos servira para manejar los registros entrantes
		String[] datos = new String[3];
		// Variable para escribir en HTML
		respuesta.setContentType("text/html");
		PrintWriter salida = respuesta.getWriter();
		try
		{
			// Necesitamos obtener el numero de campos que se insertaran
			int noCampos = Integer.parseInt(peticion.getParameter("noCampos"));
			// Necesitamos saber en donde se quedo el ult registro
			int valorInicio = Integer.parseInt(peticion.getParameter("inicio"));
			// Con ayuda de este for, vamos recorriendo cada registro
			for (int i=valorInicio; i<noCampos; i++)
			{
				datos[0] = peticion.getParameter("clave" +i);
				datos[1] = peticion.getParameter("tipo" +i);
				datos[2] = peticion.getParameter("bibliografia" +i);
				conex.insertar(generarQuery("bibliografia", datos));
			} // Fin for

			//Mensaje 01 en alert
			salida.print("<script>alert('El registro se ha hecho de manera exitosa.'); window.location.replace('Interfaz');</script>");
		}// Fin try
		catch(Exception ex)
		{
			salida.print("<script>alert('Ha ocurrido un error: '"+ex+"); window.location.replace('Interfaz');</script>");
		}
		finally
		{
			salida.close();
		}
	} // Fin metodo doPost
} // Fin servlet Controlador