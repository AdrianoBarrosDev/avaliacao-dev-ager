<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF8">
		<title><s:text name="label.titulo.pagina.cadastro"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">

		<div class="container">
			<s:form action="/novoCompromissos.action">

				<div class="card mt-5">
					<div class="card-header">
						<div class="row">
							<div class="col-sm-5">
								<s:url action="todosCompromissos" var="todos"/>
								<a href="${todos}" class="btn btn-success" >Compromissos</a>
							</div>
							
							<div class="col-sm">
								<h5 class="card-title">Novo Compromisso</h5>
							</div>
						</div>
					</div>
					
					<div class="card-body">
						<div class="row align-items-center">
							<label for="id" class="col-sm-2 col-form-label text-center">
								<s:text name="label.id" />
							</label>	

							<div class="col-sm-2">
								<s:textfield cssClass="form-control" id="id" name="compromissoVo.rowid" readonly="true"/>							
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="nome" class="col-sm-2 col-form-label text-center">
								<s:text name="label.codigoFuncionario" />
							</label>

							<div class="col-sm-5">
								<s:textfield cssClass="form-control" id="codigoFuncionario" name="compromissoVo.codigoFuncionario"/>							
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="nome" class="col-sm-2 col-form-label text-center">
								<s:text name="label.codigoAgenda" />
							</label>

							<div class="col-sm-5">
								<s:textfield cssClass="form-control" id="codigoAgenda" name="compromissoVo.codigoAgenda"/>							
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="nome" class="col-sm-2 col-form-label text-center">
								<s:text name="label.data" />
							</label>

							<div class="col-sm-5">
								<s:textfield cssClass="form-control" type="date" id="data" name="compromissoVo.data"/>							
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="nome" class="col-sm-2 col-form-label text-center">
								<s:text name="label.horario" />
							</label>

							<div class="col-sm-5">
								<s:textfield cssClass="form-control" type="time" id="horario" name="compromissoVo.horario"/>							
							</div>	
						</div>
					</div>

					<div class="card-footer">
						<div class="form-row">
							<s:submit cssClass="btn btn-primary col-sm-4 offset-sm-1" value="Salvar" />
							<button type="reset" class="btn btn-secondary col-sm-4 offset-sm-2">Limpar Formulario</button>
						</div>
					</div>
				</div>
			</s:form>			
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
	</body>
</html>