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
			<s:form action="/novoAgendas.action">

				<div class="card mt-5">
					<div class="card-header">
						<div class="row">
							<div class="col-sm-5">
								<s:url action="todosAgendas" var="todos"/>
								<a href="${todos}" class="btn btn-success" >Agendas</a>
							</div>
							
							<div class="col-sm">
								<h5 class="card-title">Nova Agenda</h5>
							</div>
						</div>
					</div>
					
					<div class="card-body">
						<div class="row align-items-center">
							<label for="id" class="col-sm-2 col-form-label text-center">
								<s:text name="label.id" />
							</label>	

							<div class="col-sm-2">
								<s:textfield cssClass="form-control" id="id" name="agendaVo.rowid" readonly="true"/>							
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="nome" class="col-sm-2 col-form-label text-center">
								<s:text name="label.nome" />
							</label>

							<div class="col-sm-5">
								<s:textfield cssClass="form-control" id="nome" name="agendaVo.nome"/>							
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="periodoDisponivel" class="col-sm-2 col-form-label text-center">
								<s:text name="label.periodoDisponivel" />
							</label>

							<div class="col-sm-5">
								<s:select 
									cssClass="form-select" 
									name="agendaVo.periodoDisponivel"		
									list="opcoesPeriodo"					
									id="periodoDisponivel"
									headerKey=""
									headerValue="Escolha um Período..."
									listKey="codigo"
									listValue="descricao"
									value="agendaVo.periodoDisponivel"
								/>
								<s:property value="agendaVo.periodoDisponivel" />
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