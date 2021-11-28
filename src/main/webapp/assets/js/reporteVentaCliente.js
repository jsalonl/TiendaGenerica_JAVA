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
   		url:"Reporte", //Servlet
		data: "listarVentasCliente",
		dataType:"json",
		success: function(resultado){
			let total = 0;
			for(let u of resultado){
				total += parseFloat(`${u.valorTotalVentas}`);
				tablaUsuarios.row.add(
					[
						`${u.cedulaCliente}`,
						`${u.nombreCliente}`,
						`${u.valorTotalVentas}`
					]
				)
			}
			$("#granTotal").html(total);
			tablaUsuarios.draw()
			tablaUsuarios.columns.adjust().responsive.recalc();
		}
	});
	
	
	
});