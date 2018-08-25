import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

//Compilar: javac -cp ,;../lib/mysqlcon.jar;../lib/servlet-api.jar; Controlador.java

public class Controlador extends HttpServlet{
	ConexionBase conex=new ConexionBase();
	String[] datos=new String[3];

	public String insertarQuery(String table, String[] values){//Construye el query para insertar registros
		return "insert into "+table+" values("+datos[0]+", '"+datos[1]+"','"+datos[2]+"')";
	}

	public String eliminarQuery(String table, String id){//Construye el query para eliminar registros
		return "delete from "+table+" where clave="+id;
	}


	public void doGet(HttpServletRequest peticion, HttpServletResponse respuesta)  throws ServletException, IOException{
		respuesta.setContentType("text/html");
		PrintWriter salida=respuesta.getWriter();
		try{

			datos[0]=peticion.getParameter("clave");
			datos[1]=peticion.getParameter("tipo");
			datos[2]=peticion.getParameter("bibliografia");

	
			/*salida.print("<HTML>");
			salida.print("	<HEAD>");
			salida.print("		<TITLE>");
			salida.print("Interfaz");
			salida.print("		</TITLE>");
			salida.print("	</HEAD>");
			salida.print("	<BODY><center>");
			salida.print(	"		<P>");

			salida.print(	"		</P>");
			salida.print("	</center></BODY>");
			
			salida.print("</HTML>");*/

		}
		catch(Exception ex){salida.print("ERROR"+ex);}
		finally{salida.close();}
	}


}
