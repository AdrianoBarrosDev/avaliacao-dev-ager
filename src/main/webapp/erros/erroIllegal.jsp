<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF8">
		<title><s:text name="label.titulo.pagina"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
		<link rel="stylesheet" href="/avaliacao/css/global.css" />
	</head>
	<body>
	
		<div class="background-container">
		    <img src="/avaliacao/imagens/LogoBackground.png" class="background-image" />
		    <img src="/avaliacao/imagens/LogoBackground.png" class="background-image" />
		    <img src="/avaliacao/imagens/LogoBackground.png" class="background-image" />
		</div>
		
		<div class="d-flex justify-content-center align-items-center flex-column pt-5" style="position: relative; z-index: 1;">
			
			<img class="logoNavSoc mt-5 mb-5" src="/avaliacao/imagens/LogoSocNav.png" />
		
			<h1 class="mt-5">Ops! Argumento Inválido.</h1>
		    <p style="margin-top: 100px;"><strong>Erro:</strong> <s:property value="exception.message" /></p>
		    <a class="btnNovo" href="javascript:history.back()" style="margin-top: 100px;">Voltar</a>
		</div>
	    
	</body>
</html>
