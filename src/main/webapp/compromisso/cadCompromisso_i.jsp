<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><s:text name="label.titulo.pagina.cadastro"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
		<link rel="stylesheet" href="/avaliacao/css/navCustomizada.css" />
		<link rel="stylesheet" href="/avaliacao/css/global.css" />
	</head>
	<body>
	
		<div class="background-container justify-content-end align-items-center">
		    <img src="/avaliacao/imagens/LogoBackground.png" class="background-image" style="height: 120%"/>
		</div>
		
		<div class="d-flex justify-content-start aling-items-start w-100 flex-column flex-xxl-row" style="position: relative; z-index: 1;">
		
			<nav class="sidebar">
			
				<img class="logoNavSoc" src="/avaliacao/imagens/LogoSocNav.png" />
			
				<div class="d-xxl-block d-flex justify-content-center align-items-center gap-5">
					<a href="todosFuncionarios.action" class="desativado gap-3">
						<img src="/avaliacao/imagens/FuncionariosIcon.png" />
						Funcionários
					</a>
					<a href="todosAgendas.action" class="desativado gap-3">
						<img src="/avaliacao/imagens/AgendasIcon.png" />
						Agendas
					</a>
					<a href="todosCompromissos.action" class="ativado gap-3">
						<img src="/avaliacao/imagens/CompromissosIcon.png" />
						Compromissos
					</a>
				</div>
				
			</nav>

			<div class="container-fluid p-5" style="margin-left: 260px;">
				
				<h1 class="mt-4"><s:text name="%{compromissoVo.rowid != null && !compromissoVo.rowid.isEmpty() ? 'label.titulo.editar' : 'label.titulo.novo'}" /></h1>
				
				<s:form action="/salvarCompromissos.action">
	
					<div class="card mt-5">
						
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
								<label for="codigoFuncionario" class="col-sm-2 col-form-label text-center">
									<s:text name="label.codigoFuncionario" />
								</label>
	
								<div class="col-sm-5">
									<s:textfield cssClass="form-control" id="codigoFuncionario" name="compromissoVo.codigoFuncionario" required="true" />							
								</div>	
							</div>
							
							<div class="row align-items-center mt-3">
								<label for="codigoAgenda" class="col-sm-2 col-form-label text-center">
									<s:text name="label.codigoAgenda" />
								</label>
	
								<div class="col-sm-5">
									<s:textfield cssClass="form-control" id="codigoAgenda" name="compromissoVo.codigoAgenda" required="true" />							
								</div>	
							</div>
							
							<div class="row align-items-center mt-3">
								<label for="data" class="col-sm-2 col-form-label text-center">
									<s:text name="label.data" />
								</label>
	
								<div class="col-sm-5">
									<s:textfield cssClass="form-control" type="date" id="data" name="compromissoVo.data" required="true" />							
								</div>	
							</div>
							
							<div class="row align-items-center mt-3">
								<label for="horario" class="col-sm-2 col-form-label text-center">
									<s:text name="label.horario" />
								</label>
	
								<div class="col-sm-5">
									<s:textfield cssClass="form-control" type="time" id="horario" name="compromissoVo.horario" required="true" />							
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
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
	</body>
</html>