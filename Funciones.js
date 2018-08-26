//FUNCIONES JAVASCRIPT NECESARIAS PARA CONTROLAR EL FORMULARIO HTML DINAMICO

function noRegistros()//Funcion para saber cuantos registros el usuario va a ingresar cuando da clic en Continuar
{
    document.getElementById("noCampos").value=i;
}

// Va agregando otra linea de campos para un nuevo registro. 
$(document).ready(function()
{   			 
	$('#add').click(function()
	{    //Tenemos que poner todos los inputs HTML para cada nuevo registro
        $('#dynamic_field').append("<tr><td width=1000><label name='etiqueta'>"+(i+1)+
        	"</label></td><input type='hidden' name='clave"+i+"' value='"+(i+1)+
        	"'></td><td><input required name='tipo"+i+"' placeholder='B' type='radio' value='B'/></td><td><input required name='tipo"+i+
        	"' placeholder='C' type='radio' value='C'/></td><td><input required name='bibliografia"+i+
        	"' placeholder='Bibliografia' size=110 type='text'/></td><td class='eliminar'><input type='button' class='btn btn-danger btn_remove' value='Eliminar'></td></tr>");  
           	i++;//Vamos aumentando la clave de la biliografia
    });  
     
			 
	// Evento que selecciona la fila y la elimina. NOTA: Aun no se maneja el ordenamiento de claves si se eliminan campos de enmedio.
	//Debe ser por orden descendente y consecutivamente.
	$(document).on("click",".eliminar",function()
	{
		i--;//Le restamos a la clave de la bibliografia
		var parent = $(this).parents().get(0);
		$(parent).remove();
	});
});
