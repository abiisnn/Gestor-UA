import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

//Compilar: javac -cp ,;../lib/mysqlcon.jar;../lib/servlet-api.jar; Interfaz.java

public class Interfaz extends HttpServlet
{
	
	ConexionBase conex=new ConexionBase();

	//Este método se ejecuta una única vez, al ser inicializado por primera vez
	//el servlet.Se suelen inicializar variables y ejecutar operaciones costosas
	//en tiempo de ejecución (abrir ficheros, conectar con bases de datos, etc)
	public void init (ServletConfig config) throws ServletException 
	{
		// Llamada al método init() de la superclase (GenericServlet)
		// Así se asegura una correcta inicialización del servlet
		super.init(config);
		conex.establecerConexion();
	}// fin del método init()


	// Este método es llamado por el servidor web al "apagarse"
	// (al hacer shut down). Sirve para proporcionar una correcta
	// desconexión de una base de datos, cerrar ficheros abiertos, etc.
	public void destroy () 
	{
		super.destroy();
		conex.cerrarConexion();
	} // fin de destroy()

	// Método llamada mediante un HTTP GET
	public void doGet(HttpServletRequest peticion, HttpServletResponse respuesta)  throws ServletException, IOException
	{
		respuesta.setContentType("text/html");
		PrintWriter salida=respuesta.getWriter();
		try
		{
			int ultClave=conex.consultarClave("select count(*) from bibliografia;");//Obtenemos la clave del ultimo registro

			//IMPLEMENTACION HTML. Para comprender facilmente el codigo revisar el archivo Index.html, es un copy paste de ese archivo
			//Pero, como necesitamos el valor de la ultima clave en la que nos quedamos debemos ponerlo en este Servlet

			salida.print("<html lang='es'>");
				salida.print("<head>");
					salida.print("<title></title>");
					salida.print("<meta charset='utf-8'>");
					salida.print("<meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1'/>");
					salida.print("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' integrity='sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u' crossorigin='anonymous'>");
					salida.print("<script> var i="+ultClave+", noClicks=1;</script>");
					salida.print("<script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0-beta1/jquery.js'></script>");
					salida.print("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js'></script>  ");
			        salida.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js'></script>  ");
			        salida.print("<script src='Funciones.js'></script> <!--Invocamos las funciones JavaScript de Funciones.js para el formulario dinamico-->");
				salida.print("</head>");
				
				salida.print("<body>");
					salida.print("<header>");
						salida.print("<div class='container'>");
						salida.print("<h2><center>Bibliografias</center></h2>");
						salida.print("Ingrese las bibliografias de la unidad de aprendizaje.");
						salida.print("</div>");
					salida.print("</header>");
					salida.print("<br>");
					salida.print("<div class='container'>");
						salida.print("<section>");
							salida.print("<table class='table'>");
								salida.print("<tr class='info'>");
									salida.print("<th width=60>Clave</th>");
									salida.print("<th width=6>B</th>");
									salida.print("<th width=30>C</th>");
									salida.print("<th>Bibliografia</th>");
									salida.print("<th></th>");
							    salida.print("</tr>");
							salida.print("</table>");
							salida.print("<form name='formulario' method='POST' action='Controlador' onsubmit='noRegistros()'>");//Aui llamamos a la funcion JS
								salida.print("<table class='table bg-info'  id='dynamic_field' width='500'>");
									salida.print("<tr>");
										salida.print("<td width=1000><label name='etiqueta'>"+ultClave+"</label></td>");
										salida.print("<input type='hidden' name='inicio' value='"+(ultClave-1)+"'></td>");
										salida.print("<input type='hidden' name='clave"+(ultClave-1)+"' value='"+ultClave+"'></td>");
										salida.print("<td><input required name='tipo"+(ultClave-1)+"' placeholder='B' type='radio' value='B'/></td>");
										salida.print("<td><input required name='tipo"+(ultClave-1)+"' placeholder='C' type='radio' value='C'/></td>");
										salida.print("<td><input required name='bibliografia"+(ultClave-1)+"' placeholder='Bibliografia' size=110 type='text'/></td>");
										salida.print("<td><button type='button' name='add' id='add' class='btn btn-success'>Agregar</button></td>");
									salida.print("</tr>");
								salida.print("</table>");
								salida.print("<div class='btn-der'>");
									salida.print("<input id='noCampos' name='noCampos' type='hidden' />");
									salida.print("<input type='submit' name='insertar' value='Continuar' class='btn btn-info'/>");
								salida.print("</div>");
							salida.print("</form>");
						salida.print("</section>");
					salida.print("</div>");
					salida.print("<footer>");
					salida.print("</footer>");
				
				salida.print("</body>");
			salida.print("</html>");

		}
		catch(Exception ex)
		{
			salida.print("Error al cargar el formulario: "+ex);
		}

		finally
		{
			salida.close();
		}
	}

}
