$(document).ready(function(){
	
	//Funcion para datatable
	tablaUsuarios = $("#tablaUsuarios").DataTable({
	responsive: true,
	"ordering": false,
	"language": {
		"lengthMenu": "Mostrar _MENU_ registros",
		"zeroRecords": "No se encontraron resultados",
		"info": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
		"infoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
		"infoFiltered": "(filtrado de un total de _MAX_ registros)",
		"sSearch": "Buscar:",
		"oPaginate": {
			"sFirst": "Primero",
			"sLast":"Último",
			"sNext":"Siguiente",
			"sPrevious": "Anterior"
		},
		"sProcessing":"Procesando...",
	}
	});
	
	//Funcion inicial para listar
	$.ajax({
		type:"post",
   		url:"Cliente", //Servlet
		data: "listar",
		dataType:"json",
		success: function(resultado){
			for(let u of resultado){
				tablaUsuarios.row.add(
					[
						`${u.cedulaCliente}`,
						`${u.direccionCliente}`,
						`${u.emailCliente}`,
						`${u.nombreCliente}`,
						`${u.telefonoCliente}`,
						'<div class="btn-group">'+
						'<button type="button" name="'+`${u.cedulaCliente}`+'"  class="btn btn-warning btnVer">Editar</button>'+
						'<button type="button"  name="'+`${u.cedulaCliente}`+'" class="btn btn-danger btnBorrar">Borrar</button>'+
						'</div>'
					]
				)
			}
			tablaUsuarios.draw()
			tablaUsuarios.columns.adjust().responsive.recalc();
		}
	});
	
	//Click boton borrar
	tablaUsuarios.on('click', '.btnBorrar', function(){	
		var cedula = $(this).attr("name")
	 	Swal.fire({
			title: "Cuidado!",
		  	text: "Esta seguro de eliminar el registro "+cedula+"?",
		  	icon: 'warning',
		  	showCancelButton: true,
		  	confirmButtonColor: '#3085d6',
		  	cancelButtonColor: '#d33',
		  	confirmButtonText: "Si, borrar el registro!",
			cancelButtonText: "Cancelar"
		}).then((result) => {
			if (result.isConfirmed) {
				eliminarCliente(cedula);
		  	}
		});
	})
	
	//Click boton ver/editar
	tablaUsuarios.on('click', '.btnVer', function(){
		var cedula = $(this).attr("name")
		verCliente(cedula);
	});
	
	//Funcion agregar cliente
	$("#formAgregar").submit(function(e){
		e.preventDefault();
		$.ajax({
			type:"post",
	   		url:"Cliente", //Servlet
			data: $("#formAgregar").serialize(),
			dataType:"json",
			success: function(resultado){
				if(resultado[0].estado=="Ok"){
					Swal.fire(
						"Proceso exitoso",
					  	"Cliente agregado",
					  	"success"
					)
					setTimeout(function(){
					    window.location.replace("clientes.jsp");
					  }, 800);
				}
			}
		});
	})
	
	//Funcion editar cliente
	$("#formEditar").submit(function(e){
		e.preventDefault();
		$.ajax({
			type:"post",
	   		url:"Cliente", //Servlet
			data: $("#formEditar").serialize(),
			dataType:"json",
			success: function(resultado){
				if(resultado[0].estado=="Ok"){
					Swal.fire(
						"Proceso exitoso",
					  	"Cliente actualizado",
					  	"success"
					);
					setTimeout(function(){
					    window.location.replace("clientes.jsp");
					  }, 800);
				}
			}
		});
	})
	
	//Funcion eliminar usuario
	function eliminarCliente(id){
		$.ajax({
			type:"post",
	   		url:"Cliente", //Servlet
			data: "borrar=&cedula="+id,
			dataType:"json",
			success: function(resultado){
				if(resultado[0].estado=="Ok"){
					Swal.fire(
						"Proceso exitoso",
					  	"Cliente eliminado",
					  	"success"
					);
					setTimeout(function(){
					    window.location.replace("usuarios.jsp");
					  }, 800);
				}
			}
		});
	}
	
	//Funcion traer usuario
	function verCliente(id){
		$.ajax({
			type:"post",
	   		url:"Cliente", //Servlet
			data: "ver=&cedula="+id,
			dataType:"json",
			success: function(resultado){
				console.log(resultado);
				if(resultado!=null){
					$("#cedula").val(resultado.cedulaCliente)
					$("#direccion").val(resultado.direccionCliente)
					$("#email").val(resultado.emailCliente)
					$("#nombre").val(resultado.nombreCliente)
					$("#telefono").val(resultado.telefonoCliente)
				}
				var modalEdit = new bootstrap.Modal(document.getElementById('modalEditarUsuario'))
				modalEdit.show()
			}
		});
	}
	
	
});