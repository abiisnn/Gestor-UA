import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Interfaz extends HttpServlet{
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

	// Método llamada mediante un HTTP POST
	public void doPost(HttpServletRequest peticion, HttpServletResponse respuesta)  throws ServletException, IOException{
		respuesta.setContentType("text/html");
		PrintWriter salida=respuesta.getWriter();
		try{

			String palabra1=peticion.getParameter("palabra1");

	
			salida.print("<HTML>");
			salida.print("	<HEAD>");
			salida.print("		<TITLE>");
			salida.print("Interfaz");
			salida.print("		</TITLE>");
			salida.print("	</HEAD>");
			salida.print("	<BODY><center>");
			salida.print(	"		<P>");

			salida.print(	"		</P>");
			salida.print("	</center></BODY>");
			
			salida.print("</HTML>");

		}
		catch(Exception ex){salida.print("ERROR"+ex);}
		finally{salida.close();}
	}

}
