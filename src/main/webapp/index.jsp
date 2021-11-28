<%@ page import="controlador.Conexion" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="shortcut icon" href="#" /> 
	<title>Login</title> 
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA==" crossorigin="anonymous" referrerpolicy="no-referrer" />	
	<link rel="stylesheet" type="text/css" href="assets/css/login.css">
</head>
<body>
	<img class="wave" src="assets/img/Logo_Log.png">
	<div class="container">
		<div class="img">
			<img src="assets/img/bg.svg">
		</div>
		<div class="login-content">
			<form id="formLogin">
				<input type="hidden" name="login" value="">
				<img src="assets/img/avatar.svg">
				<h2 class="title">Bienvenido</h2>
           		<div class="input-div one">
           		   <div class="i">
           		   	<i class="fas fa-user"></i>
           		   </div>
           		   <div class="div">
        		   	<h5>Usuario</h5>
        		   	<input type="text" name="usuario"  class="input" required>
           		   </div>
           		</div>
           		<div class="input-div pass">
           		   <div class="i">
           		    	<i class="fas fa-lock"></i>
           		   </div>
           		   <div class="div">
           		    	<h5>Contraseña</h5>
           		    	<input type="password" name="password" class="input" required>
            	   </div>
            	</div>
            	
              <button type="submit" class="btn" value="Volver"><span>Ingresar</span></button>
              <button class="btn" value="Volver"><span>Volver</span></button>
            </form>
        </div>
    </div>
   <!-- Scripts Bootstrap & DataTables -->
	<script type="text/javascript" src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="assets/vendor/DataTables/datatables.min.js"></script>
	<!-- Script FontAwesome -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"></script>
	<!-- Script Sweet AlertJS -->
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script><script type="text/javascript" src="assets/js/main.js"></script>
    <script type="text/javascript" src="assets/js/login.js"></script>
    <script>       

       function sololetras(l) {
        key=l.keyCode || l.which;
        teclado=String.fromCharCode(key);
        letras="qwertyuiopasdfghjklÃÂ±zxcvbnm QWERTYUIOPASDFGHJKLÃâZXCVBNM";
        especiales="s";
        teclado_especial=false; 

        for( var i in especiales){
          if(key==especiales[i]){
            teclado_especial=true;
          }
        } 

        if (letras.indexOf(teclado)==- 1 && !teclado_especial) {
          return false;
        }
      }

    </script>
</body>
</html>
