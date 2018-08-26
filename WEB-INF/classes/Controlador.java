import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

//Compilar: javac -cp ,;../lib/mysqlcon.jar;../lib/servlet-api.jar; Controlador.java

public class Controlador extends HttpServlet{
	ConexionBase conex=new ConexionBase();

	//Este método se ejecuta una única vez, al ser inicializado por primera vez
	//el servlet.Se suelen inicializar variables y ejecutar operaciones costosas
	//en tiempo de ejecución (abrir ficheros, conectar con bases de datos, etc)
	public void init (ServletConfig config) throws ServletException {
		// Llamada al método init() de la superclase (GenericServlet)
		// Así se asegura una correcta inicialización del servlet
		super.init(config);
		conex.establecerConexion();
	}// fin del método init()


	// Este método es llamado por el servidor web al "apagarse"
	// (al hacer shut down). Sirve para proporcionar una correcta
	// desconexión de una base de datos, cerrar ficheros abiertos, etc.
	public void destroy () {
		super.destroy();
		conex.cerrarConexion();
	} // fin de destroy()
	
	public String generarQuery(String table, String[] values){//Construye el query para insertar registros
		return "insert into "+table+" values("+values[0]+", '"+values[1]+"','"+values[2]+"')";
	}//fin generarQuery

	// Método llamada mediante un HTTP GET
	public void doGet(HttpServletRequest peticion, HttpServletResponse respuesta)  throws ServletException, IOException{
		String[] datos=new String[3];
		respuesta.setContentType("text/html");
		PrintWriter salida=respuesta.getWriter();
		try{
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
