
//var i=1; 
// Clona la fila oculta que tiene los campos base, y la agrega al final de la tabla

function noRegistros(){
       document.getElementById("noCampos").value=i;
}

$(document).ready(function(){  
      			 
	$('#add').click(function(){    
        $('#dynamic_field').append(
        	"<tr><td width=1000><label name='etiqueta'>"+(i+1)+
        	"</label></td><input type='hidden' name='clave"+i+"' value='"+(i+1)+
        	"'></td><td><input required name='tipo"+i+"' placeholder='B' type='radio' value='B'/></td><td><input required name='tipo"+i+
        	"' placeholder='C' type='radio' value='C'/></td><td><input required name='bibliografia"+i+
        	"' placeholder='Bibliografia' size=110 type='text'/></td><td class='eliminar'><input type='button' class='btn btn-danger btn_remove' value='Eliminar'></td></tr>");  
           	i++;
    });  
     
			 
	// Evento que selecciona la fila y la elimina 
	$(document).on("click",".eliminar",function(){
		i--;
		var parent = $(this).parents().get(0);
		$(parent).remove();
	});
});
