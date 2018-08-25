import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Interfaz extends HttpServlet{
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
