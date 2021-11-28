$(document).ready(function(){
	//Funcion Login usuario
	$("#formLogin").submit(function(e){
		e.preventDefault();
		$.ajax({
			type:"post",
	   		url:"Login", //Servlet
			data: $("#formLogin").serialize(),
			dataType:"json",
			success: function(resultado){
				if(resultado[0].estado=="Ok"){
					Swal.fire(
						"Proceso exitoso",
					  	"Login exitoso",
					  	"success"
					)
					setTimeout(function(){
					    window.location.replace("usuarios.jsp");
					  }, 800);
				}else{
					Swal.fire(
						"Proceso fallido",
					  	resultado[0].estado,
					  	"error"
					)
				}
			}
		});
	})
});