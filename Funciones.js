// FUNCIONES JAVASCRIPT NECESARIAS PARA CONTROLAR EL FORMULARIO HTML DINAMICO
// Funcion para saber cual sera el ultimo registro cuando da clic en Continuar
function noRegistros()
{
    document.getElementById("noCampos").value = i;
}

$(document).ready(function(){   			 
	var botonEliminar;
	
	//Boton AGREGAR - Va agregando otra linea de campos para un nuevo registro. 
	$('#add').click(function() 
	{    
		// Si solo existe una fila en el formulario, entonces SI agregamos el boton ELIMINAR en la prox fila
		if(noClicks == 1)
		{
			botonEliminar = "<td class='eliminar'><input type='button' class='btn btn-danger btn_remove' value='Eliminar'></td>"
		}
		else // Si hay más registros, omitimos el boton
		{
			botonEliminar = "";
		}

		// Terminamos de agregar nuevas filas para nuevos registros
		$('#dynamic_field').append("<tr><td width=1000><label name='etiqueta'>"+(i+1)+
			"</label></td><input type='hidden' name='clave"+i+"' value='"+(i+1)+
			"'></td><td><input required name='tipo"+i+"' placeholder='B' type='radio' value='B'/></td><td><input required name='tipo"+i+
			"' placeholder='C' type='radio' value='C'/></td><td><input required name='bibliografia"+i+
			"' placeholder='Bibliografia' size=110 type='text'/></td>"+botonEliminar+"</tr>");  
		// Vamos aumentando la clave de la biliografia		
		i++;	
		noClicks++;	        
	});  

	$(document).on("click",".eliminar",function() // BOTON ELIMINAR
	{
		if(noClicks!=1){
			document.getElementById("dynamic_field").deleteRow(noClicks-1);//Borramos la ultima fila del formulario
			i--; // Le restamos a la clave de la bibliografia
			noClicks--;
		}
	});
});